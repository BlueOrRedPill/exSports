package com.android.sportnmedc

import android.content.Intent
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.helpers.GlideApp
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.network.model.PaymentModel
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.ui.fragments.*
import com.android.sportnmedc.viewmodels.HomeActivityViewModel
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_left_profile.*
import kotlinx.android.synthetic.main.layout_right_notification.*

class HomeActivityEx:BaseActivity<HomeActivityViewModel>(R.layout.activity_home),ToolbarHelper{
    override fun initViewModel(): HomeActivityViewModel {
        return  ViewModelProviders.of(this).get(HomeActivityViewModel::class.java).also {
            it.balanceOf().observe(this, mSuccess)
//            it.user().observe(this, mSuccess)
//            it.loadingIndicator().observe(this, mLoading)
//            it.error().observe(this, mError)
        }
    }

    override fun hasBackMenu(): Boolean =false

    var profile = UserModel.fromJson(BaseApplication.prefs.profile)

    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            val intent = Intent(mActivity,HomeActivityEx::class.java)
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {
        privacy_btn.setOnClickListener {
            startActivity(TextActivityEx.newIntent(mActivity,"Privacy Policy",true))
        }
        term_btn.setOnClickListener {
            startActivity(TextActivityEx.newIntent(mActivity,"Terms and Condition",true))
        }
        change_pass_btn.setOnClickListener {
            startActivity(TextActivityEx.newIntent(mActivity,"Change Password",false))
        }
        left_slide_menu.setPadding(0,getStatusBarHeight(mActivity),0,0)
        right_slide_menu.setPadding(0,getStatusBarHeight(mActivity),0,0)
        mToolbar?.leftMenuImageEvent(R.drawable.ic_hamberger, View.OnClickListener {
            if (drawer_layout.isDrawerOpen(left_slide_menu)){
                drawer_layout.closeDrawer(left_slide_menu)
            }else {
                drawer_layout.openDrawer(left_slide_menu)
            }
        })
        mToolbar?.rightMenuImageEvent(R.drawable.ic_notification,View.OnClickListener {
            if (drawer_layout.isDrawerOpen(right_slide_menu)){
                drawer_layout.closeDrawer(right_slide_menu)
            }else{
                drawer_layout.openDrawer(right_slide_menu)
            }
        })
        buy_token_btn.setOnClickListener { startActivity(PaymentActivityEx.newIntent(mActivity)) }

        noti_message_1.text = Html.fromHtml("<b>Gustavo</b> want to trade your card.")
        noti_message_2.text = Html.fromHtml("<b>Robasta</b> confirm your trade.")
        noti_message_3.text = Html.fromHtml("<b>News/Promotion</b> available now.")
        noti_message_4.text = Html.fromHtml("<b>Buakaw Bunchamek</b> out of stock.")
        noti_message_5.text = Html.fromHtml("<b>Boyer Stephen</b> out of stock.")

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(left_slide_menu)){
            drawer_layout.closeDrawer(left_slide_menu)
        }else if (drawer_layout.isDrawerOpen(right_slide_menu)){
            drawer_layout.closeDrawer(right_slide_menu)
        }else{
            super.onBackPressed()
        }

    }

    override fun onActivityCreated() {
        setupToolbar()
        tab_home.getTabAt(1)?.icon?.alpha = 128
        tab_home.getTabAt(2)?.icon?.alpha = 128
        tab_home.getTabAt(3)?.icon?.alpha = 128
        tab_home.getTabAt(4)?.icon?.alpha = 128
        tab_home.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
                p0?.icon?.alpha = 255
                p0?.let {
                    selectFragment(it.position)
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                p0?.icon?.alpha = 128
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.icon?.alpha = 255
                p0?.let {
                    selectFragment(it.position)
                }
            }


        })
        tab_home.getTabAt(0)?.select()
        profile.let {
            GlideApp.with(this).load(it.image).placeholder(R.drawable.ic_menu_profile).into(profile_image)
            name_txt.text = it.name
            email_txt.text = it.bio
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.bcBalanceOf()
    }

    private fun selectFragment(index:Int){
        var mFragment: Fragment?= null
        when(index){
            0 -> {
                mToolbar?.setToolbarTitle("HOME")
                mFragment = HomeFragment()
            }
            1 -> {
                mToolbar?.setToolbarTitle("LIBRARY")
                mFragment = LibraryFragmentEx()
            }
            2 -> {
                mToolbar?.setToolbarTitle("MARKETS")
                mFragment = MarketFragmentEx()

            }
            3 -> {
                mToolbar?.setToolbarTitle("TRADE")
                mFragment = TradeFragmentEx()
            }
            4 -> {
                mToolbar?.setToolbarTitle("WALLETS")
                mFragment = WalletFragmentEx()
            }
        }
        mFragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.home_content,it).commitAllowingStateLoss()
        }


    }

    private val mSuccess = Observer<PaymentModel> {
        balance_coin.text = it.exsBalance.toString()
        mToolbar?.rightMenuTextEvent(it.exsBalance.toString(),View.OnClickListener {

        })
    }
}