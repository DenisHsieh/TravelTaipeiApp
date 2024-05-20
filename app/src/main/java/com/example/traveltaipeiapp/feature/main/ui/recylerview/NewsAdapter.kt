package com.example.traveltaipeiapp.feature.main.ui.recylerview

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltaipeiapp.api.model.NewsItem
import com.example.traveltaipeiapp.databinding.ListItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = this.javaClass.simpleName

    var dataList: List<NewsItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            Log.d(TAG,"data changed")
            notifyDataSetChanged()
        }

    class ViewHolderNewsItem(val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        return ViewHolderNewsItem(
            ListItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderNewsItem) {
            val currentItem: NewsItem? = dataList.getOrNull(position)
            currentItem?.let { item ->
                holder.binding.apply {
                    newsTitleTv.text = item.title
                    newsContentTv.text = item.description
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}

