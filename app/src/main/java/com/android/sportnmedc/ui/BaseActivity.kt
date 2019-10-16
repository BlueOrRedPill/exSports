package com.android.sportnmedc.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.android.sportnmedc.helpers.Utils


/**
 * Created by AndroidEB on 9/11/2017.
 */
abstract class BaseActivity<T : ViewModel>(@LayoutRes val layoutId:Int, @MenuRes val menuId:Int = 0):AppCompatActivity() {

     val mViewModel by lazy {
         initViewModel()
     }

    val mActivity get() = this

    abstract fun onActivityCreated()

    abstract fun initViewModel():T

    var mToolbar: Toolbar? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        onActivityCreated()
        setupPermissions()
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Utils.REQUEST_CODE_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Logger.i("Permission has been denied by user")
                } else {
//                    Logger.i("Permission has been granted by user")
                }
            }
        }

    }

    //    ﻿override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        when (requestCode) {
//            Utils.REQUEST_CODE_READ_EXTERNAL_STORAGE -> {
//                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Logger.i("Permission has been denied by user")
//                } else {
//                    Logger.i("Permission has been granted by user")
//                }
//            }
//        }
//        when (requestCode) {
//            Utils.REQUEST_CODE_READ_EXTERNAL_STORAGE -> {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//
//                    Toast.makeText(this, "승인이 허가되어 있습니다.", Toast.LENGTH_LONG).show()
//
//                } else {
//                    Toast.makeText(this, "아직 승인받지 않았습니다.", Toast.LENGTH_LONG).show()
//                }
//                return
//            }
//        }
//
////        if (grantResults.length > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
////
////            Toast.makeText(this, "승인이 허가되어 있습니다.", Toast.LENGTH_LONG).show()
////
////        } else {
////            Toast.makeText(this, "아직 승인받지 않았습니다.", Toast.LENGTH_LONG).show()
////        }
//    }﻿


    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE)

        val internetPermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.INTERNET)

        val accessPermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_NETWORK_STATE)

        val storage = ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
//        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

//        <uses-permission android:name="android.permission.INTERNET"/>
//        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

        if (permission != PackageManager.PERMISSION_GRANTED || internetPermission != PackageManager.PERMISSION_GRANTED || accessPermission != PackageManager.PERMISSION_GRANTED || storage != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE),
            Utils.REQUEST_CODE_READ_EXTERNAL_STORAGE)
    }
}

//    fun showAlertLogin(){
//        DialogHelper.createConfirmDialog(mActivity,"you must login for use this feature.",DialogInterface.OnClickListener{ _ , _ ->
//            startActivityForResult(LoginActivity.newIntent(mActivity),LoginActivity.LOGIN_INTENT_CODE)}).show()
//    }

