package com.android.sportnmedc

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sportnmedc.adapters.RedemptionAdapter
import com.android.sportnmedc.adapters.RedemptionPagerAdapter
import com.android.sportnmedc.exdata.redemptions.Products
import com.android.sportnmedc.helpers.FadePageTransformer
import com.android.sportnmedc.helpers.ToolbarHelper
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_redemption_center.*

class RedemptionCenterActivityEx: ExBaseActivity(R.layout.activity_redemption_center),ToolbarHelper{


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Redemption Center"

    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            return Intent(mActivity,RedemptionCenterActivityEx::class.java)

        }
    }
    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        viewpager.adapter = RedemptionPagerAdapter(mActivity)
        viewpager.setPageTransformer(true,FadePageTransformer(viewpager))
        viewpager.pageMargin = 24
        tab_layout.addTab(tab_layout.newTab().setCustomView(R.layout.item_custom_tab).setIcon(R.drawable.ic_tab_ex).setText("Ex Sport"))
        tab_layout.addTab(tab_layout.newTab().setCustomView(R.layout.item_custom_tab).setIcon(R.drawable.ic_tab_entertainment).setText("Entertainment"))
        tab_layout.addTab(tab_layout.newTab().setCustomView(R.layout.item_custom_tab).setIcon(R.drawable.ic_tab_merchandising).setText("Merchandising"))
        tab_layout.addTab(tab_layout.newTab().setCustomView(R.layout.item_custom_tab).setIcon(R.drawable.ic_tab_shopping).setText("Shopping"))
        tab_layout.addTab(tab_layout.newTab().setCustomView(R.layout.item_custom_tab).setIcon(R.drawable.ic_tab_food).setText("Food"))
        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
//                tab_indicator.getTabAt(p0?.position?:0)?.setIcon(R.drawable.ic_triangle)?.select()
                updateData()
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                p0?.getIcon()?.setColorFilter(ActivityCompat.getColor(mActivity,android.R.color.white), PorterDuff.Mode.SRC_IN)
//                tab_indicator.getTabAt(p0?.position?:0)?.setIcon(null)
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.getIcon()?.setColorFilter(ActivityCompat.getColor(mActivity,R.color.red_indicator), PorterDuff.Mode.SRC_IN)
//                tab_indicator.getTabAt(p0?.position?:0)?.setIcon(R.drawable.ic_triangle)?.select()
                updateData()
            }

        })
        tab_layout.getTabAt(0)?.icon?.setColorFilter(ActivityCompat.getColor(mActivity,R.color.red_indicator), PorterDuff.Mode.SRC_IN)
        tab_layout.getTabAt(0)?.select()
        tab_layout.viewTreeObserver.addOnScrollChangedListener {
            //            tab_indicator?.scrollX  = tab_layout.scrollX
        }
        reward_system.setOnClickListener {
            startActivity(RewardSystemActivityEx.newIntent(mActivity))
        }
    }

    private fun updateData(){
        val mLibraryAdapter =  RedemptionAdapter(mActivity)
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.adapter = mLibraryAdapter
        mLibraryAdapter.addData(Products("Nike Horse RaceMask",R.drawable.ex_redem_horse,100))
        mLibraryAdapter.addData(Products("Adidas Messi 15.2 FG",R.drawable.ex_redem_boot,200))
        mLibraryAdapter.addData(Products("Nike Volleyball Shirt",R.drawable.ex_redem_shirt,50))
    }




}