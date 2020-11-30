package gaming178.com.casinogame.load

import android.content.Context
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import cn.finalteam.toolsfinal.DeviceUtils
import com.unkonw.testapp.libs.base.BasePopupWindow
import gaming178.com.baccaratgame.R
import gaming178.com.baccaratgame.databinding.GdActivityLoginJetpackBinding
import gaming178.com.baccaratgame.databinding.GdPopLanguageSelectLayoutBinding
import gaming178.com.casinogame.base.BaseKtActivity
import gaming178.com.casinogame.login.LanguageHelper
import gaming178.com.mylibrary.allinone.util.AppTool
import gaming178.com.mylibrary.allinone.util.ScreenUtil
import kotlinx.android.synthetic.main.gd_activity_login_jetpack.*


public class
LoginKtActivity : BaseKtActivity<LoginModel, GdActivityLoginJetpackBinding>() {
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

        mBinding?.run {
            loginModel = viewModel

        }
        viewModel.beyondKeyboardLayout(gd__ll_content, gd__login_btn)
        gd__login_password_edt.setOnKeyListener(onKey)
    }

    override fun onResume() {
        super.onResume()
        if (mAppViewModel.isLiga365.value!!)
            viewModel.initMap()
    }


    fun clickLogin(view: View) {

    }

    fun leftClick() {
        Process.killProcess(Process.myPid()) //获取PID
        System.exit(0) //常规java、c#的标准退出法，返回值为0代表正常退出
    }

    override fun layoutId(): Int {
        return R.layout.gd_activity_login_jetpack
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        initLanguagePop()
    }

    lateinit var popLanguage: BasePopupWindow<GdPopLanguageSelectLayoutBinding>
    private fun initLanguagePop() {
        popLanguage =
            object : BasePopupWindow<GdPopLanguageSelectLayoutBinding>(
                this@LoginKtActivity,
                gd__ll_choose_language,
                DeviceUtils.getScreenPix(this@LoginKtActivity).widthPixels - DeviceUtils.dip2px(
                    this@LoginKtActivity,
                    20f
                ),
                DeviceUtils.dip2px(this@LoginKtActivity, 200f)
            ) {
                override fun onSetLayoutRes(): Int {
                    return return R.layout.gd_pop_language_select_layout
                }

                override fun initView(view: View) {
                    super.initView(view)
                    mBinding?.lifecycleOwner = this@LoginKtActivity
                    val model = this@LoginKtActivity.getActivityViewModelProvider()
                        .get(LanguageModel::class.java)
                    mBinding?.languageModel = model
                    mBinding?.languageModel?.items?.clear()
                    mBinding?.languageModel?.items?.addAll(
                        LanguageHelper(
                            this@LoginKtActivity
                        ).languageItems
                    )
                    mBinding?.languageModel?.languageType?.value =
                        AppTool.getAppLanguage(this@LoginKtActivity)
                    mBinding?.languageModel?.languageType?.observe(this@LoginKtActivity, Observer {
                        mBinding!!.gdBaseRv.adapter?.notifyDataSetChanged()
                    })

                }
            }
        viewModel.showLanguagePop.observe(this, Observer {
            if (it)
                popLanguage.showPopupWindowUpCenter(
                    gd__tv_register,
                    ScreenUtil.dip2px(this, 200f),
                    ScreenUtil.getScreenWidthPix(this) - ScreenUtil.dip2px(this, 20f)
                )
            else {
                popLanguage.closePopupWindow()
            }
        })
    }

    fun clickLanguage(view: View) {
        viewModel.showLanguagePop.value = true
    }


    /* private fun initLanguagePop(v: View, weight: Float) {
         if (popLanguage == null) {
             popLanguage = object :
                 PopChoiceLanguage<MenuItemInfo<String?>?>(
                     this@LoginKtActivity,
                     v,
                     DeviceUtils.getScreenPix(this@LoginKtActivity).widthPixels - DeviceUtils.dip2px(
                         this@LoginKtActivity,
                         20f
                     ),
                     DeviceUtils.dip2px(this@LoginKtActivity, 200f)
                 ) {
                 override fun onSetRcItemLayout(): Int {
                     return R.layout.gd_item_language_selected
                 }

                 public override fun onConvert(
                     holder: MyRecyclerViewHolder?,
                     position: Int,
                     item: MenuItemInfo<String?>?
                 ) {
                     val ivFlag =
                         holder?.getView<ImageView>(R.id.gd__iv_flag_country)
                     val tvContent =
                         holder?.getView<TextView>(R.id.gd__selectable_text_content_tv)
                     tvContent?.text = item!!.text
                     ivFlag?.setImageResource(item.res!!)
                     val itemLanguageSelected =
                         LanguageHelper(this@LoginKtActivity)
                             .isItemLanguageSelected(item.type)
                     if (itemLanguageSelected) {
                         if (BuildConfig.FLAVOR == "gd88" || BuildConfig.FLAVOR == "liga365") {
                             tvContent?.setCompoundDrawablesWithIntrinsicBounds(
                                 0,
                                 0,
                                 R.mipmap.oval_blue_point_12,
                                 0
                             )
                         } else {
                             tvContent?.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                         }
                     } else {
                         tvContent?.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                     }
                 }

                 public override  fun onClickItem(
                     item: MenuItemInfo<*>?,
                     position: Int
                 ) {
                     closePopupWindow()
                     if (BuildConfig.FLAVOR == "gd88" || BuildConfig.FLAVOR == "liga365") {
                         SystemTool.switchLanguage(item.type, this@LoginKtActivity)
                         recreate()
                     } else {
                         val screenWidth =
                             DeviceUtils.getScreenPix(this@LoginKtActivity).widthPixels
                         val width = screenWidth / 15 * 14
                         val type = item.type
                         val u: User? = mAppViewModel.user.value
                         when (type) {
                             "deposit" -> {
                                 val pop = DepositPop(
                                     this@LoginKtActivity,
                                     v,
                                     width,
                                     LinearLayout.LayoutParams.WRAP_CONTENT
                                 )
                                 pop.setDialog(popLanguage)
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
                                 p.setDialog(popLanguage)
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
                         view.findViewById<View>(R.id.gd__view_weight1)
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

     }

     var popLanguage: PopChoiceLanguage*/

}

