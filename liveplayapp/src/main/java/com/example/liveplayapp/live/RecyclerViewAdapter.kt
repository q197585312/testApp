package com.example.liveplayapp.live

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.liveplayapp.R
import com.example.liveplayapp.databinding.LivePlayItemBinding

class RecyclerViewAdapter(private var cartoonList: MutableList<Cartoon>) : RecyclerView.Adapter<RecyclerViewAdapter.TestViewHolder>() {

    override fun getItemCount(): Int  = cartoonList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val binding = DataBindingUtil.inflate<LivePlayItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.live_play_item,
            parent,
            false
        )
        return TestViewHolder(binding)    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(cartoonList[position])    }

    class TestViewHolder(private val binding: LivePlayItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Cartoon) {
            //方法一：
//            binding.setVariable(BR.user,data)
            //方法二：
            binding.itemModel = data
            binding.executePendingBindings()
        }
    }
}