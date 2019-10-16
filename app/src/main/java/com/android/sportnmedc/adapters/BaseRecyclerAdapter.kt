package com.android.sportnmedc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.sportnmedc.R

/**
 * Created by AndroidEB on 2/1/2018.
 */
abstract class BaseRecyclerAdapter(val mActivity: AppCompatActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    protected var mOnLoadMoreListener: OnLoadMoreListener?=null
    private var isLoading: Boolean = false
    val VIEW_TYPE_ITEM = 0
    val VIEW_TYPE_LOADING = 1

    protected  var onItemClicked: OnItemClicked ?= null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_LOADING) {
            val view = LayoutInflater.from(mActivity).inflate(R.layout.progress_layout, parent, false)
            return ProgressViewHolder(view)
        }
        return super.createViewHolder(parent,viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.takeIf { it is ProgressViewHolder }?.let { it as ProgressViewHolder }?.apply { progressBar.isIndeterminate = true }
    }


    internal fun isLoading(): Boolean {
        return isLoading
    }

    internal fun setLoaded(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    private inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar = itemView.findViewById(R.id.progressBar) as ProgressBar
    }

    fun setOnItemClick(onItemClicked: OnItemClicked) {
        this.onItemClicked = onItemClicked
    }

    interface OnLoadMoreListener {
        fun onLoadMore(page: Int)
    }

    interface OnItemClicked {
        fun onItemClicked(v: View, position: Int)
    }


    fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener
    }
}