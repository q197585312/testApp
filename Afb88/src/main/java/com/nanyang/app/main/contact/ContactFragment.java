package com.nanyang.app.main.contact;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.BaseSwitchPresenter;
import com.nanyang.app.main.BetCenter.Bean.Contact;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 47184 on 2019/3/14.
 */

public class ContactFragment extends BaseMoreFragment<BaseSwitchPresenter> {
    private static final String TAG = "ContactFragment";
    @Bind(R.id.contact_list)
    RecyclerView rvContent;
    @Bind(R.id.contact_list_2)
    RecyclerView rvContent2;

    List<Contact.ContactBean> dataList = new ArrayList<>();
    List<Contact.ContactBean> dataList1 = new ArrayList<>();
    BaseRecyclerAdapter<Contact.ContactBean> adapter;
    BaseRecyclerAdapter<Contact.ContactBean> adapter1;

    BaseToolbarActivity aty;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    public void initData() {
        super.initData();
        setBackTitle(getString(R.string.contact));
        createPresenter(new BaseSwitchPresenter(this));
        aty = (BaseToolbarActivity) getActivity();
        MyLinearLayoutManager mLayoutManager = new MyLinearLayoutManager(mContext);//设置为一个纵向网格布局
        rvContent.setLayoutManager(mLayoutManager);
        MyLinearLayoutManager mLayoutManager1 = new MyLinearLayoutManager(mContext);
        rvContent2.setLayoutManager(mLayoutManager1);

        presenter.getContactData(new BaseConsumer<Contact>(mContext) {
            @Override
            protected void onBaseGetData(Contact data) {
                for (Contact.ContactBean contactBean : data.getContact()) {
                    if (contactBean.getLayout_type().equals("1")) {
                        dataList.add(contactBean);
                    } else {
                        dataList1.add(contactBean);
                    }
                }
                adapter.setData(dataList);
                adapter1.setData(dataList1);
            }
        });
        adapter = new BaseRecyclerAdapter<Contact.ContactBean>(mContext, dataList, R.layout.item_contact) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, Contact.ContactBean item) {
                BaseRecyclerAdapter<Contact.ContactBean> adapter1 = (BaseRecyclerAdapter<Contact.ContactBean>) rvContent.getAdapter();
                Contact.ContactBean c = null;
                if (position < adapter1.getItemCount()) {
                    c = adapter1.getItem(position + 1);
                }
                contartCtrl(holder, position, item, c);
            }
        };
        adapter1 = new BaseRecyclerAdapter<Contact.ContactBean>(mContext, dataList1, R.layout.item_contact) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, Contact.ContactBean item) {
                BaseRecyclerAdapter<Contact.ContactBean> adapter1 = (BaseRecyclerAdapter<Contact.ContactBean>) rvContent.getAdapter();
                Contact.ContactBean c = null;
                if (position < adapter1.getItemCount()) {
                    c = adapter1.getItem(position + 1);
                }
                contartCtrl(holder, position, item, c);
            }
        };
        rvContent.setAdapter(adapter);
        rvContent2.setAdapter(adapter1);
    }

    private void contartCtrl(MyRecyclerViewHolder holder, int position, Contact.ContactBean item, Contact.ContactBean nextItem) {
        if (item.getType().equals("1")) {
            LinearLayout ll = holder.getLinearLayout(R.id.content_line);
            ll.setVisibility(View.GONE);
            ImageView ivRight = holder.getImageView(R.id.contact_imgRight);
            ivRight.setVisibility(View.INVISIBLE);
            ImageView ivLeft = holder.getImageView(R.id.contact_imgLeft);
            ivLeft.setVisibility(View.VISIBLE);
            TextView tv = holder.getTextView(R.id.contact_text);
            tv.setText(item.getTitle());
            tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv.setTextColor(getResources().getColor(R.color.black));
            switch (item.getIcon_type()) {
                case "1":
                    ivLeft.setImageResource(R.mipmap.skype);
                    break;
                case "2":
                    ivLeft.setImageResource(R.mipmap.wechat);
                    break;
                case "3":
                    ivLeft.setImageResource(R.mipmap.line);
                    break;
                case "4":
                    ivLeft.setImageResource(R.mipmap.zalo);
                    break;
                case "5":
                    ivLeft.setImageResource(R.mipmap.phone);
                    break;
                case "6":
                    ivLeft.setImageResource(R.mipmap.email);
                    break;
                default:
            }
        } else {
            LinearLayout ll = holder.getLinearLayout(R.id.content_line);
            if (nextItem != null && nextItem.getType().equals("1")) {
                ll.setVisibility(View.VISIBLE);
            } else {
                ll.setVisibility(View.GONE);
            }
            ImageView ivRight = holder.getImageView(R.id.contact_imgRight);
            ivRight.setVisibility(View.VISIBLE);
            ImageView ivLeft = holder.getImageView(R.id.contact_imgLeft);
            ivLeft.setVisibility(View.INVISIBLE);
            TextView tv = holder.getTextView(R.id.contact_text);
            tv.setText(item.getTitle());
            tv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv.setTextColor(getResources().getColor(R.color.contact_font));
            switch (item.getIcon_type()) {
                case "1":
                    ivRight.setImageResource(R.mipmap.message);
                    break;
                case "2":
                    ivRight.setImageResource(R.mipmap.phone_msg);
                    break;
                case "3":
                    ivRight.setImageResource(R.mipmap.email_msg);
                    break;
                default:
            }
        }
    }
}
