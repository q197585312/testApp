package com.nanyang.app.main.home.sport.main.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import com.nanyang.app.BR
import com.nanyang.app.databinding.LayoutGridRecycleContentBinding
import com.unkonw.testapp.libs.base.BaseApplication

class BetNumView(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {
    lateinit var gridContent: LayoutGridRecycleContentBinding
    lateinit var viewModel: BetNumViewModel

    init {
        initView()
    }

    private fun initView() {
        viewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(BaseApplication.getInstance()).create(BetNumViewModel::class.java)
        gridContent = LayoutGridRecycleContentBinding.inflate(LayoutInflater.from(context), this, true)
        gridContent.setVariable(BR.viewModel,viewModel)
    }


}