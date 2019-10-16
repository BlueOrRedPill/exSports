package com.android.sportnmedc.helpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.R
import com.google.android.material.appbar.AppBarLayout

/**
 * Created by AndroidEB on 10/6/2017.
 */
interface ToolbarHelper{

    var mToolbar:Toolbar?

    fun hasBackMenu():Boolean = true
    //    fun toolbarColor():Int = android.R.color.transparent
    fun toolbarTitle():String? = ""

    fun initMenuEvent(mToolbar:Toolbar?)

    fun Toolbar.leftMenuImageEvent(menuImage:Int,onClickListener: View.OnClickListener){
        val toolbarLeftMenuImage:ImageView? = findViewById(R.id.left_menu)
        toolbarLeftMenuImage?.visibility = View.VISIBLE
        toolbarLeftMenuImage?.setImageResource(menuImage)
        toolbarLeftMenuImage?.setOnClickListener(onClickListener)
    }

    fun Toolbar.rightMenuImageEvent(menuImage:Int,onClickListener: View.OnClickListener){
        val toolbarRightMenuImage:ImageView? = findViewById(R.id.toolbar_right_menu_image)
        toolbarRightMenuImage?.visibility = View.VISIBLE
        toolbarRightMenuImage?.setImageResource(menuImage)
        toolbarRightMenuImage?.setOnClickListener(onClickListener)
    }

    fun Toolbar.rightMenuTextEvent(text:String,onClickListener: View.OnClickListener){
        val toolbarRightMenuText:TextView? = findViewById(R.id.toolbar_right_menu_text)
        toolbarRightMenuText?.visibility = View.VISIBLE
        toolbarRightMenuText?.text = text
        toolbarRightMenuText?.setOnClickListener(onClickListener)
    }



    fun AppCompatActivity.setupToolbar( view: View?){
        view?.let {
            val mAppBar: AppBarLayout = it.findViewById(R.id.appbar)
            mToolbar = it.findViewById(R.id.toolbar)
            create(this, mAppBar, mToolbar)
            initMenuEvent(mToolbar)
        }
    }

    fun AppCompatActivity.setupToolbar(){
        val mAppBar: AppBarLayout = findViewById(R.id.appbar)
        mToolbar = findViewById(R.id.toolbar)
        create(this,mAppBar,mToolbar)
        initMenuEvent(mToolbar)
    }


    fun  Toolbar.setToolbarTitle(title:String?){
        val titleTxt:TextView? =  findViewById(R.id.toolbar_title)
        titleTxt?.text = title
    }
    fun  Toolbar.setToolbarSubTitle(subtitle:String?){
        val subTitleTxt:TextView? =  findViewById(R.id.toolbar_subtitle)
        subTitleTxt?.visibility = View.VISIBLE
        subTitleTxt?.text = subtitle
    }





    private fun create(mActivity: AppCompatActivity, mAppBar: AppBarLayout, mToolbar: Toolbar?){
        mToolbar?.setToolbarTitle(toolbarTitle())
        mActivity.setSupportActionBar(mToolbar)
        val leftMenuImg:ImageView? = mToolbar?.findViewById(R.id.left_menu)
        leftMenuImg?.visibility = if (hasBackMenu()) View.GONE else  View.VISIBLE
        val rightFakeView:View? = mToolbar?.findViewById(R.id.right_fake_view)
        rightFakeView?.visibility =  if (hasBackMenu()) View.VISIBLE else View.GONE

        mActivity.supportActionBar?.setDisplayHomeAsUpEnabled(hasBackMenu())
        mActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
        mAppBar.setPadding(0,getStatusBarHeight(mActivity),0,0)
        mToolbar?.setNavigationOnClickListener { mActivity.onBackPressed() }
    }


     fun getStatusBarHeight(mActivity: AppCompatActivity): Int {
        val resourceId = mActivity.resources.getIdentifier("status_bar_height", "dimen", "android")
        val height =  resourceId.takeIf { it > 0}?.let {mActivity.resources.getDimensionPixelSize(resourceId) } ?:0
        return height
    }
}