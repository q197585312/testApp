package nanyang.com.dig88.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;


import butterknife.Bind;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.Presenter.BasicPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import xs.com.mylibrary.allinone.util.AppTool;


/**
 * Created by Administrator on 2015/12/21.(基本信息)
 */
public class BasicFragment extends BaseFragment<BasicPresenter> {

    @Bind(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<GameMenuItem> adapter;
    VipInfoBean info;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_personal_info;
    }

    private void initAdapter() {
        info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
        adapter = new BaseRecyclerAdapter<GameMenuItem>(mContext, presenter.getContentData(info), R.layout.item_personl_info) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, final GameMenuItem item) {
                ImageView imgLeft = holder.getView(R.id.img_left);
                imgLeft.setImageResource(item.getDrawableRes());
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(item.getTitle());
                TextView tvValue = holder.getView(R.id.tv_value);
                TextView tvCopy = holder.getView(R.id.tv_copy);
                final EditText edtBirthday = holder.getView(R.id.edt_birthday);
                if (item.getTitle().equals(getString(R.string.tuijianlianjie))) {
                    tvCopy.setVisibility(View.VISIBLE);
                    tvCopy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                            cm.setText(item.getValue());
                            ToastUtils.showShort(getString(R.string.Copy_Success));
                        }
                    });
                } else if (item.getTitle().equals(getString(R.string.date_of_birth))) {
                    tvValue.setVisibility(View.INVISIBLE);
                    edtBirthday.setText(item.getValue());
                    edtBirthday.setVisibility(View.VISIBLE);
                    tvCopy.setText(getString(R.string.submit));
                    tvCopy.setVisibility(View.VISIBLE);
                    tvCopy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.updateBirthday(edtBirthday.getText().toString().trim());
                        }
                    });
                } else {
                    tvCopy.setVisibility(View.GONE);
                }
                tvValue.setText(item.getValue());
                View viewLine = holder.getView(R.id.view_line);
                View viewLineHeight = holder.getView(R.id.view_line_height);
                if (position == 3 || position == 6 || position == 9) {
                    viewLineHeight.setVisibility(View.VISIBLE);
                    viewLine.setVisibility(View.GONE);
                } else {
                    viewLineHeight.setVisibility(View.GONE);
                    viewLine.setVisibility(View.VISIBLE);
                }
                if (position == getItemCount() - 1) {
                    viewLine.setVisibility(View.GONE);
                }
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }

    public void onUpdateBirthdayFinish(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(mContext, "");
        getAct().setTitle(getString(R.string.jibenxinxi));
        getAct().setleftViewEnable(true);
        createPresenter(new BasicPresenter(this));
        initAdapter();
    }
}
