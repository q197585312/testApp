package com.nanyang.app.main.center;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.UserImgBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/4.
 */

public class FragmentAvatar extends BaseFragment {
    @Bind(R.id.avatar_rc)
    RecyclerView avatarRc;
    @Bind(R.id.img_header)
    ImageView imgHeader;
    @Bind(R.id.avatar_submit)
    TextView tvSubmit;
    @Bind(R.id.avatar_native)
    TextView tvNative;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_useravatar;
    }

    List<UserImgBean> imgData;

    @Override
    public void initData() {
        super.initData();
        getImgData();
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 5);//设置为一个5列的纵向网格布局
        avatarRc.setLayoutManager(layoutManager);
        BaseRecyclerAdapter<UserImgBean> adapter = new BaseRecyclerAdapter<UserImgBean>(mContext, imgData, R.layout.item_user_img) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, UserImgBean item) {
                ImageView img = holder.getView(R.id.item_useimg);
                img.setBackgroundResource(item.getImg());
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<UserImgBean>() {
            @Override
            public void onItemClick(View view, UserImgBean item, int position) {
                if (item.getImg() == imgData.get(position).getImg()) {
                    headBitmap = AfbUtils.toRoundBitmap(BitmapFactory.decodeResource(getResources(), item.getImg()));
                    if (headBitmap != null) {
                        imgHeader.setImageBitmap(AfbUtils.toRoundBitmap(headBitmap));
                    }
                }
            }
        });
        avatarRc.setAdapter(adapter);
        avatarRc.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
                outRect.bottom = 10;
                outRect.left = 10;
                outRect.right = 10;
            }
        });
    }

    private void getImgData() {
        imgData = new ArrayList<>();
        imgData.add(new UserImgBean(R.mipmap.avatar_1));
        imgData.add(new UserImgBean(R.mipmap.avatar_2));
        imgData.add(new UserImgBean(R.mipmap.avatar_3));
        imgData.add(new UserImgBean(R.mipmap.avatar_4));
        imgData.add(new UserImgBean(R.mipmap.avatar_5));
        imgData.add(new UserImgBean(R.mipmap.avatar_6));
        imgData.add(new UserImgBean(R.mipmap.avatar_7));
        imgData.add(new UserImgBean(R.mipmap.avatar_8));
        imgData.add(new UserImgBean(R.mipmap.avatar_9));
        imgData.add(new UserImgBean(R.mipmap.avatar_10));
    }

    @Override
    public void initView() {
        super.initView();
        Bitmap b = BitmapFactory.decodeFile(AfbUtils.nativePath + AfbUtils.headImgName);
        if (b != null) {
            imgHeader.setImageBitmap(b);
        }
    }

    @OnClick({R.id.avatar_submit, R.id.avatar_native})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar_submit:
                PersonCenterActivity activity = (PersonCenterActivity) getActivity();
                if (headBitmap != null) {
                    AfbUtils.writeBitmapToFile(AfbUtils.nativePath, headBitmap, 0, AfbUtils.headImgName);
                }
                activity.setResult(1);
                getActivity().finish();
                break;
            case R.id.avatar_native:
                spikPictures();
                break;
        }
    }

    //　　MediaStore.ACTION_IMAGE_CAPTURE 拍照；
//  　　MediaStore.ACTION_VIDEO_CAPTURE录像。
    private void spikPictures() {
    /* 开启Pictures画面Type设定为image */
        Intent intent = new Intent();
        intent.setType("image/*");
    /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
   /* 取得相片后返回本画面 */
        getActivity().startActivityForResult(intent, 0);
        //(在onActivityResult方法里，返回的意图里获取图片uri，在通过uri，结合内容提供者在查出图片的路径)
    }

    @Override
    public ImageView getHeadImg() {
        return imgHeader;
    }

}
