package com.example.anthonyliberatore.doordashlite.app.presentation

import com.example.anthonyliberatore.doordashlite.app.rx.RxJavaTestHooksResetRule
import com.example.anthonyliberatore.doordashlite.domain.model.Restaurant
import com.example.anthonyliberatore.doordashlite.domain.usecase.GetRestaurantUseCase
import com.example.anthonyliberatore.doordashlite.domain.usecase.GetRestaurantUseCase.Result
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestaurantDetailViewModelTest {
  @get:Rule
  var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

  @Mock lateinit var getRestaurantUseCase: GetRestaurantUseCase
  private lateinit var sut: RestaurantDetailViewModel

  @Before
  fun setUp() {
    sut = RestaurantDetailViewModel(getRestaurantUseCase)
  }

  @Test
  fun `bound retrieves the restaurant from the id`() {
    val id = 1
    given(getRestaurantUseCase.execute(id)).willReturn(Observable.just(mock()))

    sut.bound(id)

    verify(getRestaurantUseCase).execute(eq(id))
  }

  @Test
  fun `bound shows error when retrieving the restaurant fails`() {
    val id = 1
    given(getRestaurantUseCase.execute(id)).willReturn(Observable.just(Result.Failure(Throwable())))

    sut.bound(id)

    sut.showErrorGettingRestaurantDetails.observe().test().assertValue(true)
  }

  @Test
  fun `bound shows restaurant details when retrieving the restaurant`() {
    val id = 1
    val restaurant = Restaurant(
        id = 1,
        name = "name",
        description = "description",
        coverImgUrl = "coverImgUrl",
        status = "status",
        deliveryFee = 0
    )
    given(getRestaurantUseCase.execute(id)).willReturn(Observable.just(Result.Success(restaurant)))

    sut.bound(id)

    assertThat(sut.restaurantDescription.get(), equalTo(restaurant.description))
    assertThat(sut.restaurantName.get(), equalTo(restaurant.name))
    assertThat(sut.restaurantStatus.get(), equalTo(restaurant.status))
    assertThat(sut.restaurantImage.get(), equalTo(restaurant.coverImgUrl))
  }
}