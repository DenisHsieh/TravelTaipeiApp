package com.example.traveltaipeiapp.common

import androidx.fragment.app.FragmentManager

object FragmentManagerProvider {
    private var fragmentManager: FragmentManager? = null

    fun setFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    fun getFragmentManager(): FragmentManager? {
        return fragmentManager ?: throw IllegalStateException("FragmentManager is null")
    }
}