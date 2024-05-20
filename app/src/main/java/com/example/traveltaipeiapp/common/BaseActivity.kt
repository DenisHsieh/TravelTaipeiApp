package com.example.traveltaipeiapp.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    protected abstract fun inflateLayout(): T
    protected open fun findNavController(): NavController? = null
    protected open fun initActionBar() = Unit
    protected abstract fun initViews()
    protected open fun initObserve() {}

    private var navController: NavController? = null
    private var appBarConfiguration: AppBarConfiguration? = null
    protected lateinit var binding: T
    open val model: BaseViewModel? = null
    var onBackEvent: () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflateLayout()
        val view = binding.root
        setContentView(view)
        initActionBar()

        initViews()
        initObserve()
    }

    override fun onStart() {
        super.onStart()

        FragmentManagerProvider.setFragmentManager(supportFragmentManager)
        findNavController()?.also {
            navController = it
            appBarConfiguration = AppBarConfiguration(it.graph)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}