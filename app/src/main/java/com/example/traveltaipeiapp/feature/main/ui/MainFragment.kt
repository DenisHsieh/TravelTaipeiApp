package com.example.traveltaipeiapp.feature.main.ui

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.example.traveltaipeiapp.R
import com.example.traveltaipeiapp.api.model.AttractionItem
import com.example.traveltaipeiapp.api.model.NewsItem
import com.example.traveltaipeiapp.common.BaseFragment
import com.example.traveltaipeiapp.databinding.FragmentMainBinding
import com.example.traveltaipeiapp.feature.main.ui.MainActivity.Companion.mainRepository
import com.example.traveltaipeiapp.feature.main.ui.recylerview.AttractionsAdapter
import com.example.traveltaipeiapp.feature.main.ui.recylerview.NewsAdapter
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModel
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModelFactory

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val TAG = this.javaClass.simpleName
    private var newsAdapter: NewsAdapter? = null
    private var attractionsAdapter: AttractionsAdapter? = null

    override val model: MainViewModel by activityViewModels{
        MainViewModelFactory(mainRepository)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val menuInflater = MenuInflater(activity)
        menuInflater.inflate(R.menu.main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_change_language -> changeLanguage()
        }
        return super.onContextItemSelected(item)
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        newsAdapter = NewsAdapter()
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
        }
    }

    override fun initObserve() {
        Log.d(TAG, "initObserve...")

        model.newsLd.observe(viewLifecycleOwner) { item ->
            val newsDataList = item!!.data
            if (newsDataList.isNotEmpty()) {
                newsAdapter?.dataList = getNewsData(newsDataList)
            }
        }
        model.attractionsLd.observe(viewLifecycleOwner) { item ->
            val number = item!!.total
            binding.apply {
                taipeiViewpointNumberTv.text = "台北市景點 1/${number}"
            }

            val attractionsDataList = item.data
            if (attractionsDataList.isNotEmpty()) {
                attractionsAdapter?.dataList = getAttractionsData(attractionsDataList)
            }

        }

        val apiService = (activity as MainActivity).apiService
        model.getNewsData(apiService)
        model.getAttractionsData(apiService)
    }

    private fun getNewsData(newsDataList: List<NewsItem>): List<NewsItem> {
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

    private fun getAttractionsData(attractionsDataList: List<AttractionItem>): List<AttractionItem> {
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

}