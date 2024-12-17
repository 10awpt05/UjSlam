package com.example.sullan_slambook

import android.content.Context
import com.example.sullan_slambook.model.UserInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefHelper(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("slambook_prefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()
    private val USER_INFO_LIST_KEY = "user_info_list"

    fun saveUserInfoList(userInfoList: List<UserInfo>) {
        val json = gson.toJson(userInfoList)
        editor.putString(USER_INFO_LIST_KEY, json)
        editor.apply()
    }

    fun getUserInfoList(): List<UserInfo> {
        val json = sharedPreferences.getString(USER_INFO_LIST_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<UserInfo>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}
