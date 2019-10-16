package com.android.sportnmedc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.Utils


/**
 * Created by AndroidEB on 9/11/2017.
 */
abstract class ExBaseActivity(@LayoutRes val layoutId:Int, @MenuRes val menuId:Int = 0):AppCompatActivity() {



    val mActivity get() = this

    abstract fun onActivityCreated()


    var mToolbar: Toolbar? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        onActivityCreated()
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return mToolbar?.takeIf { menuId > 0 }?.run {
            menuInflater.inflate(menuId, menu)
            true
        } ?: super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.menu_item_notification -> {
//                val isShow = getPopUpNotification()?.togglePopUp(mToolbar as View)?:false
//                if (isShow) invalidateOptionsMenu()
//                return true
//            }
//            R.id.menu_item_chat -> {
//                startActivity(ChatListActivity.newIntent(mActivity))
//                return true
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == Utils.REQUEST_CODE_BUYSELL ||requestCode == Utils.REQUEST_CODE_TRADE  ||requestCode == Utils.REQUEST_CODE_ADD_COIN  )&& resultCode == Activity.RESULT_OK){
            setResult(Activity.RESULT_OK,data)
            finish()
        }

    }


}

//    fun showAlertLogin(){
//        DialogHelper.createConfirmDialog(mActivity,"you must login for use this feature.",DialogInterface.OnClickListener{ _ , _ ->
//            startActivityForResult(LoginActivity.newIntent(mActivity),LoginActivity.LOGIN_INTENT_CODE)}).show()
//    }

