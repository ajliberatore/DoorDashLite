package com.example.anthonyliberatore.doordashlite.app.presentation

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.example.anthonyliberatore.doordashlite.app.ext.addTo
import com.example.anthonyliberatore.doordashlite.app.rx.StickyAction
import com.example.anthonyliberatore.doordashlite.domain.model.Restaurant
import com.example.anthonyliberatore.doordashlite.domain.usecase.GetRestaurantListUseCase
import com.example.anthonyliberatore.doordashlite.domain.usecase.GetRestaurantListUseCase.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getRestaurantListUseCase: GetRestaurantListUseCase) {

  private val disposables = CompositeDisposable()
  val progressVisible = ObservableBoolean()
  val restaurantList = ObservableArrayList<Restaurant>()
  val showErrorGettingRestaurants = StickyAction<Boolean>()
  val showRestaurantDetail = StickyAction<Int>()

  companion object {
    const val LAT = 37.422740
    const val LNG = -122.139956
  }

  // Called onCreate. Retrieves the list of restaurants
  fun bound() {
    getRestaurantListUseCase.execute(LAT, LNG, 0, 50)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { handleGetRestaurantListResult(it) }
        .addTo(disposables)
  }

  // Called onDestroy. Clean up method.
  fun unbound() {
    disposables.clear()
  }

  // Handles Result from getRestaurantListUseCase
  fun handleGetRestaurantListResult(result: Result) {
    progressVisible.set(result == Result.Loading)
    when (result) {
      is Result.Success -> {
        restaurantList.addAll(result.restaurants)
      }
      is Result.Failure -> {
        showErrorGettingRestaurants.trigger(true)
      }
    }
  }

  // Shows restaurant detail screen based on restaurant clicked
  fun onRestaurantClicked(restaurant: Any) {
    showRestaurantDetail.trigger((restaurant as Restaurant).id)
  }
}
