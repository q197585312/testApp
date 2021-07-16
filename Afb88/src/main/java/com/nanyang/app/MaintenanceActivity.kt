package com.nanyang.app

import android.webkit.WebView
import com.nanyang.app.main.home.keno.WebActivity

class MaintenanceActivity : WebActivity() {

    override fun loadUrl(view: WebView?, url: String?) {
        if (url!!.contains("wfMain0.html"))
            finish()
        else
            super.loadUrl(view, url)

    }


}