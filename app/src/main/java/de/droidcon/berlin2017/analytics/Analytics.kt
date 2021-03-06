package de.droidcon.berlin2017.analytics

import com.bluelinelabs.conductor.Controller

/**
 * Analytics / Tracking
 *
 * @author Hannes Dorfmann
 */
interface Analytics {
  fun trackScreen(controller : Controller)
  fun trackLoadSessionDetails(id: String)
  fun trackLoadSpeakerDetails(id: String)
  fun trackSearch(query : String)
  fun trackSessionMarkedAsFavorite(sessionId : String)
  fun trackSessionRemovedFromFavorite(sessionId : String)
  fun trackInstallUpdateDismissed()
  fun trackInstallUpdateClicked()
  fun trackTwitterRefresh()
  fun trackSessionNotificationOpened(sessionId: String)
  fun trackSessionNotificationGenerated(sessionId: String)
  fun trackShowSourceCode()
  fun trackShowLicenses()
  fun trackFastScrollStarted(controllerName : String)
}