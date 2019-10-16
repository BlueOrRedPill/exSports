package com.android.sportnmedc

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sportnmedc.adapters.BaseRecyclerAdapter
import com.android.sportnmedc.adapters.UserAdapter
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.userdetail.UserDetailActivityEx
import kotlinx.android.synthetic.main.activity_find_user.*

class FindUserActivityEx:ExBaseActivity(R.layout.activity_find_user),ToolbarHelper{



    var mUserAdapter:UserAdapter?=null

    companion object {
        fun newIntent(mActivity:AppCompatActivity):Intent{
            val intent = Intent(mActivity,FindUserActivityEx::class.java)
//            intent.putExtra("exUser",mExUser)
            return intent
        }
    }
    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? = "FIND USERS"

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        setupUser()
        search_edt.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                mUserAdapter?.filter?.filter(p0)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                mUserAdapter?.getFilter()?.filter(p0)
            }

        })
        val mViewModel = ViewModelProviders.of(this).get(FindUserViewModel::class.java)
        mViewModel.getUser().observe(this, Observer {
            progressBar.visibility =   if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
            mUserAdapter?.addDataAll(it,1)
        })
    }


    private fun setupUser(){
        mUserAdapter =  UserAdapter(mActivity)
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.adapter = mUserAdapter
        mUserAdapter?.setOnItemClick(object:BaseRecyclerAdapter.OnItemClicked{
            override fun onItemClicked(v: View, position: Int) {
                mUserAdapter?.getItem(position)?.let {
                    startActivityForResult(UserDetailActivityEx.newIntent(mActivity,it), Utils.REQUEST_CODE_TRADE)
                }
            }

        })
    }
}