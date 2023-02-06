package gaming178.com.casinogame.Util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codersun.fingerprintcompat.AFingerDialog;

public class MyFingerDialog extends AFingerDialog implements View.OnClickListener{
//    private TextView titleTv;
//
//    private TextView desTv;
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        View view = inflater.inflate(R.layout.my_dialog_finger, null);
//
//        titleTv = view.findViewById(com.codersun.fingerprintcompat.R.id.finger_dialog_title_tv);
//        desTv = view.findViewById(com.codersun.fingerprintcompat.R.id.finger_dialog_des_tv);
//        TextView cancelTv = view.findViewById(com.codersun.fingerprintcompat.R.id.finger_dialog_cancel_tv);
//        cancelTv.setOnClickListener(this);
//        return view;
//    }

    @Override
    public void onSucceed() {
        dismiss();
    }

    @Override
    public void onFailed() {
//        titleTv.setText("我是失败标题,继续验证");
//        desTv.setText("连按个手指都不会,去屎吧");
    }

    @Override
    public void onHelp(String help) {
//        titleTv.setText("我是失败标题,继续验证");
//        desTv.setText("连按个手指都不会,去屎吧");
    }

    @Override
    public void onError(String error) {
//        titleTv.setText("客官,下次再来");
//        desTv.setText("这都能失败,你还能干啥,不消失,代表我是自定义弹窗");
    }

    @Override
    public void onCancelAuth() {

    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
