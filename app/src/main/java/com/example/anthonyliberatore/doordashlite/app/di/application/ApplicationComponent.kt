package com.example.anthonyliberatore.doordashlite.app.di.application

import com.example.anthonyliberatore.doordashlite.app.BaseApplication
import com.example.anthonyliberatore.doordashlite.app.di.screen.ScreenComponent
import com.example.anthonyliberatore.doordashlite.app.di.screen.ScreenModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, RepositoryModule::class, DoorDashApiModule::class))
interface ApplicationComponent {

  fun inject(activity: BaseApplication)

  fun plus(screenModule: ScreenModule): ScreenComponent
}
