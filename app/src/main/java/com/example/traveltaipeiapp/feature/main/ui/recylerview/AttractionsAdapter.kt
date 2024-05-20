package com.example.traveltaipeiapp.feature.main.ui.recylerview

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.traveltaipeiapp.R
import com.example.traveltaipeiapp.api.model.AttractionItem
import com.example.traveltaipeiapp.databinding.ListItemAttractionsBinding

class AttractionsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = this.javaClass.simpleName

    var dataList: List<AttractionItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            Log.d(TAG,"data changed")
            notifyDataSetChanged()
        }

    class ViewHolderAttractionsItem(val binding: ListItemAttractionsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        return ViewHolderAttractionsItem(
            ListItemAttractionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderAttractionsItem) {
            val currentItem: AttractionItem? = dataList.getOrNull(position)
            currentItem?.let { item ->
                holder.binding.apply {
                    attractionName.text = item.name
                    attractionIntro.text = item.introduction

                    if (item.images.isNotEmpty()) {
                        val photoUrl = item.images[0].src
                        Glide.with(holder.itemView.context)
                            .load(photoUrl)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(photoIv)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}