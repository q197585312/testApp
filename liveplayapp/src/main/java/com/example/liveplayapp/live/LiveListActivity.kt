package com.example.liveplayapp.live

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liveplayapp.R
import com.example.liveplayapp.databinding.LiveListActivityBinding
import gaming178.com.casinogame.base.BaseKtActivity

public class
LiveListActivity : BaseKtActivity<LiveListModel, LiveListActivityBinding>() {
    override fun layoutId(): Int {
        return R.layout.live_list_activity
    }
    val recyclerViewAdapter=RecyclerViewAdapter(mutableListOf<Cartoon>())

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.idItemRemoveRecyclerview.layoutManager=LinearLayoutManager(this)
        mBinding.idItemRemoveRecyclerview.adapter =recyclerViewAdapter

    }

    override fun initData() {

    }


    override fun onStart() {
        super.onStart()
//        viewModel.initListLive();
    }

    override fun registerDefUIChange() {
        super.registerDefUIChange()

    }
    fun initList(list:MutableList<Cartoon>){
        recyclerViewAdapter

    }

}