package com.nanyang.app.main.home

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nanyang.app.*
import com.nanyang.app.Utils.BannerViewPagerNetAdapter
import com.nanyang.app.data.GamesData
import com.nanyang.app.data.Left
import com.nanyang.app.data.Main
import com.nanyang.app.Utils.StringUtils
import com.nanyang.app.common.LanguageHelper
import com.nanyang.app.common.MainPresenter
import com.nanyang.app.databinding.FragmentHomeTBinding
import com.nanyang.app.load.login.LoginInfo.LanguageWfBean
import com.nanyang.app.main.BaseSwitchFragment
import com.nanyang.app.main.home.HomeViewModel
import com.nanyang.app.main.home.OnItemClickListener
import com.unkonw.testapp.libs.base.BaseActivity
import com.unkonw.testapp.libs.base.BaseApplication

import com.unkonw.testapp.libs.presenter.IBasePresenter
import com.unkonw.testapp.libs.utils.LogUtil
import com.unkonw.testapp.libs.utils.TimeUtils
import com.unkonw.testapp.libs.utils.ToastUtils
import com.zhpan.bannerview.constants.IndicatorGravity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class HomeFragmentT() : BaseSwitchFragment<IBasePresenter>() {

    private lateinit var binding: FragmentHomeTBinding

    /*    @JvmField
        @BindView(R.id.in_layout)
        var inLayout: LinearLayout? = null*/
    private var jsonObjectNum: JSONObject? = null
    private var lastAllMainData: String? = null

    private var language: String = ""

    override fun onSetLayoutId(): Int {
        return R.layout.fragment_home_t
    }

    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentHomeTBinding>(
            inflater,
            R.layout.fragment_home_t,
            container,
            false
        )
        initView()
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.application = getBaseToolbarActivity().application
        var count = 3
        if (BuildConfig.FLAVOR == "ez2888") {
            count = 4;
        }
        binding.rvHead.layoutManager = GridLayoutManager(BaseApplication.getInstance(), count)
        viewModel.setItemClick(onItemClick())
        viewModel.setLeftItemClick(onLeftItemClick())
        viewModel.heightLeft = 90f
        viewModel.selectedType.observe(viewLifecycleOwner, {
            var index = 0
            viewModel.left.map { itMap ->
                itMap.isSelected = itMap.type == it
                viewModel.left.set(index, itMap)
                index++
            }
        })
        if (BuildConfig.FLAVOR == "usun") {
            binding.rvHead.visibility = View.INVISIBLE
            binding.rlBanner.visibility = View.VISIBLE
            (baseActivity.presenter as MainPresenter).getBanner(MainPresenter.CallBack {
                binding.bannerViewPager.setLifecycleRegistry(lifecycle)
                    .setAdapter(BannerViewPagerNetAdapter()).setScrollDuration(800)
                    .setIndicatorSliderColor(
                        Color.WHITE,
                        ContextCompat.getColor(
                            mContext,
                            R.color.yellow_gold
                        )
                    ).setIndicatorGravity(IndicatorGravity.CENTER).create(it)
            })
        } else {
            binding.rvHead.visibility = View.VISIBLE
            binding.rlBanner.visibility = View.GONE
        }
        return binding.root
    }

    private fun initUSUNLeftItemClick(left: List<Left>, itemLeft: Left) {
        for ((index, main) in left.withIndex()) {
            var type = itemLeft.type
            if (left[index].type == type) {
                left[index].img = left[index].imgSelect
            } else {
                left[index].img = left[index].imgNoSelect
            }
        }
    }

    private fun clearUSUNLeftItemClick(left: List<Left>) {
        for ((index, main) in left.withIndex()) {
            left[index].img = left[index].imgNoSelect
        }
    }

    private fun onLeftItemClick(): OnItemClickListener<Left> {
        return object : OnItemClickListener<Left> {
            override fun onItemClick(m: Left) {
                println("点几了$m")
                if (binding.rvContentDetail.visibility == View.VISIBLE) {
                    if (m.type == viewModel.selectedType.value) {
                        if (BuildConfig.FLAVOR == "usun") {
                            clearUSUNLeftItemClick(viewModel.left)
                        }
                        binding.rvContentDetail.visibility = View.GONE
                        viewModel.heightLeft = 120f
                        binding.rvContentType.adapter?.notifyDataSetChanged()
                    } else {
                        if (BuildConfig.FLAVOR == "usun") {
                            initUSUNLeftItemClick(viewModel.left, m)
                        }
                        viewModel.selectedType.postValue(m.type)
                        viewModel.loadMainGame(m.type)
                    }
                } else {
                    if (BuildConfig.FLAVOR == "usun") {
                        initUSUNLeftItemClick(viewModel.left, m)
                    }
                    binding.rvContentDetail.visibility = View.VISIBLE
                    viewModel.heightLeft = 90f
                    viewModel.selectedType.postValue(m.type)
                    viewModel.loadMainGame(m.type)
                    binding.rvContentType.adapter?.notifyDataSetChanged()
                }
            }

        }
    }

    private fun onItemClick(): OnItemClickListener<Main> {
        return object : OnItemClickListener<Main> {
            override fun onItemClick(item: Main) {

                val g = item.g
                if (getBaseToolbarActivity().app.updateOtherMap().containsKey(g)) {
                    if (g == "allCasino") {
                        viewModel.selectedType.postValue("casino")
                        viewModel.loadMainGame("casino")
                        binding.rvContentType.adapter?.notifyDataSetChanged()
                    } else {
                        (baseActivity.presenter as MainPresenter).clickGdGameItem(g)
                    }
                    return
                }
                val sportIdBean = getBaseToolbarActivity().app.getSportByG(g) ?: return
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

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadAllPic(::loadAllUi)
        updateTimer()
        initHomeToolBar()

    }

    fun loadAllUi(allBannerImagesBean: GamesData) {
        println("loadAllUi:" + allBannerImagesBean.toString())
    }

    override fun initData() {
        super.initData()
        language = LanguageHelper(mContext).language
    }


    private fun initHomeToolBar() {
        (baseActivity as BaseToolbarActivity<*>).toolbar!!.navigationIcon = null
        (baseActivity as BaseToolbarActivity<*>).toolbar!!.title = null
        (baseActivity as BaseToolbarActivity<*>).tvToolbarLeft.visibility = View.VISIBLE
        (baseActivity as BaseToolbarActivity<*>).tvToolbarRight!!.visibility = View.VISIBLE
        if (BuildConfig.FLAVOR == "ez2888") {
            (baseActivity as BaseToolbarActivity<*>).tvToolbarLeft.setBackgroundResource(0)
            (baseActivity as BaseToolbarActivity<*>).tvToolbarLeft.setTextColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.login_line_select_bg
                )
            )
            (baseActivity as BaseToolbarActivity<*>).tvToolbarLeft.text = "EZ2888"
        } else {
            (baseActivity as BaseToolbarActivity<*>).tvToolbarLeft.setBackgroundResource(R.mipmap.left_logo)
        }
        (baseActivity as BaseToolbarActivity<*>).llRight!!.visibility = View.VISIBLE
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initHomeToolBar()
            updateTimer()
        }
    }


    var hasInitNum = false
    private fun sortNotEmptyData() {
        if (BuildConfig.FLAVOR != "ez2888") {
            if (!viewModel.selectedType.value.equals("sport"))
                return
            var hasNum = false
            viewModel.mainContent.forEach() {
                hasNum = (hasData("M_RAm", it, true) || hasData("M_TAm", it, true) || hasData(
                    "M_EAm",
                    it,
                    true
                )) || hasNum
            }
            viewModel.mainContent.sort()
            viewModel.mainContent.sort()
            if (!hasInitNum && hasNum) {
                /*     viewModel.mainContent.clear()
                     viewModel.mainContent.addAll(temp)*/
            }
//        println("temp:$temp")
        }
        binding.rvContentDetail.adapter?.notifyDataSetChanged()
    }

    private fun hasData(m_rAm: String, next: Main, changeNum: Boolean): Boolean {
        if (jsonObjectNum == null)
            return false
        val string = jsonObjectNum!!.optString(m_rAm + next.dbid)
        var haNum = !StringUtils.isNull(string) && string != "0"
        if (haNum && changeNum)
            next.number = string
        else {
            next.number = "0"
        }
        return haNum
    }

    var languageItem: MenuItemInfo<String>? = null
    fun updateTimer() {
        languageItem = LanguageHelper(activity).languageItem
        updateHandler.post(timeUpdateRunnable)
        updateHandler.post(mainAllDataUpdateRunnable)
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

                    override fun onBack(data: String?) {
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
                            sortNotEmptyData()
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventShowBall) {
        if (viewModel.gameType == "sport") {
            viewModel.loadMainGame(viewModel.gameType)
        }
    }

 /*   @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventShowGame) {
        println("event:${event}")
        if (viewModel.gameType != "sport" && event.showBall.length > 1) {
            val split = event.showBall
            val filter = viewModel.mainContent.filter {
                var tem = it.g
                if (it.g.contains(" ")) {
                    tem = it.g.split(" ")[0]
                    println("tem:$tem")
                }
                split.contains(tem, true)
            }

            viewModel.mainContent.clear()
            viewModel.mainContent.addAll(filter)
            println("filter:${filter.size},$filter")
        }
    }*/
}