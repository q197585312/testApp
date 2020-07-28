package gaming178.com.casinogame.load

import android.animation.ValueAnimator
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import androidx.lifecycle.MutableLiveData
import com.unkonw.testapp.libs.utils.ToastUtils
import gaming178.com.casinogame.Bean.Liga365AgentBean
import gaming178.com.casinogame.base.BaseApiViewModel

class LoginModel : BaseApiViewModel() {
    val map = MutableLiveData<HashMap<String, String>>()
    var showLanguagePop = MutableLiveData<Boolean>()

    init {

    }

    fun initMap() {
        launchGo(block = {
            val agentUrl = "http://www.appgd88.com/liga365agengt.php"
            val liga365AgentBean = api.getSiteMap(agentUrl)
            println(liga365AgentBean.toString())
            val data = liga365AgentBean.data
            val siteMap = mutableMapOf<String, String>()
            for (i in data.indices) {
                val dataBean = data[i]
                val web = dataBean.web
                val agent = dataBean.agent
                siteMap[web] = agent
            }

        },
            error = {
                defUI.toastEvent.postValue(
                    "no agent !$it"
                )
            }
        )

    }

    fun login() {
        launchUI {
//            launchFlow { api.getData("sdsdsd") }.flatMapConcat {  }
        }
    }

    public fun beyondKeyboardLayout(ll_container: View, login_login_btn: View) {
        // 监听根布局的视图变化
        ll_container.getViewTreeObserver().addOnGlobalLayoutListener(
            ViewTreeObserver.OnGlobalLayoutListener {
                val rect = Rect()
                // 获取内容布局在窗体的可视区域
                ll_container.getRootView().getWindowVisibleDisplayFrame(rect)
                // 获取内容布局在窗体的不可视区域高度(被其他View遮挡的区域高度)
                val rootInvisibleHeight: Int = ll_container.getHeight() - rect.bottom
                if (rootInvisibleHeight > 120) {
                    val location = IntArray(2)
                    login_login_btn.getLocationInWindow(location)
                    val screenHeight: Int = ll_container.getRootView().getHeight()
                    val softHeight = screenHeight - rect.bottom
                    //解决大屏幕会下移问题
                    if (location[1] + login_login_btn.getHeight() > rect.bottom) {
                        //                                scrollHeight = sc[1] + login_login_btn.getHeight() - (screenHeight - softHeight);//可以加个5dp的距离这样，按钮不会挨着输入法
                        //                                int buttonHeight = (location[1] + ll_container.getHeight()) - rect.bottom;
                        val buttonHeight: Int =
                            location[1] + login_login_btn.getHeight() - (screenHeight - softHeight)
                        scrollToPos(0, buttonHeight, ll_container)
                    } else {
                        scrollToPos(0, 0, ll_container)
                    }
                } else {
                    // 键盘隐藏
                    scrollToPos(0, 0, ll_container)
                }
            })
    }

    private fun scrollToPos(start: Int, end: Int, ll_container: View) {
        val animator =
            ValueAnimator.ofInt(start, end)
        animator.duration = 250
        animator.addUpdateListener { valueAnimator ->
            ll_container.scrollTo(
                0,
                valueAnimator.animatedValue as Int
            )
        }
        animator.start()
    }

    public fun clickLanguage(view: View) {

    }

}