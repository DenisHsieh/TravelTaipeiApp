package com.example.traveltaipeiapp.feature.main.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.traveltaipeiapp.R
import com.example.traveltaipeiapp.common.BaseFragment
import com.example.traveltaipeiapp.databinding.FragmentWebviewBinding
import com.example.traveltaipeiapp.feature.main.ui.MainActivity.Companion.mainRepository
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModel
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModelFactory

class WebviewFragment : BaseFragment<FragmentWebviewBinding>() {

    private val TAG = this.javaClass.simpleName
    private var url: String? = "https://www.google.com.tw/"

    override val model: MainViewModel by activityViewModels{
        MainViewModelFactory(mainRepository)
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWebviewBinding {
        return FragmentWebviewBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString("url")
        Log.d(TAG, "onCreate: ${url}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
        if (url != null) {
            binding.apply {
                toolbarSpace.setNavigationOnClickListener {
                    findNavController().navigateUp()
                }

                webView.settings.javaScriptEnabled = true
                webView.setWebViewClient(object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        url?.let {
                            webView.loadUrl(it)
                        }
                        return true
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        hideProgressBar()
                    }
                })

                url?.let {
                    Log.d(TAG, "webView: load url")
                    webView.loadUrl(it)
                }
            }
        }
    }

    override fun initObserve() {}

    override fun changeLanguage() {}

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


}