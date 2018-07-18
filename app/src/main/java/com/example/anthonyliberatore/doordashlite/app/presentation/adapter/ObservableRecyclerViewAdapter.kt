package com.example.anthonyliberatore.doordashlite.app.presentation.adapter

import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import android.support.v7.widget.RecyclerView

/**
 * ObservableRecyclerViewAdapter is used to allow changes to ObservableList to update the RecyclerView
 */
abstract class ObservableRecyclerViewAdapter<T, Holder: RecyclerView.ViewHolder>(
    private val items: ObservableList<T>) : RecyclerView.Adapter<Holder>() {

  private var onItemClickListener:OnItemClickListener? = null

  init {
    //notifyDataSetChanged is used for simplicity here. Optimize by calling specific method
    items.addOnListChangedCallback(object: OnListChangedCallback<ObservableList<T>>() {
      override fun onChanged(sender: ObservableList<T>?) {
        notifyDataSetChanged()
      }

      override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyDataSetChanged()
      }

      override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
        notifyDataSetChanged()
      }

      override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyDataSetChanged()
      }

      override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyDataSetChanged()
      }
    })
  }

  override fun getItemCount(): Int = items.size

  fun getItem(i: Int): T {
    return items[i]
  }

  fun getItems(): ObservableList<T> {
    return items
  }

  fun getOnItemClickListener(): OnItemClickListener? {
    return onItemClickListener
  }

  fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
    this.onItemClickListener = onItemClickListener
  }

  interface OnItemClickListener {
    fun onItemClicked(item: Any)
  }
}