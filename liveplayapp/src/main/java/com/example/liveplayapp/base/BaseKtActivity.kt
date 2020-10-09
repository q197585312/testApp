package gaming178.com.casinogame.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.unkonw.testapp.libs.base.BaseVMActivity


abstract class BaseKtActivity<VM : BaseApiViewModel, DB : ViewDataBinding> :
    BaseVMActivity<VM, DB>() {
    public lateinit var mAppViewModel: AppViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAppViewModel =
            getAppViewModelProvider().get<AppViewModel>(AppViewModel::class.java)
        viewModel.appViewModel= this.mAppViewModel
    }


}