package com.lalitpawar.shaadicomexample.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalitpawar.shaadicomexample.utils.extension.getParentActivity
import com.yuyakaido.android.cardstackview.CardStackView


@BindingAdapter("mutableAdapter")
fun setAdapter(view: CardStackView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableSrc")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.getContext())
        .load(url).apply(RequestOptions())
        .into(
            view
        )
}

@BindingAdapter("specialTag")
fun setSpecialTag(view: View, value: Any) {
    view.tag = value
}