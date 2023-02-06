package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Activity.CommonDataListActivity;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/21. (红利中心)
 */
public class BonusCenterFragment extends BaseFragment {
    @Bind(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<GameMenuItem> adapter;


    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_dividend_center;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setleftViewEnable(true);
        getAct().setTitle(getString(R.string.honglizx));
        initAdapter();
    }

    private List<GameMenuItem> getContentData() {
        List<GameMenuItem> list = new ArrayList<>();
        list.add(new GameMenuItem(R.drawable.honglicenter, getString(R.string.honglizx), BonusTransferFragment.class.getName()));
        if (!BuildConfig.FLAVOR.equals("fun77")) {
            list.add(new GameMenuItem(R.drawable.tuijianliebiao, getString(R.string.beituijianlist), BonusReferralListFragment.class.getName()));
            list.add(new GameMenuItem(R.drawable.shengqinghongli, getString(R.string.shengqinghl), BonusUpdateFragment.class.getName()));
            list.add(new GameMenuItem(R.drawable.hongliliebiao, getString(R.string.shengqinglist), BonusApplyListFragment.class.getName()));
        }
        list.add(new GameMenuItem(R.drawable.hongliliebiao, getString(R.string.Promotion_list), null));
        list.add(new GameMenuItem(R.drawable.hongliliebiao, getString(R.string.Auto_promotion), null));
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
                String value = item.getValue();
                if (TextUtils.isEmpty(value)) {
                    Intent intent = new Intent(mContext, CommonDataListActivity.class);
                    if (item.getTitle().equals(getString(R.string.Promotion_list))) {
                        intent.putExtra("type", 0);
                    } else {
                        intent.putExtra("type", 1);
                    }
                    intent.putExtra("title", item.getTitle());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                    intent.putExtra("type", item.getValue());
                    startActivity(intent);
                }
            }
        });
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }
}
