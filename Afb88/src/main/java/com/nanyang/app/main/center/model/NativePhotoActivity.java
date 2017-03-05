package com.nanyang.app.main.center.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/4.
 */

public class NativePhotoActivity extends BaseToolbarActivity {
    @Bind(R.id.native_rc)
    RecyclerView nativeRc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nativephoto);
    }

    @Override
    public void initData() {
        super.initData();
        List<String> list = AfbUtils.getPhoneImg(mContext);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);//设置为一个3列的纵向网格布局
        nativeRc.setLayoutManager(layoutManager);
        BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(mContext, list, R.layout.item_user_img) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                ImageView img = holder.getView(R.id.item_useimg);
                Bitmap b = BitmapFactory.decodeFile(item);
                Bitmap b1 = AfbUtils.compressImage(b);
                img.setImageBitmap(b1);
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {

            }
        });
        nativeRc.setAdapter(adapter);
//        nativeRc.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                outRect.top = 10;
//                outRect.bottom = 10;
//                outRect.left = 10;
//                outRect.right = 10;
//            }
//        });
    }
}
