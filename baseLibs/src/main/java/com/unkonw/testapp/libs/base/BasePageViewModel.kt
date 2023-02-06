package com.unkonw.testapp.libs.base

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.unkonw.testapp.BR


import me.tatarka.bindingcollectionadapter2.ItemBinding


/**
 * description 列表页面viewModel基类
 */
abstract class BasePageViewModel<T>() : BaseViewModel() {
    val items = ObservableArrayList<T>()
    var pageSize = 20
    val itemBinding by lazy {
        ItemBinding.of<T>(BR.item, getItemLayoutId()).bindExtra(BR.viewModel, this)
    }

    var curPage = MutableLiveData<Int>(1)

    /**
     * 获取item的布局ID
     */
    open fun getItemLayoutId(): Int = 0

    open fun requestData(page: Int) {}

    open fun getStartPageNum(): Int = 1

    fun refresh() {
        curPage.value = getStartPageNum()
        requestData(getStartPageNum())

    }

    suspend fun getAbc(): String {
        return "";
    }

    open fun loadMore() {
        curPage.value = curPage.value?.plus(1)
        requestData(curPage.value!!)
    }

    /**
     * 请求数据成功处理
     */
    fun handleItemData(page: Int, datas: List<T>) {
        if (page == getStartPageNum()) {
            //刷新完成
            items.clear()
        } else {
            //加载更多完成
        }

        items.addAll(datas)

        if (items.size == 0) {
            baseLiveData.dataEmpty.postValue(true)

        } else {
            //请求到数据且当前为空布局，需切换到列表状态
            baseLiveData.dataEmpty.postValue(false)
        }
    }

    /**
     * 请求数据失败处理
     */
    fun handleFail(page: Int) {
        handleEnd(page, 0)
        baseLiveData.loadFail.value = 1

        baseLiveData.dataEmpty.postValue(items.size == 0)

    }

    fun handleEnd(page: Int, total: Int) {
        if (page == getStartPageNum()) {
            //刷新完成
            baseLiveData.refreshEnd.value = 1
        } else {
            baseLiveData.loadMoreEnd.value = 1
        }
        if (total > items.size) {
            baseLiveData.loadMoreEnable.postValue(1)
        } else
            baseLiveData.loadMoreDisable.postValue(1)
    }

    fun setItemClick(itemClick:(v: View, data: T)->Unit) {
        itemBinding.bindExtra(BR.itemClick,object : BaseNoRepeatClickListener<T>() {
            override fun noRepeatClick(v: View, data: T) {
                itemClick.invoke(v,data)
            }
        })
    }

    /**有list page可能有刷新 和加载更多 直接调用设置smartRefreshLayout的状态监听*/
    fun smartRefreshObservable(smartRefreshLayout: SmartRefreshLayout) {
        smartRefreshLayout.apply {
            setOnRefreshListener {
                refresh()
            }
            setOnLoadMoreListener {
                loadMore()
            }
        }
        if (smartRefreshLayout.context is LifecycleOwner) {
            val lifecycleOwner = smartRefreshLayout.context as LifecycleOwner
            baseLiveData.apply {
                refreshEnd.observe(lifecycleOwner, Observer {
                    smartRefreshLayout.finishRefresh()
                })
                loadMoreEnd.observe(lifecycleOwner, Observer {
                    smartRefreshLayout.finishLoadMore()
                })
                loadMoreEnable.observe(lifecycleOwner) {
                    smartRefreshLayout.setEnableLoadMore(true)
                }
                loadMoreDisable.observe(lifecycleOwner) {
                    smartRefreshLayout.setEnableLoadMore(false)
                }
            }
        }

    }

    /**list数据的 page可能有 空布局 和list内容布局 切换的需求，直接调用*/
    fun dataEmptyObservable(emptyView: View, listContentView: RecyclerView) {
        if (listContentView.context is LifecycleOwner) {
            val lifecycleOwner = listContentView.context as LifecycleOwner

            baseLiveData.dataEmpty.observe(lifecycleOwner) {
                if (it) {
                    emptyView.visibility = View.VISIBLE
                    listContentView.visibility = View.GONE
                } else {
                    emptyView.visibility = View.GONE
                    listContentView.visibility = View.VISIBLE
                }
            }
        }


    }
}