package com.mney.wallet.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mney.wallet.network.Coin
import kotlin.reflect.KProperty

open class LocalRepository(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(Companion.MNEY_PREFS, Context.MODE_PRIVATE);

    private val gson = Gson()

    inner class StringPreferenceDelegate(private val defaultValue: String = "") {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String = sharedPreferences.getString(property.name, defaultValue)
        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) =
                sharedPreferences
                        .edit()
                        .putString(property.name, value)
                        .apply()
    }

    inner class JsonPreferenceDelegate<T>(private val clazz: Class<T>) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            val json = sharedPreferences.getString(property.name, "null")
            if (json.isEmpty())
                return null
            return gson.fromJson(json, clazz)
        }
        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) =
                sharedPreferences
                        .edit()
                        .putString(property.name, gson.toJson(value))
                        .apply()
    }

    var token by StringPreferenceDelegate()

    var id by StringPreferenceDelegate()

    var email by StringPreferenceDelegate()
    var phone by StringPreferenceDelegate()
    var firstname by StringPreferenceDelegate()
    var lastname by StringPreferenceDelegate()

    var selectedCoin by JsonPreferenceDelegate(Coin::class.java)

    fun clear() {
        sharedPreferences
                .edit()
                .clear()
                .apply()
    }

    companion object {
        const val MNEY_PREFS = "MNEY_PREFS"
    }
}