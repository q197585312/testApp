package com.unkonw.testapp.libs.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.unkonw.testapp.libs.utils.LogUtil.getMethodName
import com.unkonw.testapp.libs.utils.ToastUtils
import com.unkonw.testapp.libs.widget.DialogLoading
import java.lang.reflect.ParameterizedType

abstract class BaseVMActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    private var loading: DialogLoading? = null
    public lateinit var viewModel: VM

    public var mBinding: DB? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding()
        lifecycle.addObserver(viewModel)
        //注册 UI事件
        registorDefUIChange()
        initView(savedInstanceState)
        initData()
    }

    abstract fun layoutId(): Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData()


    /**
     * DataBinding
     */
    private fun initViewDataBinding() {
        val cls =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<*>
        if (ViewDataBinding::class.java != cls && ViewDataBinding::class.java.isAssignableFrom(cls)) {
            mBinding = DataBindingUtil.setContentView(this, layoutId())
            mBinding?.lifecycleOwner = this
        } else setContentView(layoutId())
        createViewModel()
    }


    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        viewModel.defUI.showDialog.observe(this, Observer {
            println("showDialog $it")
            showLoading()
        })
        viewModel.defUI.dismissDialog.observe(this, Observer {
            getMethodName()
            dismissLoading()
        })
        viewModel.defUI.toastEvent.observe(this, Observer {
            getMethodName()
            ToastUtils.showShort(it)
        })

    }


    /**
     * 打开等待框
     */
    private fun showLoading() {
        if (loading == null) {
            loading = DialogLoading(this)
        }
        loading?.run {
            if (!isFinishing)
                show()
        }

    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        loading?.run { if (isShowing) dismiss() }
    }


    /**
     * 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val tp = type.actualTypeArguments[0]
            val tClass = tp as? Class<VM> ?: BaseViewModel::class.java
            viewModel = getActivityViewModelProvider().get(tClass) as VM
        }
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        var app = application as IViewModelStoreOwner
        return ViewModelProvider(app.getViewModelStore(), app.getViewModelFactory())
    }

    fun getActivityViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.defaultViewModelProviderFactory)
    }
}