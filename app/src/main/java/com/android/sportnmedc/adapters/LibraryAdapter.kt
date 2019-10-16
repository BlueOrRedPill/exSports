package com.android.sportnmedc.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.libraries.LibraryModel
import java.util.*


/**
 * Created by AndroidEB on 10/9/2017.
 */
class LibraryAdapter(mActivity:AppCompatActivity):BaseRecyclerAdapter(mActivity){



    private var mTotalPage = 1
    private var mDataList:ArrayList<LibraryModel?> = ArrayList()

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun getItem(postion:Int):LibraryModel?{
        return if (postion < mDataList.size) mDataList[postion] else null
    }

    fun clear(){
        mDataList.clear()
        notifyDataSetChanged()
    }
    fun addData(mData:LibraryModel?) {
        mDataList.add(mData)
        notifyItemInserted(mDataList.size - 1)
    }

    fun addDataAll(mDataList: List<LibraryModel>, mTotalPage: Int) {
        this.mTotalPage = mTotalPage
        val size = this.mDataList.size
        this.mDataList.addAll(mDataList)
        notifyItemRangeInserted(size, mDataList.size)

    }

    fun startLoading(mLayoutManager: RecyclerView.LayoutManager, currentPage: Int) {
        var currentPage = currentPage
        val layout = (mLayoutManager as? GridLayoutManager)?:mLayoutManager as LinearLayoutManager
        if (layout.itemCount > 0 && currentPage < mTotalPage) {
            val visibleThreshold = 2
            val totalItemCount = layout.itemCount
            val lastVisibleItem = layout.findLastVisibleItemPosition()
            if (!isLoading() && totalItemCount <= lastVisibleItem + visibleThreshold) {
                Log.e("page", "" + currentPage)
                setLoaded(true)
                if (mOnLoadMoreListener != null) {
                    addData(null)
                    currentPage++
                    mOnLoadMoreListener?.onLoadMore(currentPage)
                }
            }
        }
    }

    fun removeLoadingItem() {
        if (mDataList.size > 0) {
            if (mDataList.last() == null) {
                mDataList.removeAt(mDataList.lastIndex)
            }
        }
        setLoaded(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_library, parent, false))
        } else super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemHolder -> {
                mDataList.get(position)?.let {
                            holder.updateContent(it)
                }

            }
            else -> super.onBindViewHolder(holder, position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (mDataList[position]== null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }



    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var coverImg:ImageView = itemView.findViewById(R.id.cover_img)
        var nameTxt:TextView = itemView.findViewById(R.id.name_txt)
        var percentAllTxt:TextView = itemView.findViewById(R.id.percent_all_txt)
        var currentPercentTxt:TextView = itemView.findViewById(R.id.current_percent_txt)
        var progressPercent:ProgressBar= itemView.findViewById(R.id.percent_progress)

        init {
            itemView.setOnClickListener {
                onItemClicked?.onItemClicked(it,adapterPosition)

            }
        }


         fun updateContent(mLibrary:LibraryModel){
            coverImg.setImageResource(mLibrary.imageCover)
            nameTxt.text = mLibrary.name
            percentAllTxt.text = "${mLibrary.currentCount}/${mLibrary.maxCount} complete"
            var currentPercent =  (mLibrary.currentCount * 100) / mLibrary.maxCount
            currentPercentTxt.text = "${currentPercent}%"
            progressPercent.max = 100
            progressPercent.progress = currentPercent
        }
    }



}