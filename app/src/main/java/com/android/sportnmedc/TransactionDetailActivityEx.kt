package com.android.sportnmedc

import android.content.Intent
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.ToolbarHelper
import kotlinx.android.synthetic.main.activity_transaction_detail.*

class TransactionDetailActivityEx :ExBaseActivity(R.layout.activity_transaction_detail), ToolbarHelper {


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Transaction Detail"

    data class Item(val key:String,val value:String)

    val data = arrayListOf(
        Item("AMOUNT TO RECEIVE","0 EXS"),
        Item("DATE","15 Aug 2019 13:00"),
        Item("REQUEST ID","5458asdasd4556146532adasdasdad"),
        Item("BALANCE BEFORE","1000 exs"),
        Item("BALANCE AFTER","980"),
        Item("RECEIVEMENT'S ADDRESS","cbthiy54hjkdhg2d1fh3gj84jusash52 ")
    )

    companion object {
        fun newIntent(mActivity: AppCompatActivity,amount:String): Intent {
            val intent = Intent(mActivity,TransactionDetailActivityEx::class.java)
            intent.putExtra("amount",amount)
            return intent
        }
    }


    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        if (detail_container.childCount > 0) detail_container.removeAllViews()
        for (i in 0 until data.size){
            val v = LayoutInflater.from(mActivity).inflate(R.layout.item_transaction_detail,detail_container,false)
            val keyTxt: TextView = v.findViewById(R.id.key)
            val valueTxt: TextView = v.findViewById(R.id.value)
            data[i].let { item->
                keyTxt.text = item.key
                valueTxt.text = item.value
            }
            detail_container.addView(v)
        }

        amount_txt.text = "${intent.getStringExtra("amount")?:"0"} EXS"


    }

}