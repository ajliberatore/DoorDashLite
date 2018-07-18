package com.example.anthonyliberatore.doordashlite.app.di.application

import com.example.anthonyliberatore.doordashlite.data.DoorDashApi
import com.example.anthonyliberatore.doordashlite.data.repository.RestaurantRepositoryImpl
import com.example.anthonyliberatore.doordashlite.domain.mapper.RestaurantMapper
import com.example.anthonyliberatore.doordashlite.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides
  @Singleton
  fun provideRestaurantRepository(doorDashApi: DoorDashApi, restaurantMapper: RestaurantMapper): RestaurantRepository {
    return RestaurantRepositoryImpl(doorDashApi, restaurantMapper)
  }

}