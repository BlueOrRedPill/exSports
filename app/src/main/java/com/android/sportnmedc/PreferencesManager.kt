package com.android.sportnmedc

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val PREFS_NAME = "exsports_prefs"
    private val PREF_KEY_ACCESSTOKEN = "accessToken"
    private val PREF_KEY_REFRESHTOKEN = "refreshToken"
    private val PREF_KEY_UID = "uId"
    private val PREF_KEY_PROFILE = "profile"
    private val PREF_KEY_PUBLICK_KEY = "publickKey"
    private val PREF_KEY_BALANCE = "exsBalance"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var accessToken: String
        get() = prefs.getString(PREF_KEY_ACCESSTOKEN, "")
        set(value) = prefs.edit().putString(PREF_KEY_ACCESSTOKEN, value).apply()

    var refreshToken: String
        get() = prefs.getString(PREF_KEY_REFRESHTOKEN, "")
        set(value) = prefs.edit().putString(PREF_KEY_REFRESHTOKEN, value).apply()

    var uId: Long
        get() = prefs.getLong(PREF_KEY_UID, 0)
        set(value) = prefs.edit().putLong(PREF_KEY_UID, value).apply()

    var profile: String
        get() = prefs.getString(PREF_KEY_PROFILE, "")
        set(value) = prefs.edit().putString(PREF_KEY_PROFILE, value).apply()

    var publicKey: String
        get() = prefs.getString(PREF_KEY_PUBLICK_KEY, "")
        set(value) = prefs.edit().putString(PREF_KEY_PUBLICK_KEY, value).apply()

    var exsBalance: Int
        get() = prefs.getInt(PREF_KEY_BALANCE, 0)
        set(value) = prefs.edit().putInt(PREF_KEY_BALANCE, value).apply()
//    PREF_KEY_BALANCE
}