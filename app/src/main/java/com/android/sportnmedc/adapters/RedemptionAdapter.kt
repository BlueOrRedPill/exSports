package com.android.sportnmedc.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.redemptions.Products
import java.util.*


/**
 * Created by AndroidEB on 10/9/2017.
 */
class RedemptionAdapter(mActivity:AppCompatActivity):BaseRecyclerAdapter(mActivity){



    private var mTotalPage = 1
    private var mDataList:ArrayList<Products?> = ArrayList()

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun getItem(postion:Int):Products?{
        return if (postion < mDataList.size) mDataList[postion] else null
    }

    fun clear(){
        mDataList.clear()
        notifyDataSetChanged()
    }
    fun addData(mData:Products?) {
        mDataList.add(mData)
        notifyItemInserted(mDataList.size - 1)
    }

    fun addDataAll(mDataList: List<Products>, mTotalPage: Int) {
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
            ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_redemption_center, parent, false))
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
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)

        init {
            itemView.setOnClickListener {
                mDataList.get(adapterPosition)?.let {
//                    mActivity.startActivity(LibraryDetailActivityEx.newIntent(mActivity,it))
                }

            }
        }


        fun updateContent(mData:Products){
            productName.text = mData.name
            productImage.setImageResource(mData.productImage)
            productPrice.text ="${ mData.price} EXS"
        }
    }



}