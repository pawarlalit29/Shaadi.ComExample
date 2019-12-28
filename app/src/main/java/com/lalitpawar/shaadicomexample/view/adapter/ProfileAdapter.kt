package com.lalitpawar.shaadicomexample.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.lalitpawar.shaadicomexample.R
import com.lalitpawar.shaadicomexample.databinding.ItemProfileBinding
import com.lalitpawar.shaadicomexample.injection.ViewModelFactory
import com.lalitpawar.shaadicomexample.model.Result
import com.lalitpawar.shaadicomexample.view_model.ProfileItemViewModel


class ProfileAdapter(activity: AppCompatActivity) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    private var dataList = ArrayList<Result>()
    lateinit var binding: ItemProfileBinding
    var clickListener : setOnItemClickListener? = null
    var activity = activity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_profile, parent, false)
        binding.viewmodel = ViewModelProviders.of(activity, ViewModelFactory(activity)).get(ProfileItemViewModel::class.java)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.btnAccept.setOnClickListener { view ->
            clickListener?.onClickItem(view, dataList.get(position))
        }
    }

    fun updatePostList(songslist: List<Result>) {
        dataList.addAll(songslist)
        notifyDataSetChanged()
    }

    class ViewHolder( val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(dataItem: Result) {
            binding.viewmodel?.bind(dataItem)
        }

    }

    fun setOnItemClick(clickListener : setOnItemClickListener){
        this.clickListener = clickListener
    }

    interface setOnItemClickListener{
        fun onClickItem(view : View, results: Result)
    }
}