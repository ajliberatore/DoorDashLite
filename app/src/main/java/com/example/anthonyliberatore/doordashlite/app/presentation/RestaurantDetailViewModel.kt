package com.example.anthonyliberatore.doordashlite.app.presentation

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.anthonyliberatore.doordashlite.app.ext.addTo
import com.example.anthonyliberatore.doordashlite.app.rx.StickyAction
import com.example.anthonyliberatore.doordashlite.domain.usecase.GetRestaurantUseCase
import com.example.anthonyliberatore.doordashlite.domain.usecase.GetRestaurantUseCase.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RestaurantDetailViewModel @Inject constructor(private val getRestaurantUseCase: GetRestaurantUseCase) {

  private val disposables = CompositeDisposable()
  val progressVisible = ObservableBoolean()
  val showErrorGettingRestaurantDetails = StickyAction<Boolean>()
  var restaurantName = ObservableField<String>()
  var restaurantDescription = ObservableField<String>()
  var restaurantStatus = ObservableField<String>()
  var restaurantImage = ObservableField<String>()

  // Called onCreate. Retrieves the restaurant details
  fun bound(id: Int) {
    getRestaurantUseCase.execute(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { handleGetRestaurantListResult(it) }
        .addTo(disposables)
  }

  // Called onDestroy. Clean up method
  fun unbound() {
    disposables.clear()
  }

  // Handles Result from getRestaurantUseCase
  private fun handleGetRestaurantListResult(result: Result) {
    progressVisible.set(result == Result.Loading)
    when (result) {
      is Result.Success -> {
        restaurantName.set(result.restaurant.name)
        restaurantDescription.set(result.restaurant.description)
        restaurantStatus.set(result.restaurant.status)
        restaurantImage.set(result.restaurant.coverImgUrl)
      }
      is Result.Failure -> {
        showErrorGettingRestaurantDetails.trigger(true)
      }
    }
  }
}
