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
import kotlinx.android.synthetic.main.activity_login.login_password_edt
import kotlinx.android.synthetic.main.activity_login_jetpack.*


public class
LoginKtActivity : BaseKtActivity<LoginModel, ActivityLoginJetpackBinding>() {
    var onKey = View.OnKeyListener { v, keyCode, event -> // TODO Auto-generated method stub
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
        viewModel.beyondKeyboardLayout(ll_content,login_btn)
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

    }

}