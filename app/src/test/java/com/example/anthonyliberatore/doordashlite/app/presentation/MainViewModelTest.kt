package com.example.anthonyliberatore.doordashlite.app.presentation

import com.example.anthonyliberatore.doordashlite.app.rx.RxJavaTestHooksResetRule
import com.example.anthonyliberatore.doordashlite.domain.model.Restaurant
import com.example.anthonyliberatore.doordashlite.domain.usecase.GetRestaurantListUseCase
import com.example.anthonyliberatore.doordashlite.domain.usecase.GetRestaurantListUseCase.Result
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
  @get:Rule
  var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

  @Mock
  lateinit var getRestaurantListUseCase: GetRestaurantListUseCase
  private lateinit var sut: MainViewModel

  @Before
  fun setUp() {
    sut = MainViewModel(getRestaurantListUseCase)
  }

  @Test
  fun `bound retrieves the restaurant list`() {
    given(getRestaurantListUseCase.execute(any(), any(), any(), any()))
        .willReturn(Observable.just(mock()))

    sut.bound()

    verify(getRestaurantListUseCase).execute(any(), any(), any(), any())
  }

  @Test
  fun `bound shows error when retrieving the restaurants fail`() {
    given(getRestaurantListUseCase.execute(any(), any(), any(), any()))
        .willReturn(Observable.just(Result.Failure(Throwable())))

    sut.bound()

    sut.showErrorGettingRestaurants.observe().test().assertValue(true)
  }

  @Test
  fun `bound adds restaurants to list when successful`() {
    val restaurants = arrayListOf<Restaurant>(mock(), mock())
    given(getRestaurantListUseCase.execute(any(), any(), any(), any()))
        .willReturn(Observable.just(Result.Success(restaurants)))

    sut.bound()

    assertThat(sut.restaurantList.size, equalTo(2))
  }

  @Test
  fun `onRestaurantClicked shows restaurant detail screen`() {
    val restaurantId = 1
    val restaurant = mock<Restaurant> { on { id } doReturn restaurantId }
    sut.onRestaurantClicked(restaurant)

    sut.showRestaurantDetail.observe().test().assertValue(restaurantId)
  }
}