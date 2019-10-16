package com.android.sportnmedc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.cards.CardModel


/**
 * Created by AndroidEB on 10/9/2017.
 */
class CardAdapter(mActivity:AppCompatActivity):BaseFilterAdapter<CardModel, CardAdapter.ItemHolder>(){


    override fun filterObject( mObject: CardModel, searchText: String): Boolean {
        return mObject.name.toLowerCase().startsWith(searchText.toLowerCase())
    }


//    private var mTotalPage = 1
//    private var mDataList:ArrayList<CardModel?> = ArrayList()

//    override fun getItemCount(): Int {
//        return mDataList.size
//    }
//
//    fun getItem(position:Int):CardModel?{
//        return if (position < mDataList.size) mDataList[position] else null
//    }
//
//    fun clear(){
//        mDataList.clear()
//        notifyDataSetChanged()
//    }
//
//    fun addData(mData:CardModel?) {
//        mDataList.add(mData)
//        notifyItemInserted(mDataList.size - 1)
//    }
//
//    fun addDataAll(mDataList: List<CardModel>, mTotalPage: Int) {
//        this.mTotalPage = mTotalPage
//        val size = this.mDataList.size
//        this.mDataList.addAll(mDataList)
//        notifyItemRangeInserted(size, mDataList.size)
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        mArrayList[position]?.let {
            holder.updateContent(it)
        }
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImg:ImageView = itemView.findViewById(R.id.img_card_buy)
        val cardAdd:ImageView = itemView.findViewById(R.id.card_add)



        init {
            cardAdd.setOnClickListener {
                onItemClicked?.onItemClicked(it,adapterPosition)
            }
            cardImg.setOnClickListener {
                onItemClicked?.onItemClicked(it,adapterPosition)
            }
        }
         fun updateContent(mData:CardModel){
            cardImg.setImageResource(mData.image)

        }


    }

}