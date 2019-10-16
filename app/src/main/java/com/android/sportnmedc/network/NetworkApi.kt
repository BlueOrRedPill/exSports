package com.android.sportnmedc.network

import com.android.sportnmedc.network.request.*
import com.android.sportnmedc.network.response.*
import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface NetworkApi {
    //AUTH
    @Multipart
    @POST("/api/v1.1/auth/signup")
    fun authSignUp(
        @Part("email") email: RequestBody,
        @Part("userName") userName: RequestBody,
        @Part("userBio") userBio: RequestBody,
        @Part("password") password: RequestBody,
        @Part("secondPassword") secondPassword: RequestBody,
        @Part userImage: MultipartBody.Part?
    ): Single<ResAuthSignup>    // SignUp

    @POST("/api/v1.1/auth/login")
    fun authLogin(@Body req: ReqAuthLogin): Single<ResAuthLogin>  // Login


    //WALLET
    @POST("/api/v1.1/wallet/transaction")
    fun walletTransactionList(@Header("x-access-token") token: String, @Body req: ReqWalletTransactionList): Single<ResWalletTransactionList>  // Wallet Transaction List

    @POST("/api/v1.1/wallet/transaction/detail")
    fun walletTransactionDetail(@Header("x-access-token") token: String, @Body req: ReqWalletTransactionDetail): Single<ResWalletTransactionDetail>  // Transaction Detail

    @POST("/api/v1.1/wallet/send")
    fun walletSendCoin(@Header("x-access-token") token: String, @Body req: ReqWalletSendCoin): Single<ResWalletSendCoin>  // Send EXS

    @POST("/api/v1.1/wallet/buy")
    fun walletBuyCoin(@Header("x-access-token") token: String, @Body req: ReqWalletBuyCoin): Single<ResWalletBuyCoin>  // Buy EXS


    //USER
    @POST("/api/v1.1/user/{targetUid}/detailprofile")
    fun userProfile(@Header("x-access-token") token: String, @Path("targetUid") targetUid:Long): Single<ResUserProfile>  // User Profile Detail

    @POST("/api/v1.1/user/update/profile")
    fun userUpdateProfile(@Header("x-access-token") token: String, @Body req: ReqUserUpdateProfile): Single<ResUserUpdateProfile>  // Update Profile

    @POST("/api/v1.1/user/update/password")
    fun userUpdatePassword(@Header("x-access-token") token: String, @Body req: ReqUserUpdatePassword): Single<ResUserUpdatePassword>  // Update Password

    @Multipart
    @POST("/api/v1.1/api/v1.1/user/setuserimage")
    fun userUpdateImage(
        @Header("x-access-token") token: String,
        @Part userImage: MultipartBody.Part
    ): Single<ResUserUpdateImage>   // Update Image

    @POST("/api/v1.1/user")
    fun userList(@Header("x-access-token") token: String, @Body req: JsonObject): Single<JsonObject>  // User List


    //LEADER BOARD
    @POST("/api/v1.1/leaderboard")
    fun leaderboard(@Header("x-access-token") token: String, @Body req: ReqLeaderboard): Single<ResLeaderboard>  // LeaderBoard


    //LIBRARY
    @POST("/api/v1.1/library/collection/type")
    fun libraryCollectionType(@Header("x-access-token") token: String, @Body req: ReqLibraryCollectionType): Single<ResLibraryCollectionType>  // Collection Type

    @POST("/api/v1.1/library/collection")
    fun libraryCollectionList(@Header("x-access-token") token: String, @Body req: ReqLibraryCollectionList): Single<ResLibraryCollectionList>  // Collection List

    @POST("/api/v1.1/library/card/detail")
    fun libraryItemDetail(@Header("x-access-token") token: String, @Body req: ReqLibraryItemDetail): Single<ResLibraryItemDetail>  // Item Detail

    @POST("/api/v1.1/library/card/transactions")
    fun libraryItemTransactionList(@Header("x-access-token") token: String, @Body req: ReqLibraryItemTransactionList): Single<ResLibraryItemTransactionList>  // Item Transaction List

    @POST("/api/v1.1/library/card/sale")
    fun librarySalesItemList(@Header("x-access-token") token: String, @Body req: ReqLibrarySalesItemList): Single<ResLibrarySalesItemList>  // Market Sales List (My)

    @POST("/api/v1.1/library/card/all")
    fun libraryItemList(@Header("x-access-token") token: String, @Body req: ReqLibraryItemList): Single<ResLibraryItemList>  // All Item List

    @POST("/api/v1.1/library/card/search")
    fun librarySearchItem(@Header("x-access-token") token: String, @Body req: ReqLibrarySearchItem): Single<ResLibrarySearchItem>  // Search Item


    //MARKET
    @POST("/api/v1.1/market")
    fun marketSales(@Header("x-access-token") token: String, @Body req: ReqMarketSaleList): Single<ResMarketSales>  // Market Sales List

    @POST("/api/v1.1/market/register")
    fun marketRegister(@Header("x-access-token") token: String, @Body req: ReqMarketRegister): Single<ResMarketRegister>  // Market Register

    @POST("/api/v1.1/market/cancel")
    fun marketCancel(@Header("x-access-token") token: String, @Body req: ReqMarketCancel): Single<ResMarketCancel>  // Market Cancel

    @POST("/api/v1.1/market/buy")
    fun marketBuy(@Header("x-access-token") token: String, @Body req: ReqMarketBuy): Single<ResMarketBuy>  // Market Buy

    @POST("/api/v1.1/market/newarrival")
    fun marketNewArrival(@Header("x-access-token") token: String, @Body req: ReqMarketNewArrivalList): Single<ResMarketNewArrivals>  // New Arrival List

    @POST("/api/v1.1/market/detail")
    fun marketSaleDetail(@Header("x-access-token") token: String, @Body req: ReqMarketSaleDetail): Single<ResMarketSaleDetail>  // Sales Item Detail

    //TRADE
    @POST("/api/v1.1/trade")
    fun tradeList(@Header("x-access-token") token: String, @Body req: ReqTradeList): Single<ResTradeList>  // Trade List

    @POST("/api/v1.1/trade/register")
    fun tradeRegister(@Header("x-access-token") token: String, @Body req: ReqTradeRegister): Single<ResTradeRegister>  // Trade Register

    @POST("/api/v1.1/trade/cancel")
    fun tradeCancel(@Header("x-access-token") token: String, @Body req: ReqTradeCancel): Single<ResTradeCancel>  // Trade Cancel

    @POST("/api/v1.1/trade/confirm")
    fun tradeConfirm(@Header("x-access-token") token: String, @Body req: ReqTradeConfirm): Single<ResTradeConfirm>  // Trade Confirm

    @POST("/api/v1.1/trade/search")
    fun tradeSearch(@Header("x-access-token") token: String, @Body req: ReqTradeSearchList): Single<ResTradeSearch>  // Search Trade List

    //NOTIFICATION
    @POST("/api/v1.1/notification")
    fun notificationList(@Header("x-access-token") token: String, @Body req: ReqNotificationList): Single<ResNotificationList>  // Notification List

    //PROMOTION
    @POST("/api/v1.1/promotion")
    fun promotionList(@Header("x-access-token") token: String, @Body req: ReqPromotionList): Single<ResPromotionList>  // Promotion List

    //BLOCK CHAIN

    @POST("/api/v1.1/bcmarket/getValidServiceProducts")
    fun bcValidServiceProducts(@Header("x-access-token") token: String): Single<ResBcValidServiceProducts>  // getValidServiceProducts

    @POST("/api/v1.1/bcmarket/countOfValidServiceProducts")
    fun bcCountOfValidServiceProducts(@Header("x-access-token") token: String): Single<JsonObject>  // countOfValidServiceProducts

    //body publicKey
    @POST("/api/v1.1/bccoin/balanceOf")
    fun bcBalanceOf(@Header("x-access-token") token: String, @Body req: ReqBalanceOf): Single<ResBcBalanceOf>  // balanceOf

    @POST("/api/v1.1/sports/tokensportslist")
    fun sportsList(@Header("x-access-token") token: String): Single<ResSportsList>  // Sports List

    @POST("/api/v1.1/sports/tokencardlist")
    fun cardList(@Header("x-access-token") token: String, @Body req: ReqCardList): Single<ResCardList>  // All Item List

    @POST("/api/v1.1/sports/tokenCardDetail")
    fun cardDetail(@Header("x-access-token") token: String, @Body req: ReqTokenCardDetail): Single<ResTokenCardDetail>

    //body publicKey
    @POST("/api/v1.1/bccoin/buyCoin")
    fun buyCoin(@Header("x-access-token") token: String, @Body req: ReqBuyCoin): Single<ResBuyCoin>  // All Item List

    //body publicKey
    @POST("/api/v1.1/bcmarket/purchaseserviceproduct")
    fun purchaseServiceProduct(@Header("x-access-token") token: String, @Body req: ReqPurchaseServiceProduct): Single<ResPurchaseServiceProduct>  // All Item List

    //body publicKey
    @POST("/api/v1.1/bctoken/tokensOwned")
    fun bcTokensOwned(@Header("x-access-token") token: String, @Body req:ReqTokensowned): Single<ResBcTokensOwned>  // tokensOwned

    //body publicKey
    @POST("/api/v1.1/bccoin/sendCoin")
    fun sendCoin(@Header("x-access-token") token: String, @Body req:ReqTransferFrom): Single<ResTransferFrom>  // tokensOwned
}