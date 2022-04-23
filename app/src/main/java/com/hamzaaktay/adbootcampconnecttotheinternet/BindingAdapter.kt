package com.hamzaaktay.adbootcampconnecttotheinternet

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hamzaaktay.adbootcampconnecttotheinternet.network.CharacterModel
import com.hamzaaktay.adbootcampconnecttotheinternet.overview.CharactersAdapter
import com.hamzaaktay.adbootcampconnecttotheinternet.overview.HarryPotterApiStatus

@BindingAdapter ("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CharacterModel>?) {
    val adapter = recyclerView.adapter as CharactersAdapter
    adapter.submitList(data)
}

@BindingAdapter("harryPotterApiStatus")
fun bindStatus(statusImageView: ImageView, status: HarryPotterApiStatus?) {

    when (status) {
        HarryPotterApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        HarryPotterApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_cloud_off)
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage (imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context).load(imageUrl).apply (
            RequestOptions.placeholderOf(R.drawable.loading_animation).error(R.drawable.ic_baseline_broken_image)
        ).into(imageView)
    }
}