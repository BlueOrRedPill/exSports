package com.android.sportnmedc.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sportnmedc.R
import com.android.sportnmedc.helpers.GlideApp
import com.android.sportnmedc.network.model.MarketModel
import java.util.*


/**
 * Created by AndroidEB on 10/9/2017.
 */
class MarketAdapter(mActivity:AppCompatActivity, val mType:TYPE):BaseRecyclerAdapter(mActivity){

    enum class TYPE{
        BUY,COLLECTION,ON_SALE
    }

    private var mTotalPage = 1
    private var mDataList:ArrayList<MarketModel?> = ArrayList()

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun getItem(postion:Int):MarketModel?{
        return if (postion < mDataList.size) mDataList[postion] else null
    }

    fun clear(){
        mDataList.clear()
        notifyDataSetChanged()
    }
    fun addData(mData:MarketModel?) {
        mDataList.add(mData)
        notifyItemInserted(mDataList.size - 1)
    }

    fun addDataAll(mDataList: List<MarketModel>, mTotalPage: Int) {
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
            ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fighter_card, parent, false))
        } else super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemHolder -> {
                mDataList.get(position)?.let {
                    holder.updateContent(it)
//                    if (mType == TYPE.BUY){
//                        holder.buyContent.visibility =View.VISIBLE
////                        holder.sale_text.visibility =View.GONE
//                        holder.setSoldCard(position%3 == 0)
//                    }else{
//
////                        holder.sale_text.visibility =  if (mType == TYPE.ON_SALE) View.VISIBLE else View.GONE
//                        holder.buyContent.visibility =View.GONE
//                    }

                }

            }
            else -> super.onBindViewHolder(holder, position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (mDataList[position]== null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }



    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImg:ImageView = itemView.findViewById(R.id.img_card_buy)
        val itemV:View = itemView
        val soldTxt:TextView = itemView.findViewById(R.id.sold_text)
        val priceContent:LinearLayout = itemView.findViewById(R.id.price_content)
        val soldBG:LinearLayout = itemView.findViewById(R.id.sold_bg)
        val buyContent:LinearLayout = itemView.findViewById(R.id.buy_content)
        val cardPrice:TextView = itemView.findViewById(R.id.card_price)
        val userName:TextView = itemView.findViewById(R.id.user_name)
        val userIcon = itemView.findViewById<ImageView>(R.id.user_icon)
        val count = itemView.findViewById<TextView>(R.id.count_card)

        init {
            itemView.setOnClickListener {
                onItemClicked?.onItemClicked(it,adapterPosition)
            }
        }
         fun updateContent(mData:MarketModel){
             cardPrice.text =  "${mData.perPrice} EXS"
             if(mData.ownerName == "ExSports") {
                 userIcon.visibility = View.GONE
                 count.text = mData.quantity.toString()
                 count.visibility = View.VISIBLE
             } else {
                 userIcon.visibility = View.VISIBLE
                 count.visibility = View.GONE
             }
             userName.text = mData.ownerName
             GlideApp.with(itemV).load("https://exsports.s3-ap-southeast-1.amazonaws.com/medias/images/carditems/" + mData.tokenId + ".png").into(cardImg)
//            cardImg.setImageResource(mData.cardImage)
//            cardPrice.text = "${mData.price} EXS"
//            userName.text = mData.userName
        }


        fun setSoldCard(isSold:Boolean){
            if (isSold){
                soldTxt.visibility =View.VISIBLE
                priceContent.visibility = View.INVISIBLE
                soldBG.visibility=View.VISIBLE
            }else{
                soldTxt.visibility =View.INVISIBLE
                priceContent.visibility = View.VISIBLE
                soldBG.visibility=View.GONE
            }

        }
    }

}