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
import com.android.sportnmedc.exdata.TransactionModel
import java.util.*


/**
 * Created by AndroidEB on 10/9/2017.
 */
class TransactionAdapter(mActivity:AppCompatActivity):BaseRecyclerAdapter(mActivity){



    private var mTotalPage = 1
    private var mDataList:ArrayList<TransactionModel?> = ArrayList()

    init {
        mDataList.add(TransactionModel("#23608699",
            "1844abbec1ecceb7a78vf98956c01bf30714f31e4e7cf349e65cbf50de7",
            "Luca",R.drawable.ex_profile_image_list,"Cuin",70, "January 29th 2019, 4:38:50 am"))
        mDataList.add(TransactionModel("#23608680",
            "1844abbec1ecceb7a78vf98956c01bf30714f31e4e7cf349e65cbf50de7",
            "Cuin",R.drawable.ex_profile_image_list,"Leesu",40, "December 29th 2018, 4:38:50 am"))

        mDataList.add(TransactionModel("#23608699",
            "1844abbec1ecceb7a78vf98956c01bf30714f31e4e7cf349e65cbf50de7",
            "Leesu",R.drawable.ex_profile_image_list,"Eddy",20, "November 29th 2018, 4:38:50 am"))
        mDataList.add(TransactionModel("#23608699",
            "1844abbec1ecceb7a78vf98956c01bf30714f31e4e7cf349e65cbf50de7",
            "Eddy",R.drawable.ex_profile_image_list,"Issuer",10, "October 29th 2018, 4:38:50 am"))
    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun getItem(postion:Int):TransactionModel?{
        return if (postion < mDataList.size) mDataList[postion] else null
    }

    fun clear(){
        mDataList.clear()
        notifyDataSetChanged()
    }
    fun addData(mData:TransactionModel?) {
        mDataList.add(mData)
        notifyItemInserted(mDataList.size - 1)
    }

    fun addDataAll(mDataList: List<TransactionModel>, mTotalPage: Int) {
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
            ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false))
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
        var profileImg: ImageView = itemView.findViewById(R.id.profile_image)
        var nameTxt: TextView = itemView.findViewById(R.id.name_txt)
        var accuiredFrom: TextView = itemView.findViewById(R.id.accuired_from)
        var dateTxt: TextView = itemView.findViewById(R.id.date_txt)
        var transactionId: TextView = itemView.findViewById(R.id.transaction_id)
        var transactionCode: TextView = itemView.findViewById(R.id.transaction_code)
        var priceTxt: TextView = itemView.findViewById(R.id.price_txt)
        //        init {
//            itemView.setOnClickListener {
//                mDataList.get(adapterPosition)?.let {
//                    mActivity.startActivity(LibraryDetailActivityEx.newIntent(mActivity,it))
//                }
//
//            }
//        }
//
//
        fun updateContent(mData:TransactionModel){
            profileImg.setImageResource(mData.profileImage)
            nameTxt.text = mData.name
            accuiredFrom.text = "Acquired from a ${mData.fromName}."
            dateTxt.text = mData.date
            transactionId.text= mData.transactionId
            transactionCode.text = mData.transactionCode
            priceTxt.text = "${mData.price} EXS"
        }
    }



}