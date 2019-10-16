package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose
import okhttp3.MultipartBody

/*
Auth - Singup
/api/v1.1/auth/signup

{
    "email": "11aaaasdgsdggdgsgdf7155mino74444@hanafos.com",
    "password": "haha",
    "userName": "mark",
    "userBio" : "one day more",
    "userImage" : file,
    "secondPassword": "123456"
}
*/

//file: MultipartBody.Part)
data class ReqAuthSingup(@Expose var email: String, @Expose var userName: String, @Expose var userBio: String, @Expose var password: String, @Expose var secondPassword: String, @Expose var userImage: MultipartBody.Part)