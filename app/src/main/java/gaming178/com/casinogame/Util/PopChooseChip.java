package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.DeviceUtils;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;



public abstract class PopChooseChip extends BasePopupWindow {
    @BindView(R2.id.gd__rc_content)
    RecyclerView rcContent;
    BaseActivity baseActivity;
    BaseRecyclerAdapter contentAdapter;
    LinkedHashMap<Integer, Boolean> selectedMap = new LinkedHashMap<>();
    ArrayList<ChipBean> chipListChoice;
    int selectCount = 0;
    String chipStr;
    public PopChooseChip(Context context, View v, int width, int height) {
        super(context, v, width, height);
        baseActivity = (BaseActivity) context;
        chipListChoice = baseActivity.chipListChoice;
        rcContent.post(new Runnable() {
            @Override
            public void run() {
                initContent();
            }
        });
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.gd_pop_choose_chip;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);

    }

    private void initContent() {
        int spanCount = 5;
        if (baseActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 6;
            ViewGroup.LayoutParams layoutParams = rcContent.getLayoutParams();
            layoutParams.width = DeviceUtils.dip2px(context, 80) * 6;
            rcContent.setLayoutParams(layoutParams);
        }
        rcContent.setLayoutManager(new GridLayoutManager(context, spanCount));
        contentAdapter = new BaseRecyclerAdapter<ChipBean>(context, chipListChoice, R.layout.gd_item_choose_chip) {
            @Override
            public void convert(MyRecyclerViewHolder helper, int position, ChipBean item) {
                ImageView chipImg = helper.getView(R.id.gd__iv_chip_pic);
                LinearLayout llParent = helper.getView(R.id.gd__ll_chip_parent);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                layoutParams.width = DeviceUtils.dip2px(mContext, 60);
                layoutParams.height = DeviceUtils.dip2px(mContext, 60);
                layoutParams.bottomMargin = DeviceUtils.dip2px(mContext, 10);
                llParent.setLayoutParams(layoutParams);
                chipImg.setImageResource(item.getDrawableRes());
                helper.setText(R.id.gd__tv_chip_amount, item.getName());
                Boolean isSelect = selectedMap.get(position);
                if (isSelect == null) {
                    isSelect = false;
                    selectedMap.put(position, false);
                }
                if (isSelect) {
                    helper.setBackgroundRes(R.id.gd__ll_chip_parent, R.drawable.gd_rectangle_trans_stroke_yellow);
                } else {
                    helper.setBackgroundRes(R.id.gd__ll_chip_parent, 0);
                }
            }
        };
        contentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ChipBean>() {
            @Override
            public void onItemClick(View view, ChipBean item, int position) {
                Boolean b = !selectedMap.get(position);
                if (selectCount < 6 && b) {
                    selectedMap.put(position, b);
                    selectCount++;
                    contentAdapter.notifyDataSetChanged();
                    getChipStr();
                } else {
                    if (!b) {
                        selectedMap.put(position, b);
                        selectCount--;
                        contentAdapter.notifyDataSetChanged();
                        getChipStr();
                    }
                }
            }
        });
        rcContent.setAdapter(contentAdapter);

    }

    public void getChipStr() {
        chipStr = "";
        Iterator<Map.Entry<Integer, Boolean>> it = selectedMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Boolean> entry = it.next();
            Integer key = entry.getKey();
            Boolean value = entry.getValue();
            if (value) {
                chipStr += chipListChoice.get(key).getName() + "-";
            }
        }
    }

    @OnClick(R2.id.gd__tv_cancel)
    public void onClickCancel(View v) {
        closePopupWindow();
    }

    @OnClick(R2.id.gd__tv_sure)
    public void onClickSure(View v) {
        if (selectCount < 6) {
            Toast.makeText(context, context.getString(R.string.please_choose_chip), Toast.LENGTH_SHORT).show();
        } else {
            Gd88Utils.saveChipContent(context, chipStr);
            onChooseChipFinish();
            closePopupWindow();
        }
    }

    public abstract void onChooseChipFinish();

}
