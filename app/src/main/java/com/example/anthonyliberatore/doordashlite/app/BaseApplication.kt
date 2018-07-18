package com.example.anthonyliberatore.doordashlite.app

import android.app.Application
import com.example.anthonyliberatore.doordashlite.app.di.application.ApplicationComponent
import com.example.anthonyliberatore.doordashlite.app.di.application.ApplicationModule
import com.example.anthonyliberatore.doordashlite.app.di.application.DaggerApplicationComponent

class BaseApplication : Application() {

  lateinit var component: ApplicationComponent

  override fun onCreate() {
    super.onCreate()

    inject()
  }

  fun inject() {
    component = DaggerApplicationComponent.builder().applicationModule(
        ApplicationModule(this)).build()
    component.inject(this)
  }
}