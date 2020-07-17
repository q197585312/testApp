package gaming178.com.casinogame.base

import com.unkonw.testapp.libs.base.BaseApiService
import com.unkonw.testapp.libs.base.BaseViewModel

open class BaseApiViewModel : BaseViewModel() {
    var api: ApiService
    init {
        api = BaseApiService.getInstance().create()
    }
}