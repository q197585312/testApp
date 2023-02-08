package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.MainTabActivity;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Home.MenuWithdrawFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;

/**
 * Created by Administrator on 2015/12/21. (取款)
 */
public class WithdrawCenterFragment extends BaseFragment {
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    @BindView(R.id.ll_parent)
    LinearLayout llParent;
    @BindView(R.id.ll_title)
    View llTitle;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    BaseRecyclerAdapter<GameMenuItem> adapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_withdraw_center;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (baseActivity instanceof MainTabActivity) {
            llParent.setBackgroundResource(R.mipmap.base_bg);
            tvToolbarTitle.setText(getString(R.string.qukuanzx));
            llTitle.setVisibility(View.VISIBLE);
        }
        getAct().setTitle(getString(R.string.qukuanzx));
        initAdapter();
    }

    private List<GameMenuItem> getContentData() {
        List<GameMenuItem> list = new ArrayList<>();
        list.add(new GameMenuItem(R.drawable.qukuancenter, getString(R.string.withdraw), MenuWithdrawFragment.class.getName()));
        list.add(new GameMenuItem(R.drawable.xiazhujilu, getString(R.string.qukuanlist), WithdrawListFragment.class.getName()));
        if (!getCurrency().equals("IDR")) {
            list.add(new GameMenuItem(R.drawable.qukuancenter, getString(R.string.Fixed_Bank), Funplay26BountBankFragment.class.getName()));
        }
        return list;
    }

    private void initAdapter() {
        adapter = new BaseRecyclerAdapter<GameMenuItem>(mContext, getContentData(), R.layout.item_common_list_content) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, GameMenuItem item) {
                ImageView imgLeft = holder.getView(R.id.img_left);
                imgLeft.setImageResource(item.getDrawableRes());
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(item.getTitle());
                View viewLine = holder.getView(R.id.view_line);
                if (getItemCount() - 1 == position) {
                    viewLine.setVisibility(View.GONE);
                } else {
                    viewLine.setVisibility(View.VISIBLE);
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<GameMenuItem>() {
            @Override
            public void onItemClick(View view, GameMenuItem item, int position) {
                Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                String value = item.getValue();
                if (!getCurrency().equals("IDR")) {
                    if (value.equals(MenuWithdrawFragment.class.getName())) {
                        VipInfoBean info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
                        String nullStr = info.getBank_name();
                        nullStr = info.getAddress();
                        if (TextUtils.isEmpty(nullStr)) {
                            value = Funplay26BountBankFragment.class.getName();
                        }
                    }
                }
                intent.putExtra("type", value);
                startActivity(intent);
            }
        });
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }
}
