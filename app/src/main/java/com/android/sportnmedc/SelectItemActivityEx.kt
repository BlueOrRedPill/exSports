package com.android.sportnmedc

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.adapters.SelectItemPagerAdapter
import com.android.sportnmedc.exdata.trades.TradeModel
import com.android.sportnmedc.helpers.FadePageTransformer
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import kotlinx.android.synthetic.main.activity_select_item.*


class SelectItemActivityEx:ExBaseActivity(R.layout.activity_select_item),ToolbarHelper{


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? = "TRADE"

    private var imageListIn = arrayListOf(
        R.drawable.v01,
        R.drawable.m02,
        R.drawable.j03,
        R.drawable.v04,
        R.drawable.m05)

    private var imageListOut = arrayListOf(
        R.drawable.m01,
        R.drawable.j02,
        R.drawable.v03,
        R.drawable.m04,
        R.drawable.m05)

//    private var selectItemIn = true

    private lateinit var mTradeModel:TradeModel

    private var currentPage = 0


    private var itemSelectedIn:ArrayList<Int> = ArrayList()
    private var itemSelectedOut:ArrayList<Int> = ArrayList()
    companion object {
        fun newIntent(mActivity: AppCompatActivity,mTradeModel:TradeModel): Intent {
            val intent = Intent(mActivity,SelectItemActivityEx::class.java)
            intent.putExtra("tradeModel",mTradeModel)
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
//        selectItemIn = intent.getBooleanExtra("selectItemIn",true)
        mTradeModel = intent.getParcelableExtra("tradeModel")
//        viewpager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
//            override fun onPageScrollStateChanged(p0: Int) {
//
//            }
//
//            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
//
//            }
//
//            override fun onPageSelected(p0: Int) {
//                updateCurrentItem(p0)
//
//            }
//
//        })
        setupItemIn()
        setupItemOut()
        add_item.setOnClickListener {
            if (itemSelectedIn.size > 0  && itemSelectedOut.size > 0) {
                mTradeModel.cardIn =   imageListIn.filterIndexed { index, _ -> itemSelectedIn.contains(index)}  as ArrayList<Int>
                mTradeModel.cardOut = imageListOut.filterIndexed { index,_ -> itemSelectedOut.contains(index)}  as ArrayList<Int>
                startActivityForResult(
                    TradeConfirmActivityEx.newIntent(mActivity, mTradeModel),
                    Utils.REQUEST_CODE_TRADE
                )
            }else{
                Toast.makeText(mActivity,"Please select some card at least 1.",Toast.LENGTH_SHORT).show()
            }

        }



    }

    private fun setupItemIn(){
        mTradeModel.exUser.let {
            profile_image_in.setImageResource(it.imgProfile)
            user_name_in.text = it.name
        }
        val mAdapter =  SelectItemPagerAdapter(mActivity)
        viewpager_item_in.adapter  = mAdapter
        viewpager_item_in.setPageTransformer(true, FadePageTransformer(viewpager_item_in))
        viewpager_item_in.clipToPadding = false
        viewpager_item_in.offscreenPageLimit = 5
        mAdapter.addAllItem(imageListIn,0)
        viewpager_item_in.setCurrentItem(0,false)
        mAdapter.setOnUpdateCount(object:SelectItemPagerAdapter.OnCountUpdate{
            override fun onUpdate(mList: ArrayList<Int>) {
                itemSelectedIn.clear()
                itemSelectedIn.addAll(mList)
                item_in_select.text = "${ mList.size} Cards"
            }

        })

    }
    private fun setupItemOut(){
        val mAdapter =  SelectItemPagerAdapter(mActivity)
        viewpager_item_out.adapter  = mAdapter
        viewpager_item_out.setPageTransformer(true, FadePageTransformer(viewpager_item_out))
        viewpager_item_out.clipToPadding = false
        viewpager_item_out.offscreenPageLimit = 5
        mAdapter.addAllItem(imageListOut,0)
        viewpager_item_out.setCurrentItem(0,false)
        mAdapter.setOnUpdateCount(object:SelectItemPagerAdapter.OnCountUpdate{
            override fun onUpdate(mList: ArrayList<Int>) {
                itemSelectedOut.clear()
                itemSelectedOut.addAll(mList)
                item_out_select.text = "${ mList.size} Cards"
            }

        })
    }

    private fun updateCurrentItem(position:Int){
//        page_count.text = "${position+1}/${if (selectItemIn) imageListIn.size else imageListOut.size}"
//        currentPage = position
    }

}