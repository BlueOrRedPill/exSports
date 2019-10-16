package com.android.sportnmedc

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.adapters.BaseRecyclerAdapter
import com.android.sportnmedc.adapters.CardAdapter
import com.android.sportnmedc.exdata.BaseDataSource
import com.android.sportnmedc.exdata.cards.CardRepositories
import com.android.sportnmedc.exdata.trades.TradeModel
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import kotlinx.android.synthetic.main.activity_select_card.*

class SelectCardActivityEx: ExBaseActivity(R.layout.activity_select_card), ToolbarHelper {



    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? =""
    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    var mAdapter: CardAdapter?=null
    private var mRepositories = CardRepositories()

    var mTradeModel:TradeModel ?=null
    var index:Int = 0
    var isCardOut:Boolean = false


    companion object {
        fun newIntent(mActivity: AppCompatActivity, tradeModel: TradeModel,index:Int,isCardOut:Boolean): Intent {
            val intent = Intent(mActivity, SelectCardActivityEx::class.java)
            intent.putExtra("tradeModel", tradeModel)
            intent.putExtra("index", index)
            intent.putExtra("isCardOut", isCardOut)
            return intent
        }
    }

    override fun onActivityCreated()  {
        setupToolbar()
        mTradeModel = intent.getParcelableExtra("tradeModel")
        index = intent.getIntExtra("index",0)
        isCardOut = intent.getBooleanExtra("isCardOut",false)
        mAdapter = CardAdapter(mActivity)
        recyclerView.adapter = mAdapter
        mAdapter?.setOnItemClick(object :BaseRecyclerAdapter.OnItemClicked{
            override fun onItemClicked(v: View, position: Int) {
                when(v.id){
                    R.id.card_add ->{
                        mTradeModel?.apply {
                            if (isCardOut){
                                cardOut[index] = mAdapter?.getListItem(position)?.image?:0
                            }else{
                                cardIn[index] = mAdapter?.getListItem(position)?.image?:0
                            }
                        }
                        Utils.hideKeyboard(this@SelectCardActivityEx)
                        setResult(Activity.RESULT_OK, intent.putExtra("tradeModel", mTradeModel))
                        finish()
                    }
                    else -> {

                    }
                }


            }

        })
        search_auto_complete.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                mAdapter?.getFilter()?.filter(p0)
//                if (p0?.length?:0 > 0){
//                    content_view.visibility = View.GONE
//                }else{
//                    content_view.visibility = View.VISIBLE
//                }

            }

        })
        updateData()

    }

    private fun updateData(){
        mRepositories.load(object: BaseDataSource.OnLoad{
            override fun onLoaded() {
                mAdapter?.setItems(mRepositories.getDataList())
            }
        })
    }


}