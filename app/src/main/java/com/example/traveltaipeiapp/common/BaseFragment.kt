package com.example.traveltaipeiapp.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected abstract fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): T
    protected abstract fun initViews()
    protected abstract fun initObserve()
    protected abstract fun changeLanguage()

    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected open val model: BaseViewModel? = null
    protected var activity: BaseActivity<*>? = null
    protected var actionBar: ActionBar? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = (context as BaseActivity<*>)
        actionBar = activity?.supportActionBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateLayout(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.onBackEvent = {}
        initViews()
        initObserve()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        actionBar = null
        super.onDetach()
    }
}