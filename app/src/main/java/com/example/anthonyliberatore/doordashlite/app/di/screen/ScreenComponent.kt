package com.example.anthonyliberatore.doordashlite.app.di.screen

import com.example.anthonyliberatore.doordashlite.app.di.scope.PerScreen
import com.example.anthonyliberatore.doordashlite.app.presentation.MainActivity
import com.example.anthonyliberatore.doordashlite.app.presentation.RestaurantDetailActivity
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = arrayOf(ScreenModule::class))
interface ScreenComponent {

  fun inject(mainActivity: MainActivity)

  fun inject(restaurantDetailActivity: RestaurantDetailActivity)
}
