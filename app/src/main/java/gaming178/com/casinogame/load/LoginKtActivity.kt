package gaming178.com.casinogame.load

import android.content.Context
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import gaming178.com.baccaratgame.R
import gaming178.com.baccaratgame.databinding.ActivityLoginJetpackBinding
import gaming178.com.casinogame.base.BaseKtActivity
import kotlinx.android.synthetic.main.activity_login_gd.login_password_edt
import kotlinx.android.synthetic.main.activity_login_jetpack.*


public class
LoginKtActivity : BaseKtActivity<LoginModel, ActivityLoginJetpackBinding>() {
    var onKey = View.OnKeyListener { v, keyCode, event ->
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val imm = v.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (imm.isActive) {
                imm.hideSoftInputFromWindow(v.applicationWindowToken, 0)
            }
            clickLogin(v)
            return@OnKeyListener true
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding?.loginModel = viewModel
        mBinding?.shareModel = mAppViewModel
        mBinding?.run {
            loginModel = viewModel
            shareModel = mAppViewModel
            if (mAppViewModel.isLiga365.value!!)
                viewModel.initMap()
        }

        mBinding?.shareModel?.run {
            isLiga365.observe(this@LoginKtActivity, Observer {
                print("test:${isLiga365.value}")
            })
        }

        viewModel.beyondKeyboardLayout(ll_content, login_btn)
        login_password_edt.setOnKeyListener(onKey)
    }


    fun clickLogin(view: View) {

    }

    fun leftClick() {
        Process.killProcess(Process.myPid()) //获取PID
        System.exit(0) //常规java、c#的标准退出法，返回值为0代表正常退出
    }

    override fun layoutId(): Int {
        return R.layout.activity_login_jetpack
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
//        initLanguagePop()

    }

/*    var popLanguage
    private fun initLanguagePop(v: View, weight: Float) {
        if (popLanguage == null) {
            popLanguage = object :
                PopChoiceLanguage<MenuItemInfo<String?>?>(
                    this@LoginKtActivity,
                    v,
                    ScreenUtil.getScreenWidthPix(this@LoginKtActivity) - ScreenUtil.dip2px(
                        this@LoginKtActivity,
                        20f
                    ),
                    ScreenUtil.dip2px(this@LoginKtActivity, 200f)
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
                        LanguageHelper(this@LoginKtActivity)
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
                        AppTool.setAppLanguage(this@LoginKtActivity, item.type)
                        recreate()
                    } else {
                        val screenWidth =
                            WidgetUtil.getPortraitScreenWidth(this@LoginKtActivity)
                        val width = screenWidth / 15 * 14
                        val type = item.type
                        val u: User = mAppViewModel.getUser()
                        when (type) {
                            "deposit" -> {
                                val pop = DepositPop(
                                    this@LoginKtActivity,
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
                                    this@LoginKtActivity,
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
                                    this@LoginKtActivity,
                                    v,
                                    width,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                                )
                                popReferrer.showPopupCenterWindow()
                            }
                            "katasandi" -> startActivity(
                                Intent(
                                    this@LoginKtActivity,
                                    ChangePasswordActivity::class.java
                                )
                            )
                            "Referral_List" -> {
                                val popReferralList =
                                    PopReferralList(this@LoginKtActivity, v, width, width)
                                popReferralList.showPopupCenterWindow()
                            }
                        }
                    }
                }

                override fun initData() {
                    super.initData()
                    recyclerView.layoutManager = GridLayoutManager(
                        this@LoginKtActivity,
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
                LanguageHelper(this@LoginKtAcitivity).languageItems
            )
        }
        darkenBackground(0.5f)
        popLanguage.showPopupWindowUpCenter(
            v,
            ScreenUtil.dip2px(this@LoginKtAcitivity, 200f),
            ScreenUtil.getScreenWidthPix(this@LoginKtAcitivity) - ScreenUtil.dip2px(
                this@LoginKtAcitivity,
                20f
            )
        )

    }*/

}