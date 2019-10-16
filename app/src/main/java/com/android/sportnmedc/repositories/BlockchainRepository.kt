package com.android.sportnmedc.repositories

import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.request.*

class BlockchainRepository<T> :BaseRepository<T>(){

    fun libraryItemList() {
        networkClient.api.bcBalanceOf(BaseApplication.prefs.accessToken, ReqBalanceOf(BaseApplication.prefs.publicKey)).start
    }

    fun bcTokensOwned() {
        networkClient.api.bcTokensOwned(BaseApplication.prefs.accessToken, ReqTokensowned(BaseApplication.prefs.publicKey)).start
    }

    fun bcValidServiceProducts() {
        networkClient.api.bcValidServiceProducts(BaseApplication.prefs.accessToken).start // getValidServiceProducts
    }

    fun bcBalanceOf() {
        networkClient.api.bcBalanceOf(BaseApplication.prefs.accessToken, ReqBalanceOf(BaseApplication.prefs.publicKey)).start
    }

    fun buyCoin(amount:Int) {
        networkClient.api.buyCoin(BaseApplication.prefs.accessToken, ReqBuyCoin(BaseApplication.prefs.publicKey, amount)).start
    }

    fun purchaseServiceProduct(productId: Int, quantity: Int) {
        networkClient.api.purchaseServiceProduct(BaseApplication.prefs.accessToken, ReqPurchaseServiceProduct(BaseApplication.prefs.publicKey, productId, quantity)).start
    }

    fun sendCoin(toPublicKey:String, amount: Int) {
        networkClient.api.sendCoin(BaseApplication.prefs.accessToken, ReqTransferFrom(BaseApplication.prefs.publicKey, toPublicKey, amount)).start
    }
}