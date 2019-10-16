package com.android.sportnmedc.helpers

import android.view.View
import androidx.viewpager.widget.ViewPager

class FlipPageViewTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val percentage = 1 - Math.abs(position)
        page.setCameraDistance(12000f)
        setVisibility(page, position)
        setTranslation(page)
        setSize(page, position, percentage)
        setRotation(page, position, percentage)
    }

    private fun setVisibility(page: View, position: Float) {
        if (position < 0.5 && position > -0.5) {
            page.visibility = View.VISIBLE
        } else {
            page.visibility = View.INVISIBLE
        }
    }

    private fun setTranslation(page: View) {
        val viewPager = page.getParent() as ViewPager
        val scroll = viewPager.scrollX - page.getLeft()
        page.translationX = scroll.toFloat()
    }

    private fun setSize(page: View, position: Float, percentage: Float) {
        page.setScaleX(if (position != 0f && position != 1f) percentage else 1f)
        page.setScaleY(if (position != 0f && position != 1f) percentage else 1f)
    }

    private fun setRotation(page: View, position: Float, percentage: Float) {
        if (position > 0) {
            page.setRotationY(-180 * (percentage + 1))
        } else {
            page.setRotationY(180 * (percentage + 1))
        }
    }
}