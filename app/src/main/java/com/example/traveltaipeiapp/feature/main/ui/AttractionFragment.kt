package com.example.traveltaipeiapp.feature.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.traveltaipeiapp.R
import com.example.traveltaipeiapp.common.BaseFragment
import com.example.traveltaipeiapp.databinding.FragmentAttractionBinding
import com.example.traveltaipeiapp.feature.main.ui.MainActivity.Companion.mainRepository
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModel
import com.example.traveltaipeiapp.feature.main.viewmodel.MainViewModelFactory

class AttractionFragment : BaseFragment<FragmentAttractionBinding>() {

    private val TAG = this.javaClass.simpleName
    companion object {
        private var name: String = ""
        private var imageSrc: String = ""
        private var openTime: String = ""
        private var address: String = ""
        private var tel: String = ""
        private var website: String = ""
        private var description: String = ""
    }

    override val model: MainViewModel by activityViewModels{
        MainViewModelFactory(mainRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name", "")
            imageSrc = it.getString("imageSrc", "")
            openTime = it.getString("openTime", "")
            address = it.getString("address", "")
            tel = it.getString("tel", "")
            website = it.getString("website", "")
            description = it.getString("description", "")
        }
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAttractionBinding {
        return FragmentAttractionBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.apply {
            toolbarSpace.apply {
                title = name
                setOnClickListener {
                    findNavController().navigateUp()
                }
            }

            websiteCardView.setOnClickListener {
                navigateToWebViewFragment(binding.websiteTv.text.toString(), binding.toolbarSpace.title.toString())
            }

            if (imageSrc.isNotEmpty()) {
                Glide.with(requireContext())
                    .load(imageSrc)
                    .placeholder(R.mipmap.ic_launcher)
                    .apply(RequestOptions.overrideOf(800, 600))
                    .into(attractionPhotoIv)
            } else {
                val placeholderUrl = "https://placehold.co/600x400.png"
                Glide.with(requireContext())
                    .load(placeholderUrl)
                    .into(attractionPhotoIv)
            }
            openTimeTv.text = openTime.ifBlank { "No Data" }
            addressTv.text = address.ifBlank { "No Data" }
            telTv.text = tel.ifBlank { "No Data" }
            websiteTv.text = website.ifBlank { "No Data" }
            descriptionTv.text = description.ifBlank { "No Data" }
        }
    }

    override fun initObserve() {}

    override fun changeLanguage() {}

    private fun navigateToWebViewFragment(url: String, title: String) {
        val bundle = bundleOf("url" to url, "title" to title)
        findNavController().navigate(R.id.action_attractionFragment_to_webviewFragment, bundle)
    }
}