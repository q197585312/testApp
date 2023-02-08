package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;

/**
 * Created by Administrator on 2017/1/19.
 */

public class TransferAccPersonFragment extends BaseFragment {
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<GameMenuItem> adapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_transferaccperson;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setTitle(getString(R.string.transferacc));
        initAdapter();
    }

    private List<GameMenuItem> getContentData() {
        List<GameMenuItem> list = new ArrayList<>();
        list.add(new GameMenuItem(R.mipmap.zhuanzhang, getString(R.string.transferacc), TransferAccFragment.class.getName()));
        list.add(new GameMenuItem(R.drawable.xiazhujilu, getString(R.string.transferacc_list), TransferAccListFragment.class.getName()));
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
                intent.putExtra("type", item.getValue());
                startActivity(intent);
            }
        });
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }
}
