package com.android.sportnmedc.helpers

import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager




class FadePageTransformer(val mPager:ViewPager) : ViewPager.PageTransformer {
    private val MIN_SCALE = 0.65f
    private val MIN_ALPHA = 0.5f
    override fun transformPage(view: View, position: Float) {
//         Page is not an immediate sibling, just make transparent
        Log.e("alpha","position: $position")

        if(position <= -1.0F || position >= 1.0F) {
            view.setAlpha(MIN_ALPHA);
        } else if( position > 0.0f && position < 0.9f ) {
            view.setAlpha(1.0F);
        } else {
            // position is between -1.0F & 0.0F OR 0.0F & 1.0F
            val alpha = 1.0F - Math.abs(position)
            Log.e("alpha","alpha: $alpha")
            view.alpha =     if (alpha < MIN_ALPHA) MIN_ALPHA else alpha
        }


    }




}