package com.example.traveltaipeiapp.feature.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.traveltaipeiapp.api.ApiService
import com.example.traveltaipeiapp.api.model.Attractions
import com.example.traveltaipeiapp.api.model.News
import com.example.traveltaipeiapp.common.BaseViewModel
import com.example.traveltaipeiapp.repository.MainRepository
import com.example.traveltaipeiapp.util.LanguageUtil
import com.example.traveltaipeiapp.util.SharedPreferencesUtil
import com.example.traveltaipeiapp.util.SingleLiveEventV2
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    fun changeLanguage(lang: String) {
        SharedPreferencesUtil.putAppLanguage(lang)
    }

    private val _newsLd = SingleLiveEventV2<News?>()
    val newsLd: MutableLiveData<News?> = _newsLd
    fun getNewsData(apiService: ApiService) = viewModelScope.launch(dispatcher) {
            Log.d(TAG, "getNewsData...")

            val response = mainRepository.getNewsData(LanguageUtil.getApiLanguage(), apiService)
            if (response.isSuccessful) {
                val newsData = response.body()
                // update liveData
                _newsLd.postValue(newsData)
            } else {
                Log.e(TAG, "getNewsData: something went wrong!")
            }
    }

    private val _attractionsLd = SingleLiveEventV2<Attractions?>()
    val attractionsLd: MutableLiveData<Attractions?> = _attractionsLd
    fun getAttractionsData(apiService: ApiService) = viewModelScope.launch(dispatcher) {
        Log.d(TAG, "getAttractionsData...")

        val response = mainRepository.getAttractionsData(LanguageUtil.getApiLanguage(), apiService)
        if (response.isSuccessful) {
            val attractionsData = response.body()
            // update liveData
            _attractionsLd.postValue(attractionsData)
        } else {
            Log.e(TAG, "getAttractionsData: something went wrong!")
        }
    }

}