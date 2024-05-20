package com.example.traveltaipeiapp.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traveltaipeiapp.repository.MainRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(mainRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}