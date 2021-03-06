package com.example.engineeringenglish.service.model


import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "TsjDom"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var numberCharacters: Int?
        get() = preferences.getInt("numberCharacters", 0)
        set(value) = preferences.edit {
            it.putInt("numberCharacters", value!!)
        }

    var isLogined: Boolean
        get() = preferences.getBoolean("isLogined", false)
        set(value) = preferences.edit {
            it.putBoolean("isLogined", value)
        }
}