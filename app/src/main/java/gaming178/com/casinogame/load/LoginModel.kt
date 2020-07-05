package gaming178.com.casinogame.load

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import gaming178.com.baccaratgame.BuildConfig
import gaming178.com.casinogame.Activity.ChangePasswordActivity
import gaming178.com.casinogame.Bean.Liga365AgentBean
import gaming178.com.casinogame.Bean.User
import gaming178.com.casinogame.Popupwindow.DepositPop
import gaming178.com.casinogame.Popupwindow.PopReferralList
import gaming178.com.casinogame.Popupwindow.WithdrawPop
import gaming178.com.casinogame.Util.PopReferrer
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder
import gaming178.com.casinogame.base.BaseApiViewModel
import gaming178.com.casinogame.login.LanguageHelper
import gaming178.com.casinogame.login.MenuItemInfo
import gaming178.com.casinogame.login.PopChoiceLanguage
import gaming178.com.mylibrary.allinone.util.AppTool
import gaming178.com.mylibrary.allinone.util.ScreenUtil
import gaming178.com.mylibrary.allinone.util.WidgetUtil

class LoginModel : BaseApiViewModel() {
    val map = MutableLiveData<HashMap<String, String>>()

    init {

    }

    fun initMap() {
        launchGo({
            val agentUrl = "http://www.appgd88.com/liga365agengt.php"
            val liga365AgentBean = api.getSiteMap(agentUrl)
            val dataList: List<Liga365AgentBean.DataBean> =
                liga365AgentBean.getData()
            val siteMap = mutableMapOf<String, String>()
            for (i in dataList.indices) {
                val dataBean = dataList[i]
                val web = dataBean.web
                val agent = dataBean.agent
                siteMap[web] = agent
            }

        })

    }

    fun login() {
        launchUI {
//            launchFlow { api.getData("sdsdsd") }.flatMapConcat {  }
        }
    }

    public fun beyondKeyboardLayout(ll_container: View, login_login_btn: View) {
        // 监听根布局的视图变化
        ll_container.getViewTreeObserver().addOnGlobalLayoutListener(
            ViewTreeObserver.OnGlobalLayoutListener {
                val rect = Rect()
                // 获取内容布局在窗体的可视区域
                ll_container.getRootView().getWindowVisibleDisplayFrame(rect)
                // 获取内容布局在窗体的不可视区域高度(被其他View遮挡的区域高度)
                val rootInvisibleHeight: Int = ll_container.getHeight() - rect.bottom
                if (rootInvisibleHeight > 120) {
                    val location = IntArray(2)
                    login_login_btn.getLocationInWindow(location)
                    val screenHeight: Int = ll_container.getRootView().getHeight()
                    val softHeight = screenHeight - rect.bottom
                    //解决大屏幕会下移问题
                    if (location[1] + login_login_btn.getHeight() > rect.bottom) {
                        //                                scrollHeight = sc[1] + login_login_btn.getHeight() - (screenHeight - softHeight);//可以加个5dp的距离这样，按钮不会挨着输入法
                        //                                int buttonHeight = (location[1] + ll_container.getHeight()) - rect.bottom;
                        val buttonHeight: Int =
                            location[1] + login_login_btn.getHeight() - (screenHeight - softHeight)
                        scrollToPos(0, buttonHeight, ll_container)
                    } else {
                        scrollToPos(0, 0, ll_container)
                    }
                } else {
                    // 键盘隐藏
                    scrollToPos(0, 0, ll_container)
                }
            })
    }

    private fun scrollToPos(start: Int, end: Int, ll_container: View) {
        val animator =
            ValueAnimator.ofInt(start, end)
        animator.duration = 250
        animator.addUpdateListener { valueAnimator ->
            ll_container.scrollTo(
                0,
                valueAnimator.animatedValue as Int
            )
        }
        animator.start()
    }

    public fun clickLanguage(view: View) {

    }

