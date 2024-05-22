package com.example.traveltaipeiapp.feature.main.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.example.traveltaipeiapp.R
import com.example.traveltaipeiapp.api.ApiService
import com.example.traveltaipeiapp.api.model.AttractionItem
import com.example.traveltaipeiapp.api.model.NewsItem
import com.example.traveltaipeiapp.common.BaseFragment
import com.example.traveltaipeiapp.common.getLocalString
import com.example.traveltaipeiapp.databinding.FragmentMainBinding
import com.example.traveltaipeiapp.feature.main.ui.MainActivity.Companion.mainRepository
import com.example.traveltaipeiapp.feature.main.ui.recylerview.adaptor.AttractionsAdapter
import com.example.traveltaipeiapp.feature.main.ui.recylerview.adaptor.NewsAdapter
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModel
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModelFactory
import com.example.traveltaipeiapp.util.LanguageUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.Locale

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val TAG = this.javaClass.simpleName
    private var newsAdapter: NewsAdapter? = null
    private var attractionsAdapter: AttractionsAdapter? = null

    override val model: MainViewModel by activityViewModels{
        MainViewModelFactory(mainRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarSpace.inflateMenu(R.menu.main)
        binding.toolbarSpace.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_change_language -> {
                    changeLanguage()
                    true
                }
                else -> false
            }
        }

        showProgressBar()
    }

    override fun changeLanguage() {
        Log.d(TAG, "changeLanguage...")
        val items = arrayOf(LanguageUtil.LANGUAGE_ZH_TW, LanguageUtil.LANGUAGE_EN)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(requireContext().getLocalString(R.string.dialog_language_title))
            .setItems(items) { dialog: DialogInterface?, which: Int ->
                when (items[which]) {
                    LanguageUtil.LANGUAGE_ZH_TW -> model.changeLanguage(Locale.TRADITIONAL_CHINESE.toLanguageTag())
                    else -> model.changeLanguage(Locale.ENGLISH.toLanguageTag())
                }
                activity?.recreate()
            }.show()

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        newsAdapter = NewsAdapter(object : NewsAdapter.ClickListener {
            override fun onItemClick(v: View, position: Int) {
                val newsItem = newsAdapter?.getNewsItemAtPosition(position)
                if (newsItem != null) {
                    navigateToWebViewFragment(newsItem.url)
                }
            }

        })
        attractionsAdapter = AttractionsAdapter()

        val layoutManager01 = LinearLayoutManager(this.context)
        val viewPool01 = RecycledViewPool()
        layoutManager01.orientation = LinearLayoutManager.VERTICAL

        val layoutManager02 = LinearLayoutManager(this.context)
        val viewPool02 = RecycledViewPool()
        layoutManager02.orientation = LinearLayoutManager.VERTICAL

        binding.apply {
            latestNewsRecyclerView.layoutManager = layoutManager01
            latestNewsRecyclerView.setRecycledViewPool(viewPool01)
            latestNewsRecyclerView.adapter = newsAdapter

            attractionsRecyclerView.layoutManager = layoutManager02
            attractionsRecyclerView.setRecycledViewPool(viewPool02)
            attractionsRecyclerView.adapter = attractionsAdapter

            toolbarSpace.setTitle(requireContext().getLocalString(R.string.app_name))
            latestNewsBtn.text = requireContext().getLocalString(R.string.latest_news)
            attractionsBtn.text = requireContext().getLocalString(R.string.travel_attractions)

            latestNewsBtn.setOnClickListener {
                showProgressBar()
                val apiService = (activity as MainActivity).apiService
                getNewsData(apiService)
            }
            attractionsBtn.setOnClickListener {
                showProgressBar()
                val apiService = (activity as MainActivity).apiService
                getAttractionsData(apiService)
            }
        }
    }

    override fun initObserve() {
        Log.d(TAG, "initObserve...")

        model.newsLd.observe(viewLifecycleOwner) { item ->
            val newsDataList = item!!.data
            if (newsDataList.isNotEmpty()) {
                newsAdapter?.dataList = handleNewsData(newsDataList)
                hideProgressBar()
            } else {
                newsAdapter?.dataList = handleNewsData(emptyList())
                hideProgressBar()
            }
        }
        model.attractionsLd.observe(viewLifecycleOwner) { item ->
            val number = item!!.total
            binding.apply {
                taipeiViewpointNumberTv.text = requireContext().getLocalString(R.string.taipei_attractions).plus(number)
            }

            val attractionsDataList = item.data
            if (attractionsDataList.isNotEmpty()) {
                attractionsAdapter?.dataList = handleAttractionsData(attractionsDataList)
                hideProgressBar()
            }
        }

        val apiService = (activity as MainActivity).apiService
        getNewsData(apiService)
        getAttractionsData(apiService)
    }

    private fun getNewsData(apiService: ApiService) {
        model.getNewsData(apiService)
    }

    private fun getAttractionsData(apiService: ApiService) {
        model.getAttractionsData(apiService)
    }

    private fun handleNewsData(newsDataList: List<NewsItem>): List<NewsItem> {
        if (newsDataList.isEmpty()) {
            return listOf(NewsItem(title = "No Data", description = "No Data"))
        }

        val data0 = newsDataList[0].apply {
            title = title.substring(0, 20) + "..."
            description = description.trim().substring(0, 70) + "..."
        }
        val data1 = newsDataList[1].apply {
            title = title.substring(0, 20) + "..."
            description = description.trim().substring(0, 70) + "..."
        }
        val data2 = newsDataList[2].apply {
            title = title.substring(0, 20) + "..."
            description = description.trim().substring(0, 70) + "..."
        }
        return mutableListOf(data0, data1, data2)
    }

    private fun handleAttractionsData(attractionsDataList: List<AttractionItem>): List<AttractionItem> {
        val data0 = attractionsDataList[2].apply {
            introduction = introduction.trim().substring(0, 50) + "..."
        }
        val data1 = attractionsDataList[3].apply {
            introduction = introduction.trim().substring(0, 50) + "..."
        }
        val data2 = attractionsDataList[4].apply {
            introduction = introduction.trim().substring(0, 50) + "..."
        }
        return mutableListOf(data0, data1, data2)
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    fun navigateToWebViewFragment(url: String) {
        if (url.isNotBlank()) {
            val bundle = bundleOf("url" to url)
            findNavController().navigate(R.id.action_mainFragment_to_webviewFragment, bundle)
        }
    }

}