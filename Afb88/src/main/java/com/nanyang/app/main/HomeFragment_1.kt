package com.nanyang.app.main

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.nanyang.app.*
import com.nanyang.app.Utils.StringUtils
import com.nanyang.app.common.LanguageHelper
import com.nanyang.app.common.MainPresenter
import com.nanyang.app.load.login.LoginInfo.LanguageWfBean
import com.nanyang.app.load.welcome.AllBannerImagesBean
import com.nanyang.app.load.welcome.AllBannerImagesBean.MainBannersBean
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder
import com.unkonw.testapp.libs.utils.LogUtil
import com.unkonw.testapp.libs.utils.TimeUtils
import com.unkonw.testapp.libs.utils.ToastUtils
import org.json.JSONException
import org.json.JSONObject
import java.util.*
 q
    @BindView(R.id.in_layout)
    var inLayout: LinearLayout? = null
    private var jsonObjectNum: JSONObject? = null
    private var lastAllMainData: String? = null
    private var adapter: BaseRecyclerAdapter<MainBannersBean?>? =
        null
    private var language: String? = null
    private var mainList: List<MainBannersBean>? = null
    private var binding: ViewDataBinding? = null
    override fun onSetLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        super.initData()
        language = LanguageHelper(mContext).language
        initContentAdapter(ArrayList())
        loadAllPic()
    }

    override fun onCreateView( 
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContentView = inflater
            .inflate(onSetLayoutId(), container, false)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_1,
            container,
            false
        )
        initView()
        mSearchViewModel = getFragmentViewModelProvider(this).get(SearchViewModel::class.java)
        binding.setVm(mSearchViewModel)
        return binding.getRoot()
    }

    protected val appViewModelProvider: ViewModelProvider
        protected get() = (mActivity.getApplicationContext() as App).getAppViewModelProvider(
            mActivity
        )

    protected fun getFragmentViewModelProvider(fragment: Fragment): ViewModelProvider {
        return ViewModelProvider(fragment, fragment.getDefaultViewModelProviderFactory())
    }

    protected fun getActivityViewModelProvider(activity: AppCompatActivity): ViewModelProvider {
        return ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory())
    }

    override fun onResume() {
        super.onResume()
        if ((baseActivity.application as AfbApplication).listMainBanners != null) loadUi() else {
            loadAllPic()
        }
        updateTimer()
        initHomeToolBar()
    }

    private fun loadAllPic() {
        (getBaseToolbarActivity() as MainActivity).loadingUrlPics(object :
            MainPresenter.CallBack<AllBannerImagesBean?> {
            @Throws(JSONException::class)
            override fun onBack(data: AllBannerImagesBean) {
                loadUi()
            }
        })
    }

    private fun loadUi() {
        initViewPager((baseActivity.application as AfbApplication).listMainBanners)
        updateContent((baseActivity.application as AfbApplication).listMainPictures)
    }

    private fun initHomeToolBar() {
        (baseActivity as BaseToolbarActivity<*>).toolbar!!.navigationIcon = null
        (baseActivity as BaseToolbarActivity<*>).toolbar!!.title = null
        (baseActivity as BaseToolbarActivity<*>).tvToolbarLeft.visibility = View.VISIBLE
        (baseActivity as BaseToolbarActivity<*>).tvToolbarRight!!.visibility = View.VISIBLE
        (baseActivity as BaseToolbarActivity<*>).tvToolbarLeft.setBackgroundResource(R.mipmap.left_logo)
        (baseActivity as BaseToolbarActivity<*>).llRight!!.visibility = View.VISIBLE
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initHomeToolBar()
        }
    }

    private fun updateContent(data: MutableList<MainBannersBean>) {
        mainList = data
        val iterator = data.iterator()
        if (AppConstant.IS_AGENT) {
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (StringUtils.isNull(next.dbid)) iterator.remove()
            }
        }
        adapter!!.addAllAndClear(sortNotEmptyData(data))
    }

    private fun initContentAdapter(data: List<MainBannersBean>) {
        val layoutManager = GridLayoutManager(mContext, 3) //设置为一个3列的纵向网格布局
        rvContent!!.layoutManager = layoutManager
        adapter = object : BaseRecyclerAdapter<MainBannersBean?>(
            mContext,
            data,
            R.layout.home_sport_item_image_text
        ) {
            override fun convert(
                holder: MyRecyclerViewHolder,
                position: Int,
                item: MainBannersBean
            ) {
                val tv = holder.getView<TextView>(R.id.tv_text)
                val tv_num = holder.getView<TextView>(R.id.tv_num)
                holder.setImageByUrl(R.id.iv_pic, item.img)
                val sportIdBean =
                    getBaseToolbarActivity().app.getSportByG(item.g)
                if (sportIdBean != null && sportIdBean.textRes > 0) {
                    tv.text = getString(sportIdBean.textRes) //M_RAm1
                    val textColor = sportIdBean.textColor
                    tv.setTextColor(textColor)
                } else {
                    tv.text = "gd88Casino"
                }
                tv_num.visibility = View.GONE
                if (jsonObjectNum != null) {
                    if (!StringUtils.isNull(jsonObjectNum!!.optString("M_RAm" + item.dbid))) {
                        tv_num.text = jsonObjectNum!!.optString("M_RAm" + item.dbid)
                        tv_num.visibility = View.VISIBLE
                    } else if (!StringUtils.isNull(jsonObjectNum!!.optString("M_TAm" + item.dbid))) {
                        tv_num.text = jsonObjectNum!!.optString("M_TAm" + item.dbid)
                        tv_num.visibility = View.VISIBLE
                    } else if (!StringUtils.isNull(jsonObjectNum!!.optString("M_EAm" + item.dbid))) {
                        tv_num.text = jsonObjectNum!!.optString("M_EAm" + item.dbid)
                        tv_num.visibility = View.VISIBLE
                    }
                }
            }
        }
        rvContent!!.adapter = adapter
        adapter.setOnItemClickListener(object :
            BaseRecyclerAdapter.OnItemClickListener<MainBannersBean?> {
            override fun onItemClick(
                view: View,
                item: MainBannersBean,
                position: Int
            ) {
                val g = item.g
                if (getBaseToolbarActivity().app.updateOtherMap().containsKey(g)) {
                    (baseActivity.presenter as MainPresenter).clickGdGameItem(g)
                    return
                }
                val sportIdBean = getBaseToolbarActivity().app.getSportByG(g)
                var menuItemInfo =
                    MenuItemInfo<String?>(0, R.string.running)
                menuItemInfo.type = "Running"
                if (jsonObjectNum != null) {
                    if (!StringUtils.isNull(jsonObjectNum!!.optString("M_RAm" + item.dbid))) {
                        menuItemInfo =
                            MenuItemInfo(0, R.string.running)
                        menuItemInfo.setType("Running")
                    } else if (!StringUtils.isNull(jsonObjectNum!!.optString("M_TAm" + item.dbid))) {
                        menuItemInfo =
                            MenuItemInfo(0, R.string.Today)
                        menuItemInfo.setType("Today")
                    } else if (!StringUtils.isNull(jsonObjectNum!!.optString("M_EAm" + item.dbid))) {
                        menuItemInfo =
                            MenuItemInfo(0, R.string.Early_All)
                        menuItemInfo.type = "Early"
                    }
                }
                if (sportIdBean == null) {
                    ToastUtils.showLong("Server Error, wrong game GId")
                    return
                }
                menuItemInfo.setParent(sportIdBean.type)
                val b1 = Bundle()
                b1.putSerializable(AppConstant.KEY_DATA, menuItemInfo)
                skipAct(sportIdBean.cls, b1)
            }
        })
    }

    private fun sortNotEmptyData(data: List<MainBannersBean>?): List<MainBannersBean?> {
        val iterator = data!!.iterator()
        val hasData: MutableList<MainBannersBean> =
            ArrayList()
        val noData: MutableList<MainBannersBean> =
            ArrayList()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (hasData("M_RAm", next) || hasData("M_TAm", next) || hasData("M_EAm", next)) {
                hasData.add(next)
            } else {
                noData.add(next)
            }
        }
        val allData: MutableList<MainBannersBean?> =
            ArrayList()
        allData.addAll(hasData)
        allData.addAll(noData)
        return allData
    }

    private fun hasData(m_rAm: String, next: MainBannersBean): Boolean {
        if (jsonObjectNum == null) return false
        val string = jsonObjectNum!!.optString(m_rAm + next.dbid)
        return !StringUtils.isNull(string) && string != "0"
    }

    var languageItem: MenuItemInfo<String>? = null
    fun updateTimer() {
        languageItem = LanguageHelper(activity).languageItem
        updateHandler.post(timeUpdateRunnable)
        updateHandler.post(mainAllDataUpdateRunnable)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        updateHandler.removeCallbacks(timeUpdateRunnable)
        updateHandler.removeCallbacks(mainAllDataUpdateRunnable)
        updateHandler.removeCallbacksAndMessages(null)
    }

    var mainAllDataUpdateRunnable: Runnable = object : Runnable {
        override fun run() {
            (baseActivity.presenter as MainPresenter).loadAllMainData(
                LanguageWfBean(
                    "Getmenu",
                    language,
                    AppConstant.wfMain
                ), object : MainPresenter.CallBack<String?> {
                    override fun onBack(data: String) {
                        LogUtil.d(
                            "mainAllDataUpdateRunnable",
                            "得到数据——$data"
                        )
                        if (!TextUtils.isEmpty(lastAllMainData)) {
                            if (lastAllMainData == data) {
                                LogUtil.d(
                                    "mainAllDataUpdateRunnable",
                                    "相同——停止"
                                )
                                return
                            }
                        }
                        try {
                            LogUtil.d(
                                "mainAllDataUpdateRunnable",
                                "不同——刷新"
                            )
                            jsonObjectNum = JSONObject(data)
                            val list =
                                sortNotEmptyData(mainList)
                            adapter!!.addAllAndClear(list)
                            lastAllMainData = data
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                })
            updateHandler.postDelayed(this, 10000)
        }
    }
    var timeUpdateRunnable: Runnable = object : Runnable {
        override fun run() {
            val currentTime =
                getString(R.string.hk) + TimeUtils.getTime(
                    "dd MMM yyyy hh:mm:ss aa",
                    Locale.ENGLISH
                )
            val s1 = currentTime.replace("Jan".toRegex(), getString(R.string.Jan))
                .replace("Feb".toRegex(), getString(R.string.Feb))
                .replace("Mar".toRegex(), getString(R.string.Mar))
                .replace("Apr".toRegex(), getString(R.string.Apr))
                .replace("May".toRegex(), getString(R.string.May))
                .replace("Jun".toRegex(), getString(R.string.Jun))
                .replace("Jul".toRegex(), getString(R.string.Jul))
                .replace("Aug".toRegex(), getString(R.string.Aug))
                .replace("Oct".toRegex(), getString(R.string.Oct))
                .replace("Nov".toRegex(), getString(R.string.Nov))
                .replace("Dec".toRegex(), getString(R.string.Dec))
            val s = getString(R.string.gmt_8)
            (baseActivity as BaseToolbarActivity<*>).tvTime!!.text = "$s1 $s"
            updateHandler.postDelayed(this, 1000)
        }
    }
    var updateHandler = Handler()
}