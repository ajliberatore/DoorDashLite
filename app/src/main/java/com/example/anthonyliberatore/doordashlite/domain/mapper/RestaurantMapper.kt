package com.example.anthonyliberatore.doordashlite.domain.mapper

import com.example.anthonyliberatore.doordashlite.data.response.RestaurantResponse
import com.example.anthonyliberatore.doordashlite.domain.model.Restaurant
import javax.inject.Inject

/**
 * RestaurantMapper maps the restaurant response objects to the restaurant model object
 */
class RestaurantMapper @Inject constructor() {

  fun map(responseList: List<RestaurantResponse>): List<Restaurant> {
    val result = ArrayList<Restaurant>()

    responseList.forEach {
      result.add(map(it))
    }

    return result
  }

  fun map(response: RestaurantResponse): Restaurant {
    return Restaurant(
        id = response.id,
        name = response.name,
        description = response.description,
        coverImgUrl = response.coverImgUrl,
        status = response.status,
        deliveryFee = response.deliveryFee
    )
  }
}
