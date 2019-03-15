package com.nanyang.app.main.home.contact;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.Contact;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/3/14.
 */

public class ContactPresenter extends BaseRetrofitPresenter<ContactFragment> {
    private static final String TAG = "ContactPresenter";
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public ContactPresenter(ContactFragment iBaseContext) {
        super(iBaseContext);
    }

    public void getData(BaseConsumer<Contact> bc){
        doRetrofitApiOnUiThread(getService(ApiService.class).getContactData("http://www.appgd88.com/api/afb1188.php?app=afb1188"), bc);
    }

}
