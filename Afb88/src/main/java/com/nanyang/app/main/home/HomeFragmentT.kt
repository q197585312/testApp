package com.nanyang.app.main

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nanyang.app.*
import com.nanyang.app.data.GamesData
import com.nanyang.app.data.Left
import com.nanyang.app.data.Main
import com.nanyang.app.Utils.StringUtils
import com.nanyang.app.common.LanguageHelper
import com.nanyang.app.common.MainPresenter
import com.nanyang.app.databinding.FragmentHomeTBinding
import com.nanyang.app.load.login.LoginInfo.LanguageWfBean
import com.nanyang.app.load.welcome.AllBannerImagesBean
import com.nanyang.app.load.welcome.AllBannerImagesBean.MainBannersBean
import com.nanyang.app.main.home.HomeViewModel
import com.nanyang.app.main.home.OnItemClickListener
import com.unkonw.testapp.libs.base.BaseApplication

import com.unkonw.testapp.libs.presenter.IBasePresenter
import com.unkonw.testapp.libs.utils.LogUtil
import com.unkonw.testapp.libs.utils.TimeUtils
import com.unkonw.testapp.libs.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_sport.view.*
import kotlinx.android.synthetic.main.fragment_home_t.view.*
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

    private var language: String? = null

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
        binding.rvHead.layoutManager = GridLayoutManager(BaseApplication.getInstance(), 3)
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



        return binding.root
    }

    private fun onLeftItemClick(): OnItemClickListener<Left> {
        return object : OnItemClickListener<Left> {
            override fun onItemClick(m: Left) {
                println("点几了$m")
                if (binding.rvContentDetail.visibility == View.VISIBLE) {
                    if (m.type == viewModel.selectedType.value) {
                        binding.rvContentDetail.visibility = View.GONE
                        viewModel.heightLeft = 120f
                        binding.rvContentType.adapter?.notifyDataSetChanged()
                    } else {
                        viewModel.selectedType.postValue(m.type)
                        viewModel.loadMainGame(m.type)
                    }
                } else {
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
                    (baseActivity.presenter as MainPresenter).clickGdGameItem(g)
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


    private fun loadAllPic() {
        (getBaseToolbarActivity() as MainActivity).loadingUrlPics(object :
            MainPresenter.CallBack<AllBannerImagesBean?> {
            @Throws(JSONException::class)
            override fun onBack(data: AllBannerImagesBean?) {
                loadUi()
            }
        })
    }

    private fun loadUi() {
        /*initViewPager((baseActivity.application as AfbApplication).listMainBanners)
        updateContent((baseActivity.application as AfbApplication).listMainPictures)*/
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


    private fun initContentAdapter(data: List<MainBannersBean>) {
/*
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
*/
    }

    var hasInitNum = false
    private fun sortNotEmptyData() {
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
}