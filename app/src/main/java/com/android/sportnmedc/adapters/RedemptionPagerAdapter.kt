package com.android.sportnmedc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter


/**
 * Created by AndroidEB on 10/9/2017.
 */
class RedemptionPagerAdapter(val mActivity:AppCompatActivity): PagerAdapter(){


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(mActivity).inflate(com.android.sportnmedc.R.layout.item_redemption_pager, container, false) as ViewGroup
        container.addView(layout)
        return layout
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return 5
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}