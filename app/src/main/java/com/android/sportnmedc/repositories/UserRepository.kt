package com.android.sportnmedc.repositories

import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.network.request.ReqAuthLogin
import com.android.sportnmedc.network.request.ReqLeaderboard
import com.android.sportnmedc.network.request.ReqUserUpdatePassword
import com.android.sportnmedc.network.request.ReqUserUpdateProfile
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class UserRepository :BaseRepository<UserModel>(){

    fun login(email: String, password: String) {
        networkClient.api.authLogin(ReqAuthLogin(email, password)).start
    }

    fun singUp(email: String, userName: String, userBio: String,
               password: String, secondPassword: String, file: File?) {
        var filePart:MultipartBody.Part? = null
//        var fileRequestBody:RequestBody? = null
        file?.also {
//            var fileRequestBody =  it.toString().toRequestBody("image/*".toMediaTypeOrNull())
//            filePart = MultipartBody.Part.createFormData("image", it.name, fileRequestBody)

//            multipart/form-data
//            val fileBody = it.toString().toRequestBody("image/${Utils.getExtension(file.path)}".toMediaTypeOrNull())
            val fileBody = RequestBody.create("image/*".toMediaTypeOrNull(), it) //it.toString().toRequestBody("image/*".toMediaTypeOrNull())
//            val fileBody = it.toString().toRequestBody("image/${Utils.getExtension(file.path)}".toMediaTypeOrNull())
            filePart = MultipartBody.Part.createFormData("userImage", it.name, fileBody)
        }
        val emailBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val passwordBody = password.toRequestBody("text/plain".toMediaTypeOrNull())
        val userNameBody = userName.toRequestBody("text/plain".toMediaTypeOrNull())
        var userBioBody = userBio.toRequestBody("text/plain".toMediaTypeOrNull())
        val secondPasswordBody = secondPassword.toRequestBody("text/plain".toMediaTypeOrNull())

//        val emailBody = email.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//        val passwordBody = password.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//        val userNameBody = userName.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//        var userBioBody = userBio.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//        val secondPasswordBody = secondPassword.toRequestBody("multipart/form-data".toMediaTypeOrNull())


//        val emailBody = RequestBody.create(MultipartBody.FORM, email)
//        val passwordBody = RequestBody.create(MultipartBody.FORM, password)
//        val userNameBody = RequestBody.create(MultipartBody.FORM, userName)
//        val userBioBody = RequestBody.create(MultipartBody.FORM, userBio)
//        val secondPasswordBody = RequestBody.create(MultipartBody.FORM, secondPassword)

//        val MEDIA_TYPE_TEXT = "text/plain".toMediaTypeOrNull()
//        val MEDIA_TYPE_IMAGE = "image/*".toMediaTypeOrNull()



//        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file!!)
////RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//        val body = MultipartBody.Part.createFormData("image", file.name, requestBody)

        networkClient.api.authSignUp(emailBody, userNameBody, userBioBody, passwordBody, secondPasswordBody, filePart).start
    }


    fun userProfile(targetUid: Long) {
        networkClient.api.userProfile(BaseApplication.prefs.accessToken, targetUid).start
    }

    fun userUpdateProfile(name: String, bio: String) {
       networkClient.api.userUpdateProfile(BaseApplication.prefs.accessToken, ReqUserUpdateProfile(name, bio)).start
    }

    fun userUpdatePassword(password: String) {
     networkClient.api.userUpdatePassword(BaseApplication.prefs.accessToken, ReqUserUpdatePassword(password)).start
    }

    fun userUpdateImage(file: File) {
        val fileBody = file.toString().toRequestBody("image/*".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("userImage", file.name, fileBody)
         networkClient.api.userUpdateImage(BaseApplication.prefs.accessToken, filePart).start
    }

    fun leaderboard(resultCount: Int, order: String, lastUId: Long) {
      networkClient.api.leaderboard(BaseApplication.prefs.accessToken, ReqLeaderboard(resultCount, order, lastUId)).start
    }

}