  /*  fun showLanguagePop(v: View?, weight: Float) {
        if (popLanguage == null) {
            popLanguage = object :
                PopChoiceLanguage<MenuItemInfo<String?>?>(
                    mContext,
                    v,
                    ScreenUtil.getScreenWidthPix(mContext) - ScreenUtil.dip2px(mContext, 20f),
                    ScreenUtil.dip2px(mContext, 200f)
                ) {
                override fun onSetRcItemLayout(): Int {
                    return R.layout.item_language_selected
                }

                fun onConvert(
                    holder: MyRecyclerViewHolder,
                    position: Int,
                    item: MenuItemInfo<String?>
                ) {
                    val ivFlag =
                        holder.getView<ImageView>(R.id.iv_flag_country)
                    val tvContent =
                        holder.getView<TextView>(R.id.selectable_text_content_tv)
                    tvContent.text = item.text
                    ivFlag.setImageResource(item.res)
                    val itemLanguageSelected =
                        LanguageHelper(mContext)
                            .isItemLanguageSelected(item.type)
                    if (itemLanguageSelected) {
                        if (BuildConfig.FLAVOR == "gd88" || BuildConfig.FLAVOR == "liga365") {
                            tvContent.setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.mipmap.oval_blue_point_12,
                                0
                            )
                        } else {
                            tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                        }
                    } else {
                        tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    }
                }

                fun onClickItem(
                    item: MenuItemInfo<*>,
                    position: Int
                ) {
                    closePopupWindow()
                    if (BuildConfig.FLAVOR == "gd88" || BuildConfig.FLAVOR == "liga365") {
                        AppTool.setAppLanguage(this@BaseActivity, item.type)
                        recreate()
                    } else {
                        val screenWidth =
                            WidgetUtil.getPortraitScreenWidth(context as Activity)
                        val width = screenWidth / 15 * 14
                        val type = item.type
                        val u: User = afbApp.getUser()
                        when (type) {
                            "deposit" -> {
                                val pop = DepositPop(
                                    mContext,
                                    v,
                                    width,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                )
                                pop.setDialog(dialog)
                                pop.setUser(u)
                                pop.showPopupCenterWindow()
                            }
                            "withdraw" -> {
                                val p = WithdrawPop(
                                    mContext,
                                    v,
                                    width,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                )
                                p.setDialog(dialog)
                                p.setUser(u)
                                p.showPopupCenterWindow()
                            }
                            "referrer" -> {
                                val popReferrer = PopReferrer(
                                    mContext,
                                    v,
                                    width,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                )
                                popReferrer.showPopupCenterWindow()
                            }
                            "katasandi" -> startActivity(
                                Intent(
                                    mContext,
                                    ChangePasswordActivity::class.java
                                )
                            )
                            "Referral_List" -> {
                                val popReferralList =
                                    PopReferralList(mContext, v, width, width)
                                popReferralList.showPopupCenterWindow()
                            }
                        }
                    }
                }

                override fun initData() {
                    super.initData()
                    recyclerView.layoutManager = GridLayoutManager(
                        mContext,
                        2
                    )
                }

                override fun initView(view: View) {
                    super.initView(view)
                    val viewById =
                        view.findViewById<View>(R.id.view_weight1)
                    val params =
                        viewById.layoutParams as LinearLayout.LayoutParams
                    params.weight = weight
                    viewById.layoutParams = params
                }

                override fun onCloose() {
                    super.onCloose()
                    darkenBackground(1f)
                }

                override fun getContentViewLayoutRes(): Int {
                    return R.layout.popupwindow_language_select
                }
            }
            popLanguage.setData(
                LanguageHelper(mContext).languageItems
            )
        }
        darkenBackground(0.5f)
        popLanguage.showPopupWindowUpCenter(
            v,
            ScreenUtil.dip2px(mContext, 200f),
            ScreenUtil.getScreenWidthPix(mContext) - ScreenUtil.dip2px(mContext, 20f)
        )
    }*/
}