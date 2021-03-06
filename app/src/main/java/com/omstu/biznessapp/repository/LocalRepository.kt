package com.omstu.biznessapp.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omstu.biznessapp.network.response.Group
import com.omstu.biznessapp.network.response.StudentItem
import kotlin.reflect.KProperty

open class LocalRepository(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(OMSTU_PREFS, Context.MODE_PRIVATE);

    private val gson = Gson()

    inner class StringPreferenceDelegate(private val defaultValue: String = "") {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String = sharedPreferences.getString(property.name, defaultValue)
        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) =
                sharedPreferences
                        .edit()
                        .putString(property.name, value)
                        .apply()
    }

    inner class IntPreferenceDelegate(private val defaultValue: Int = 0) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = sharedPreferences.getInt(property.name, defaultValue)
        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) =
                sharedPreferences
                        .edit()
                        .putInt(property.name, value)
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

    var fnpp by StringPreferenceDelegate()

    var email by StringPreferenceDelegate()
    var selectedGroup by JsonPreferenceDelegate(Group::class.java)
    var color: Int by IntPreferenceDelegate()
    var strokeWidth: Int by IntPreferenceDelegate(15)
    var changedStudentsData by JsonPreferenceDelegate(StudentItemsHolder::class.java)

    fun clear() {
        sharedPreferences
                .edit()
                .clear()
                .apply()
    }

    data class StudentItemsHolder(val changedStudentsData: MutableMap<String, StudentItem>)
    companion object {
        const val OMSTU_PREFS = "OMSTU_PREFS"
        const val MAX_WIDTH = 25
    }
}