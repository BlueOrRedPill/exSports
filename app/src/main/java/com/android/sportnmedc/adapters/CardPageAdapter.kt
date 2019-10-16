package com.android.sportnmedc.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.ui.fragments.BackCardFragmentEx
import com.android.sportnmedc.ui.fragments.FontCardFragmentEx
import com.orhanobut.logger.Logger

class CardPageAdapter(fm: FragmentManager, val cardModel:CardModel): FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return   if (position == 1){
            BackCardFragmentEx(cardModel)
        }else{
            FontCardFragmentEx(cardModel)
        }
    }
}
