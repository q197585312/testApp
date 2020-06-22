package gaming178.com.casinogame.load

import android.os.Bundle
import gaming178.com.baccaratgame.R
import gaming178.com.casinogame.base.BaseActivity
import gaming178.com.mylibrary.allinone.util.AppTool

public class LoginActivity : BaseActivity() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        AppTool.setAppLanguage(mContext, AppTool.getAppLanguage(mContext))

    }
}