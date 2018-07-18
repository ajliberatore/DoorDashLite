package com.example.anthonyliberatore.doordashlite.app.di.application

import com.example.anthonyliberatore.doordashlite.data.DoorDashEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DoorDashApiModule {

  @Provides
  @Singleton
  fun provideDoorDashEndpoint(retrofit: Retrofit): DoorDashEndpoint {
    return retrofit
        .create(DoorDashEndpoint::class.java)
  }
}
