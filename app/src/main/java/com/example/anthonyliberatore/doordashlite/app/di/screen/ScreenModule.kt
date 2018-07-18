package com.example.anthonyliberatore.doordashlite.app.di.screen

import com.example.anthonyliberatore.doordashlite.app.di.scope.PerScreen
import com.example.anthonyliberatore.doordashlite.app.presentation.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ScreenModule(val activity: BaseActivity) {

  @PerScreen
  @Provides
  fun providesActivity(): BaseActivity {
    return activity
  }

}