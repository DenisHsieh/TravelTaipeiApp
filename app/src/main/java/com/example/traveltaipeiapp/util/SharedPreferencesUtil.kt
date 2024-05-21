package com.example.traveltaipeiapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.traveltaipeiapp.TtApplication
import java.util.Locale

class SharedPreferencesUtil {

    companion object {
        const val PREFS_NAME = "PREFS_NAME"
        const val KEY_APP_LANGUAGE = "KEY_APP_LANGUAGE"

        private var _sharedPreferences: SharedPreferences? = null
        private val sharedPreferences: SharedPreferences
            get() {
                if (_sharedPreferences == null) {
                    _sharedPreferences = generateSharedPreferences()
                }
                return _sharedPreferences!!
            }

        private fun generateSharedPreferences(): SharedPreferences {
            return TtApplication.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }

        @SuppressLint("ApplySharedPref")
        fun putAppLanguage(language: String) {
            sharedPreferences.edit().putString(KEY_APP_LANGUAGE, language).commit()
        }

        fun getAppLanguage() =
            sharedPreferences.getString(KEY_APP_LANGUAGE, Locale.TRADITIONAL_CHINESE.toLanguageTag()) ?: Locale.TRADITIONAL_CHINESE.toLanguageTag()
    }
}