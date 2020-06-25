package gaming178.com.casinogame.load

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import gaming178.com.baccaratgame.R
import gaming178.com.baccaratgame.databinding.ActivityLoginJetpackBinding
import gaming178.com.casinogame.base.BaseKtActivity

public class LoginKtActivity : BaseKtActivity(R.layout.activity_login_jetpack) {
    private lateinit var viewModel:LoginModel
     lateinit var binding: ActivityLoginJetpackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel<LoginModel>()
        binding = DataBindingUtil.setContentView<ActivityLoginJetpackBinding>(
            this,
            R.layout.activity_login_jetpack
        )
        binding.lifecycleOwner=this
        binding.loginModel=viewModel
        binding.shareModel=mSharedViewModel


    }


    fun clickLogin(view: View) {

    }
}