package com.android.sportnmedc.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.android.sportnmedc.R


/**
 * Created by AndroidEB on 10/9/2017.
 */
class SelectItemPagerAdapter(val mActivity:AppCompatActivity): PagerAdapter(){


    var mDataList:ArrayList<Int> = ArrayList()
    var currentPage = 0

    var mSelectList:ArrayList<Int> = ArrayList()
    var mOnCountUpdate:OnCountUpdate ?=null


    fun addAllItem(mDataList:ArrayList<Int>,currentPage :Int){
        this.mDataList = mDataList
        this.currentPage = currentPage
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.e("ViewPager","instantiateItem")
        val layout = LayoutInflater.from(mActivity).inflate(R.layout.item_select_item, container, false)
        val image: ImageView = layout.findViewById(R.id.image)
        val checkCard: ImageView = layout.findViewById(R.id.check_card)
        if (mSelectList.contains(position)){
            checkCard.setImageResource( R.drawable.img_check_card_active)
        }
        layout.findViewById<View>(R.id.content_view).setOnClickListener {
            val isCheck = !mSelectList.contains(position)
            if (isCheck){
                mSelectList.add(position)
            }else{
                mSelectList.remove(position)
            }
           checkCard .setImageResource(if (mSelectList.contains(position)) R.drawable.img_check_card_active else R.drawable.img_check_card)
            mOnCountUpdate?.onUpdate( mSelectList)
        }
        image.setImageResource(mDataList[position])
        container.addView(layout, position)
        return   layout

    }

    fun setOnUpdateCount(mOnCountUpdate:OnCountUpdate){
        this.mOnCountUpdate = mOnCountUpdate
    }

     interface OnCountUpdate{
        fun onUpdate(mList:ArrayList<Int>)
    }



    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return mDataList.size
    }

    override fun getPageWidth(position: Int): Float {
        return 0.7f
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}