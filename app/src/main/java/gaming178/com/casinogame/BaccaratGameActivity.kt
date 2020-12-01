package gaming178.com.casinogame

import android.animation.Animator
import android.animation.ObjectAnimator
import com.zhy.autolayout.config.UseLandscape
import gaming178.com.casinogame.Activity.entity.GoodRoadDataBean
import butterknife.BindView
import gaming178.com.mylibrary.myview.View.GridBackgroundView
import gaming178.com.casinogame.Util.CountDownView
import gaming178.com.casinogame.Chat.FaceRelativeLayout
import com.apng.view.ApngImageView
import android.graphics.drawable.AnimationDrawable
import gaming178.com.casinogame.Activity.entity.ApngPlayBean
import cn.nodemedia.NodePlayerView
import android.os.Bundle
import gaming178.com.mylibrary.base.AdapterViewContent
import android.graphics.drawable.BitmapDrawable
import gaming178.com.casinogame.Util.ChipShowHelper
import com.unkonw.testapp.libs.widget.VideoHelper
import gaming178.com.casinogame.Control.PageWidgetT
import gaming178.com.casinogame.Util.HandlerCode
import gaming178.com.casinogame.Util.WebSiteUrl
import kotlin.jvm.Volatile
import gaming178.com.baccaratgame.R
import gaming178.com.casinogame.Util.FrontMuzicService
import gaming178.com.casinogame.Util.PopGoodRoad
import android.text.TextUtils
import android.view.animation.Animation
import com.zhy.autolayout.config.AutoLayoutConifg
import gaming178.com.casinogame.Util.AppConfig
import gaming178.com.casinogame.Activity.LobbyActivity
import gaming178.com.casinogame.Util.BackgroudMuzicService
import android.view.animation.TranslateAnimation
import gaming178.com.casinogame.Util.UIUtil
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import com.zhy.autolayout.AutoFrameLayout
import gaming178.com.casinogame.Activity.HomeGuideActivity
import gaming178.com.casinogame.Util.BetUiHelper
import gaming178.com.mylibrary.myview.miscwidgets.widget.Panel.OnPanelListener
import gaming178.com.mylibrary.myview.miscwidgets.interpolator.EasingType
import gaming178.com.mylibrary.base.QuickAdapterImp
import gaming178.com.mylibrary.base.ItemCLickImp
import com.apng.view.ApngLoader
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Color
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import gaming178.com.casinogame.Control.flipCallBack
import gaming178.com.casinogame.Util.ImageRotate3D
import gaming178.com.casinogame.Chat.ChatEmoji
import android.text.SpannableString
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import gaming178.com.casinogame.Chat.FaceConversionUtil
import gaming178.com.casinogame.Chat.MsgBean
import gaming178.com.mylibrary.lib.service.ISocketResponse
import gaming178.com.casinogame.Chat.ReceiveMsgBean
import butterknife.ButterKnife
import butterknife.OnClick
import com.apng.utils.FileUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhy.autolayout.utils.AutoUtils
import gaming178.com.baccaratgame.R2
import gaming178.com.baccaratgame.databinding.GdActivityTestBinding
import gaming178.com.casinogame.Activity.BaccaratActivity
import gaming178.com.casinogame.Bean.*
import gaming178.com.casinogame.Popupwindow.PopRule
import gaming178.com.casinogame.base.BaseActivity
import gaming178.com.mylibrary.allinone.util.*
import gaming178.com.mylibrary.base.ViewHolder
import gaming178.com.mylibrary.lib.service.Client
import gaming178.com.mylibrary.lib.service.Packet
import gaming178.com.mylibrary.lib.util.LogUtil
import gaming178.com.mylibrary.myview.miscwidgets.interpolator.BounceInterpolator
import gaming178.com.mylibrary.myview.miscwidgets.widget.Panel
import java.lang.Exception
import java.util.*
import java.util.concurrent.Executors

/**
 * Created by Administrator on 2016/4/11.
 */
class BaccaratGameActivity : BaseActivity(), UseLandscape {
    lateinit var mBinding:GdActivityTestBinding
    override fun getLayoutRes()=R.layout.gd_activity_test
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =  DataBindingUtil.setContentView(this, R.layout.gd_activity_test)
        mBinding.lifecycleOwner = this
    }

}