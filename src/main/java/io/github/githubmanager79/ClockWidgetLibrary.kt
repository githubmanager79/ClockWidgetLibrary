package io.github.githubmanager79

import android.content.Context
import android.content.Intent

object ClockWidgetLibrary {
  fun showClock(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
  }
}