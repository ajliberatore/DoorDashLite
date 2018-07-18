package com.example.anthonyliberatore.doordashlite.data

import com.example.anthonyliberatore.doordashlite.data.response.RestaurantResponse
import io.reactivex.Single
import javax.inject.Inject

class DoorDashApi @Inject constructor(private val doorDashEndpoint: DoorDashEndpoint) {

  fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<RestaurantResponse>> {
    return doorDashEndpoint.getRestaurantList(lat, lng, offset, limit)
  }

  fun getRestaurant(id: Int): Single<RestaurantResponse> {
    return doorDashEndpoint.getRestaurant(id)
  }
}
