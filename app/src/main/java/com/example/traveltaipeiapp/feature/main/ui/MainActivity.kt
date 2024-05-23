package com.example.traveltaipeiapp.feature.main.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.traveltaipeiapp.R
import com.example.traveltaipeiapp.TtApplication
import com.example.traveltaipeiapp.api.ApiService
import com.example.traveltaipeiapp.common.BaseActivity
import com.example.traveltaipeiapp.databinding.ActivityMainBinding
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModel
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModelFactory
import com.example.traveltaipeiapp.repository.MainRepository

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val TAG = this.javaClass.simpleName
    lateinit var apiService: ApiService

    override val model: MainViewModel by viewModels {
        MainViewModelFactory(mainRepository)
    }

    companion object {
        private var _mainRepository: MainRepository? = null
            get() {
                if (field == null) {
                    field = MainRepository()
                }
                return field
            }
        val mainRepository: MainRepository
            get() = _mainRepository!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = (application as TtApplication).apiService
    }

    override fun inflateLayout(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun findNavController(): NavController =
        findNavController(R.id.fcv_main)

    override fun initViews() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}