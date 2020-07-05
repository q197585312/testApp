package gaming178.com.casinogame.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.unkonw.testapp.libs.base.BaseVMActivity
import com.unkonw.testapp.libs.base.BaseViewModel


@SuppressLint("Registered")
abstract class BaseKtActivity<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseVMActivity<VM, DB>() {
    public lateinit var mAppViewModel: AppViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAppViewModel =
            getAppViewModelProvider().get<AppViewModel>(AppViewModel::class.java)
    }


}