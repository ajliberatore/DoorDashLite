package com.example.anthonyliberatore.doordashlite.app.presentation.adapter

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.anthonyliberatore.doordashlite.app.presentation.adapter.RestaurantListAdapter.Holder
import com.example.anthonyliberatore.doordashlite.databinding.ListItemRestaurantBinding
import com.example.anthonyliberatore.doordashlite.domain.model.Restaurant
import com.squareup.picasso.Picasso

/**
 * RestaurantListAdapter is used to display data for each restaurant in the list
 */
class RestaurantListAdapter(restaurants: ObservableList<Restaurant>) :
    ObservableRecyclerViewAdapter<Restaurant, Holder>(restaurants) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    return Holder(
        ListItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        getOnItemClickListener())
  }

  override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bind(getItem(position))
  }

  class Holder(
      private val binding: ListItemRestaurantBinding,
      private val onItemClickListener: OnItemClickListener?) :
      RecyclerView.ViewHolder(binding.root)/*, View.OnClickListener*/ {

    private lateinit var restaurant: Restaurant

    fun bind(restaurant: Restaurant) {
      this.restaurant = restaurant

      Picasso.get().load(restaurant.coverImgUrl).into(binding.image)
      binding.name.text = restaurant.name
      binding.description.text = restaurant.description
      binding.minutes.text = restaurant.status

      binding.root.setOnClickListener { onItemClickListener?.onItemClicked(restaurant) }
    }
  }
}