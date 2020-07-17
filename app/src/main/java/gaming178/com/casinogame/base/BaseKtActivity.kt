package gaming178.com.casinogame.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@SuppressLint("Registered")
public open class BaseKtActivity(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId) {


    public lateinit var mSharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSharedViewModel = getAppViewModelProvider().get<SharedViewModel>(SharedViewModel::class.java)
    }
    public inline fun <reified T : ViewModel> getViewModel(): T {
       return getActivityViewModelProvider().get(T::class.java)
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        var app = application as IViewModelStoreOwner
        return ViewModelProvider(app.getViewModelStore(), app.getViewModelFactory())
    }

    fun getActivityViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.defaultViewModelProviderFactory)
    }


}