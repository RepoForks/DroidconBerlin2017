package de.droidcon.berlin2017.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import de.droidcon.berlin2017.R
import de.droidcon.berlin2017.model.Session
import de.droidcon.berlin2017.ui.splash.SplashController


class MainActivity : AppCompatActivity() {

  companion object {
    fun buildSessionDetailsIntent(context: Context, session: Session): Intent {
      TODO("Implement")
    }
  }

  /*
  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      id.navigation_home -> {
        message.setText(string.title_home)
        return@OnNavigationItemSelectedListener true
      }
      id.navigation_dashboard -> {
        message.setText(string.title_dashboard)
        return@OnNavigationItemSelectedListener true
      }
      id.navigation_notifications -> {
        message.setText(string.title_notifications)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
  }
  */

  private lateinit var router: Router

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    val container = findViewById<ViewGroup>(R.id.controller_container)

    router = Conductor.attachRouter(this, container, savedInstanceState)
    if (!router.hasRootController()) {
      router.setRoot(RouterTransaction.with(SplashController()))
    }
  }

  override fun onBackPressed() {
    if (!router.handleBack()) {
      super.onBackPressed()
    }
  }
}