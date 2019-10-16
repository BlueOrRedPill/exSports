package com.android.sportnmedc.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sportnmedc.TradeDetailActivityEx
import com.android.sportnmedc.exdata.trades.TradeModel
import java.util.*
import kotlin.collections.ArrayList




/**
 * Created by AndroidEB on 10/9/2017.
 */
class TradeAdapter(mActivity:AppCompatActivity):BaseRecyclerAdapter(mActivity),Filterable{



    private var mTotalPage = 1
    private var mDataList:ArrayList<TradeModel?> = ArrayList()
    private var mDataListTemp:ArrayList<TradeModel?> = ArrayList()

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun getItem(postion:Int):TradeModel?{
        return if (postion < mDataList.size) mDataList[postion] else null
    }

    fun clear(){
        mDataListTemp.clear()
        mDataList.clear()
        notifyDataSetChanged()
    }
    fun addData(mData:TradeModel?) {
        mDataListTemp.add(mData)
        notifyItemInserted(mDataList.size - 1)
    }

    fun addDataAll(mDataList: List<TradeModel>, mTotalPage: Int) {
        this.mTotalPage = mTotalPage
//        val size = this.mDataList.size
        this.mDataListTemp.addAll(mDataList)
//        notifyItemRangeInserted(size, mDataList.size)

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

    private fun removeLoadingItem() {
        if (mDataList.size > 0) {
            if (mDataList.last() == null) {
                mDataList.removeAt(mDataList.lastIndex)
            }
        }
        setLoaded(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            ItemHolder(LayoutInflater.from(parent.context).inflate(com.android.sportnmedc.R.layout.item_trade, parent, false))
        } else super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemHolder -> {
                mDataList[position]?.let {
                    holder.updateContent(it)
                }

            }
            else -> super.onBindViewHolder(holder, position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (mDataList[position]== null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }
    override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                var filteredList = ArrayList<TradeModel?>()
                if (charString.isEmpty()) {
                    filteredList = mDataListTemp
                } else {
                    for (row in mDataListTemp) {
                        if (row?.exUser?.name?.toLowerCase()?.startsWith(charString.toLowerCase()) == true) {
                            filteredList.add(row)
                        }
                    }

//                    mDataList = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                mDataList.clear()
                filterResults?.let {
                    mDataList.addAll( it.values as ArrayList<TradeModel?>)
                    notifyDataSetChanged()
                }

            }

        }
    }



    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardNeed:List<ImageView> =Arrays.asList(
            itemView.findViewById(com.android.sportnmedc.R.id.card_need_1),
            itemView.findViewById(com.android.sportnmedc.R.id.card_need_2),
            itemView.findViewById(com.android.sportnmedc.R.id.card_need_3)

        )
        var cardGive:List<ImageView> =Arrays.asList(
            itemView.findViewById(com.android.sportnmedc.R.id.card_give_1),
            itemView.findViewById(com.android.sportnmedc.R.id.card_give_2),
            itemView.findViewById(com.android.sportnmedc.R.id.card_give_3)

        )
        var profileName:TextView = itemView.findViewById(com.android.sportnmedc.R.id.profile_name)
        var profileImage:ImageView = itemView.findViewById(com.android.sportnmedc.R.id.profile_image)
        var needCount:TextView = itemView.findViewById(com.android.sportnmedc.R.id.need_count)
        var giveCount:TextView = itemView.findViewById(com.android.sportnmedc.R.id.give_count)
        init {
            itemView.setOnClickListener {
                mDataList.get(adapterPosition)?.let {
                    mActivity.startActivity(TradeDetailActivityEx.newIntent(mActivity,it))
                }

            }
        }
        //
//
        fun updateContent(mData:TradeModel){
            cardNeed.forEachIndexed { index, imageView ->
                if (mData.cardIn.size > index){
                    imageView.visibility = View.VISIBLE
                    imageView.setImageResource(mData.cardIn[index])
                }else{
                    imageView.visibility = View.GONE
                }
            }
            cardGive.forEachIndexed { index, imageView ->
                if (mData.cardOut.size > index){
                    imageView.visibility = View.VISIBLE
                    imageView.setImageResource(mData.cardOut[index])
                }else{
                    imageView.visibility = View.GONE
                }
            }
            giveCount.text = "(${mData.cardOut.size})"
            needCount.text = "(${mData.cardIn.size})"
            mData.exUser.let {
                profileImage.setImageResource(it.imgProfile)
                profileName.text = it.name
            }

        }
    }



}