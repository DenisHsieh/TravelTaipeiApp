package com.example.traveltaipeiapp.common

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner

object MyLifecycleOwnerProvider : Application.ActivityLifecycleCallbacks {
    private var lifecycleOwner: LifecycleOwner? = null

    fun getLifecycleOwner(): LifecycleOwner? {
        return lifecycleOwner
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        lifecycleOwner = activity as? LifecycleOwner
    }

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        if (lifecycleOwner == activity) {
            lifecycleOwner = null
        }
    }
}