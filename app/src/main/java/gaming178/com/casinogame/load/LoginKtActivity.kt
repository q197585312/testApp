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


}

