package com.android.sportnmedc.network

import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.request.ReqBalanceOf
import com.android.sportnmedc.network.request.ReqNotificationList
import com.android.sportnmedc.network.request.ReqPromotionList
import com.android.sportnmedc.network.request.ReqTokensowned
import com.android.sportnmedc.repositories.BaseRepository

/************************************************************/
/****  Move to repositories that sort by type of model. *****/
/************************************************************/
class SampleRepositories : BaseRepository<Object>() {

    fun notificationList(resultCount: Int, order: String, lastId: Long) {
        networkClient.api.notificationList(BaseApplication.prefs.accessToken, ReqNotificationList(resultCount, order, lastId)).start
    }

    fun promotionList(resultCount: Int, order: String, lastId: Long) {
        networkClient.api.promotionList(BaseApplication.prefs.accessToken, ReqPromotionList(resultCount, order, lastId)).start
    }

    /************************************************************/
    /****               New API Function.                   *****/
    /************************************************************/

    fun bcTokensOwned() {
        networkClient.api.bcTokensOwned(BaseApplication.prefs.accessToken, ReqTokensowned(BaseApplication.prefs.publicKey))//.start //tokensOwned
    }

    fun bcValidServiceProducts() {
        networkClient.api.bcValidServiceProducts(BaseApplication.prefs.accessToken) // getValidServiceProducts
    }

    fun bcCountOfValidServiceProducts() {
        networkClient.api.bcCountOfValidServiceProducts(BaseApplication.prefs.accessToken) // countOfValidServiceProducts
    }

    fun bcBalanceOf() {
        networkClient.api.bcBalanceOf(BaseApplication.prefs.accessToken, ReqBalanceOf(BaseApplication.prefs.publicKey)) // balanceOf
    }

    /************************************************************/
    /****               New API Function. END               *****/
    /************************************************************/
}