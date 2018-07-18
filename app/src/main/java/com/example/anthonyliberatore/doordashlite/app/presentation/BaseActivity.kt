package com.example.anthonyliberatore.doordashlite.app.presentation

import android.support.v7.app.AppCompatActivity
import com.example.anthonyliberatore.doordashlite.app.BaseApplication
import com.example.anthonyliberatore.doordashlite.app.di.application.ApplicationComponent
import com.example.anthonyliberatore.doordashlite.app.di.screen.ScreenModule

open class BaseActivity : AppCompatActivity() {

  val screenComponent by lazy {
    getApplicationComponent().plus(ScreenModule(this))
  }

  private fun getApplicationComponent(): ApplicationComponent {
    return (application as BaseApplication).component
  }
}