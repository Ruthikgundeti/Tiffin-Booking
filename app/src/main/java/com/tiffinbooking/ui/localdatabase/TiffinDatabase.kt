package com.tiffinbooking.ui.localdatabase

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.runBlocking

class TiffinDatabase(context:Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("tiffin_database", Context.MODE_PRIVATE)

    var isLogin: Boolean
        get() = sharedPreferences.getBoolean("isLogin", false)
        set(isLogin) = runBlocking {
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLogin", isLogin)
            editor.apply()
        }
}