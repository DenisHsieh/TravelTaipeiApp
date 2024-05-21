package com.example.traveltaipeiapp.util

import android.os.Build
import android.util.Log
import com.example.traveltaipeiapp.TtApplication
import java.util.Locale


class LanguageUtil {

    companion object {
        private val TAG = Companion::class.java.simpleName

        const val LANGUAGE_ZH_TW = "zh-tw"
        const val LANGUAGE_ZH_CN = "zh-cn"
        const val LANGUAGE_EN = "en"
        const val LANGUAGE_JA = "ja"
        const val LANGUAGE_KO = "ko"
        const val LANGUAGE_ES = "es"
        const val LANGUAGE_ID = "id"
        const val LANGUAGE_TH = "th"
        const val LANGUAGE_VI = "vi"

        fun getApiLanguage(): String {
            return when (getLanguageTag()) {
                Locale.TRADITIONAL_CHINESE.toLanguageTag() -> LANGUAGE_ZH_TW
                else -> LANGUAGE_EN
            }
        }

        fun getLanguageTag(): String {
            return SharedPreferencesUtil.getAppLanguage()
        }

        fun initAppLanguage() {
            val appLang = SharedPreferencesUtil.getAppLanguage()
            if (appLang == Locale.TRADITIONAL_CHINESE.toLanguageTag()) {
                SharedPreferencesUtil.putAppLanguage(Locale.TRADITIONAL_CHINESE.toLanguageTag())
            } else {
                SharedPreferencesUtil.putAppLanguage(Locale.ENGLISH.toLanguageTag())
            }
        }

        fun getSystemLanguageTag(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                TtApplication.getContext().resources.configuration.locales[0].language
            } else {
                TtApplication.getContext().resources.configuration.locale.language
            }
        }
    }

}