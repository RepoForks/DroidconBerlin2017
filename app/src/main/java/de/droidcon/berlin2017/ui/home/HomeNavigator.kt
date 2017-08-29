package de.droidcon.berlin2017.ui.home

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import de.droidcon.berlin2017.R
import de.droidcon.berlin2017.model.Session
import de.droidcon.berlin2017.model.Speaker
import de.droidcon.berlin2017.ui.navigation.Navigator
import de.droidcon.berlin2017.ui.sessions.SessionsController
import de.droidcon.berlin2017.ui.speakers.SpeakersController

/**
 * The navigator for the home
 *
 * @author Hannes Dorfmann
 */
class HomeNavigator(private val controller : Controller) : Navigator {
  override fun showHome() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showSessions() {
    controller.getChildRouter(controller.view!!.findViewById(R.id.home_controller_containers))
        .setRoot(
            RouterTransaction.with(SessionsController())
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler())
        )
  }

  override fun showMySchedule() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showSpeakers() {
   controller.getChildRouter(controller.view!!.findViewById(R.id.home_controller_containers))
       .setRoot(
           RouterTransaction.with(SpeakersController())
               .popChangeHandler(FadeChangeHandler())
               .pushChangeHandler(FadeChangeHandler())
       )
  }

  override fun showSpeakerDetails(speaker: Speaker) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showSessionDetails(session: Session) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showTweets() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}