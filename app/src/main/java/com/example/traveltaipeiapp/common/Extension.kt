package com.example.traveltaipeiapp.common

import android.content.Context
import android.content.res.Configuration
import com.example.traveltaipeiapp.util.LanguageUtil
import java.util.Locale

fun Context.getLocalString(
    resId: Int,
    languageTag: String = LanguageUtil.getLanguageTag()
): String {
    val config = Configuration(resources.configuration)
    config.setLocale(Locale(languageTag))
    return createConfigurationContext(config).getText(resId).toString()
}