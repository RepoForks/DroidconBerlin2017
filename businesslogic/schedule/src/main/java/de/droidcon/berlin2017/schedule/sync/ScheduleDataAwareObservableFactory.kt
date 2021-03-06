package de.droidcon.berlin2017.schedule.sync

import de.droidcon.berlin2017.schedule.backend.ScheduleDataStateDeterminer
import io.reactivex.Observable
import io.reactivex.Scheduler
import timber.log.Timber

/**
 * This is a Factory that creates an rx Observable that before running checks if the schedule data
 * (sessions, speaker etc.) is available / up-to-date on the local device.
 * If not [ScheduleSync] will be started.
 *
 * @author Hannes Dorfmann
 */
class ScheduleDataAwareObservableFactory(private val scheduleSync: ScheduleSync,
    private val scheduleDataStateDeterminer: ScheduleDataStateDeterminer,
    private val backgroundSyncScheduler: Scheduler) {

  /**
   * Creates an Observable that is aware of the schedule data on the users device.
   * Depending on the state of the schedule data observables created by this method
   * may wait until a full sync has been finished (i.e. in case no data was on users device before)
   * before resuming with the original observable, or maybe a background sync will be started
   * (not waiting to complete)
   */
  fun <T> create(originalObservable: Observable<T>): Observable<T> =
      scheduleDataStateDeterminer.getScheduleSyncDataState().flatMapObservable {
        Timber.d("Schedule Sync state: $it")
        when (it) {
          ScheduleDataStateDeterminer.ScheduleDataState.UP_TO_DATE -> originalObservable

          ScheduleDataStateDeterminer.ScheduleDataState.NO_DATA ->
            scheduleSync.executeSync()
                .andThen(scheduleDataStateDeterminer.markScheduleSyncedSuccessful())
                .andThen( originalObservable)

          ScheduleDataStateDeterminer.ScheduleDataState.RUN_BACKGROUND_SYNC -> {
            // Execute sync in background
            scheduleSync.executeSync()
                .andThen(scheduleDataStateDeterminer.markScheduleSyncedSuccessful())
                .observeOn(backgroundSyncScheduler)
                .subscribeOn(backgroundSyncScheduler)
                .subscribe({ Timber.d("Synced schedule in background successfully") },
                    { Timber.e(it, "Error while syncing schedule in background") })

            // Continue with original observable in parallel
            originalObservable
          }
        }
      }

}