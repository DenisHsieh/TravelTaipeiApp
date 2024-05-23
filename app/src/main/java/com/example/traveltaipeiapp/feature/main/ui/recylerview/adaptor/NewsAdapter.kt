package com.example.traveltaipeiapp.feature.main.ui.recylerview.adaptor

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltaipeiapp.api.model.NewsItem
import com.example.traveltaipeiapp.Listener.ClickListener
import com.example.traveltaipeiapp.databinding.ListItemNewsBinding

class NewsAdapter(val clickListener: ClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

                    newsTitleTv.text = item.title.let {
                        if (it.length > 20) {
                            it.trim().substring(0, 20).plus("...")
                        } else {
                            it
                        }
                    }
                    newsContentTv.text = item.description.let {
                        if (it.length > 70) {
                            it.trim().substring(0, 20).plus("...")
                        } else {
                            it
                        }
                    }
                }
                holder.binding.newsItem.setOnClickListener {
                    clickListener.onItemClick(holder.binding.newsItem, position)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun getNewsItemAtPosition(position: Int): NewsItem {
        return dataList[position]
    }

}

