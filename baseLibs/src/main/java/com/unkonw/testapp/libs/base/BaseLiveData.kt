package com.dxl.base.mvvm

import androidx.lifecycle.MutableLiveData
import com.unkonw.testapp.libs.base.SingleLiveEvent

/**
 * description 项目事件通知基类
 */
class BaseLiveData {

    /** 请求成功通知  */
    val loadSuccess by lazy { SingleLiveEvent<Int>() }

    /** 请求失败通知  */
    val loadFail by lazy { SingleLiveEvent<Int>() }

    /** 刷新 */
    val refreshEnd by lazy { SingleLiveEvent<Int>() }

    /** 加载更多 */
    val loadMoreEnd by lazy { SingleLiveEvent<Int>() }

    /** 是否有数据，无数据切换空布局 */
    val dataEmpty by lazy { MutableLiveData(true) }


    /** 可以加载更多 */
    val loadMoreEnable by lazy { SingleLiveEvent<Int>() }
    /** 不可以加载更多 */
    val loadMoreDisable by lazy { SingleLiveEvent<Int>() }
}