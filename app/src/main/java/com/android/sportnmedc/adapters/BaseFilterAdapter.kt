package com.android.sportnmedc.adapters

import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView


abstract class BaseFilterAdapter<T, S : RecyclerView.ViewHolder> : RecyclerView.Adapter<S>(), Filterable {

    val originalArrayList: ArrayList<T> = ArrayList()
    var mArrayList: ArrayList<T> = ArrayList()

    protected var onItemClicked: BaseRecyclerAdapter.OnItemClicked?= null
    private var recyclerView: RecyclerView? = null

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                mArrayList.clear()

                if (TextUtils.isEmpty(charSequence)) {
                    mArrayList.addAll(originalArrayList)
                } else {
                    val filteredList = ArrayList<T>()
                    for (mObject in originalArrayList) {
                        if ( filterObject(mObject, charSequence.toString())){
                            filteredList.add(mObject)
                        }
                    }
                    mArrayList = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = mArrayList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                Handler().postDelayed( {
                    if (recyclerView != null) {
                        recyclerView!!.recycledViewPool.clear()
                    }
                    notifyDataSetChanged()
                }, 100)
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    fun addItem(`object`: T) {
        originalArrayList.add(`object`)
        notifyDataSetChanged()
    }

    fun setItems(arrayList: ArrayList<T>) {
        this.originalArrayList.clear()
        this.originalArrayList.addAll(arrayList)
        this.mArrayList.clear()
        this.mArrayList.addAll(arrayList)
        notifyDataSetChanged()
    }

    fun addItems(arrayList: ArrayList<T>) {
        this.originalArrayList.addAll(arrayList)
        this.mArrayList.addAll(arrayList)
        notifyDataSetChanged()
    }

    fun getListItem(position: Int): T? {
        return if (position > mArrayList.size) {
            null
        } else mArrayList[position]
    }



    override fun getItemCount(): Int {
        return mArrayList.size
    }

    fun setOnItemClick(onItemClicked: BaseRecyclerAdapter.OnItemClicked) {
        this.onItemClicked = onItemClicked
    }

    interface OnItemClicked {
        fun onItemClicked(v: View, position: Int)
    }

    protected abstract fun filterObject( mObject: T, searchText: String):Boolean

}