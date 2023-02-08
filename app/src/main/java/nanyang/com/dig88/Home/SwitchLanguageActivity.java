package nanyang.com.dig88.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.MainTabActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;

/**
 * Created by 47184 on 2019/6/24.
 */

public class SwitchLanguageActivity extends BaseActivity {
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<GameMenuItem> adapter;
    private String localLang;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_switch_language;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        localLang = getLocalLanguage();
        setTitle(getString(R.string.set_language));
        setleftViewEnable(true);
        initAdapter();
    }

    private void initAdapter() {
        List<GameMenuItem> list = new ArrayList<>();
        String language = "zh,kr,en,vn,kh,in,ms,th";
        String[] split = language.split(",");
        for (int i = 0; i < split.length; i++) {
            String lg = split[i];
            switch (lg) {
                case "zh":
                    list.add(new GameMenuItem(R.drawable.lang_zh_flag, "中文", "zh"));
                    break;
                case "kr":
                    list.add(new GameMenuItem(R.drawable.lang_kr_flag, "대한민국", "kr"));
                    break;
                case "en":
                    list.add(new GameMenuItem(R.drawable.lang_en_flag, "English", "en"));
                    break;
                case "vn":
                    list.add(new GameMenuItem(R.drawable.lang_vn_flag, "Việt Nam", "vn"));
                    break;
                case "kh":
                    list.add(new GameMenuItem(R.drawable.lang_ka_flag, "ភាសាខ្មែរ", "kh"));
                    break;
                case "de":
                    list.add(new GameMenuItem(R.drawable.lang_de_flag, "Deutschland", "de"));
                    break;
                case "in":
                    list.add(new GameMenuItem(R.drawable.lang_in_flag, "Indonesia", "in"));
                    break;
                case "mk":
                    list.add(new GameMenuItem(R.drawable.lang_mk_flag, "Macedonian", "mk"));
                    break;
                case "ms":
                    list.add(new GameMenuItem(R.drawable.lang_my_flag, "Malaysia", "ms"));
                    break;
                case "tr":
                    list.add(new GameMenuItem(R.drawable.lang_tr_flag, "Türkiye", "tr"));
                    break;
                case "th":
                    list.add(new GameMenuItem(R.drawable.lang_th_flag, "ประเทศไทย", "th"));
                    break;
                case "sq":
                    list.add(new GameMenuItem(R.drawable.lang_sq_flag, "Shqipërisë", "sq"));
                    break;
                case "my":
                    list.add(new GameMenuItem(R.drawable.lang_mm_flag, "Myammar", "my"));
                    break;
            }
        }
        adapter = new BaseRecyclerAdapter<GameMenuItem>(mContext, list, R.layout.item_switch_lang) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, GameMenuItem item) {
                ImageView imgLang = holder.getView(R.id.img_lang);
                TextView tvLangName = holder.getView(R.id.tv_lang_name);
                ImageView imgSelect = holder.getView(R.id.img_select);
                View viewRight = holder.getView(R.id.view_right);
                View viewBottom = holder.getView(R.id.view_bottom);
                if (position % 2 == 0) {
                    viewRight.setVisibility(View.VISIBLE);
                } else {
                    viewRight.setVisibility(View.GONE);
                }
                int itemCount = adapter.getItemCount();
                if (itemCount % 2 == 0) {
                    if (position == itemCount - 1 || position == itemCount - 2) {
                        viewBottom.setVisibility(View.GONE);
                    } else {
                        viewBottom.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (position == itemCount - 1) {
                        viewBottom.setVisibility(View.GONE);
                    } else {
                        viewBottom.setVisibility(View.VISIBLE);
                    }
                }
                imgLang.setImageResource(item.getDrawableRes());
                tvLangName.setText(item.getTitle());
                if (item.getValue().equals(localLang)) {
                    imgSelect.setVisibility(View.VISIBLE);
                } else {
                    imgSelect.setVisibility(View.GONE);
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<GameMenuItem>() {
            @Override
            public void onItemClick(View view, GameMenuItem item, int position) {
                localLang = item.getValue();
                adapter.notifyDataSetChanged();
                AppTool.setAppLanguage(mContext, localLang);
                Intent intent = new Intent(mContext, MainTabActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        rcContent.setLayoutManager(new GridLayoutManager(mContext, 2));
        rcContent.setAdapter(adapter);
    }

    @OnClick({R.id.btn_submit})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                AppTool.setAppLanguage(mContext, localLang);
                Intent intent = new Intent(mContext, MainTabActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
