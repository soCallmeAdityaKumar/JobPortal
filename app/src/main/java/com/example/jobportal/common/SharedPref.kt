package com.example.jobportal.common

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SharedPref @Inject constructor(@ApplicationContext context: Context) {

    private  val prefs: SharedPreferences =context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

    fun save(data:Int?,name:String){
        if (data != null) {
            prefs.edit().putInt(name,data).apply()
        }
    }

    fun delete(name: String){
        prefs.edit().remove(name).apply()
    }

    fun value(name: String): Int {
        return prefs.getInt(name,-1)
    }

    fun ifContain(name:String):Boolean{
        return prefs.contains(name)
    }
}