package com.devonfw.mythaistar

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary


class App : Application() {

  val component: AppComponent = DaggerAppComponent.builder().build()
  companion object {
    fun component(context: Context) = (context.applicationContext as App).component
  }

  override fun onCreate() {
    super.onCreate()
    if(BuildConfig.DEBUG){
      LeakCanary.install(this)
    }
  }
}
