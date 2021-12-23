package com.nanyang.app.main.DepositAndWithdraw;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.Utils.ImageBase64;
import com.nanyang.app.main.DepositAndWithdraw.Bean.DepositDataBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.PayMethodBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.UploadImgBean;
import com.nanyang.app.main.DepositAndWithdraw.Pop.PopBankName;
import com.nanyang.app.main.DepositAndWithdraw.Pop.PopPayMethod;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DepositFragment extends DepositWithdrawBaseFragment {
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    @BindView(R.id.tv_pay_method)
    TextView tvPayMethod;
    @BindView(R.id.tv_bank_name_content)
    TextView tvBankNameContent;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_account_number)
    TextView tvAccountNumber;
    @BindView(R.id.ll_upload)
    LinearLayout llUpload;
    @BindView(R.id.tv_no_img)
    TextView tvNoImg;
    @BindView(R.id.edt_remark)
    EditText edtRemark;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private List<PayMethodBean> payMethodList;
    private String payId;
    private List<DepositDataBean> list;
    private BaseToolbarActivity mainActivity;
    private String uploadImgStr;
    private DepositDataBean currentDepositDataBean;


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_deposit;
    }

    @Override
    public void initView() {
        super.initView();
        tvBankName.setText(getString(R.string.to) + ": " + getString(R.string.bank_name));
    }

    @Override
    public void initData() {
        super.initData();
        payMethodList = new ArrayList<>();
        payMethodList.add(new PayMethodBean("Cash Deposit", "0"));
        payMethodList.add(new PayMethodBean("Online Deposit", "1"));
        setPayMethod(payMethodList.get(0));
        presenter.getDepositData();
        mainActivity = (BaseToolbarActivity) getActivity();
        mainActivity.setUploadFragment(this);
    }

    @Override
    public void onGetDepositData(List<DepositDataBean> list) {
        this.list = list;
        if (list != null && list.size() > 0) {
            initUI(list.get(0));
        }
    }

    private void initUI(DepositDataBean bean) {
        currentDepositDataBean = bean;
        tvBankNameContent.setText(bean.getBankName());
        tvAccountName.setText(bean.getAccName());
        tvAccountNumber.setText(bean.getAccNo());
    }

    private void setPayMethod(PayMethodBean bean) {
        tvPayMethod.setText(bean.getPayMethod());
        payId = bean.getPayId();
    }

    @Override
    public void onGetUploadImgData(UploadImgBean bean) {
        uploadImgStr = bean.getFilepath();
        if (!TextUtils.isEmpty(uploadImgStr)) {
            tvNoImg.setText(bean.getImgName());
        } else {
            tvNoImg.setText(getString(R.string.no_image_selected));
            ToastUtils.showLong("Picture upload error");
        }
    }

    @Override
    public void showChoosePic(Uri uri) {
        String imgPath = ImageBase64.getPath_above19(mContext, uri);
        File file = new File(imgPath);
        String url = mainActivity.getApp().getUserCashBean().getFileuUploadUrl() + "api/ImgFile/Post";
        presenter.uploadImg(url, file);
    }

    @Override
    public void onGetSubmitDepositData(String msg) {
        ToastUtils.showLong(msg);
        if (msg.contains("successful")) {
            tvNoImg.setText(getString(R.string.no_image_selected));
            uploadImgStr = "";
            edtAmount.setText("");
            edtRemark.setText("");
        }
    }

    @OnClick({R.id.tv_pay_method, R.id.tv_bank_name_content, R.id.ll_upload, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pay_method:
                PopPayMethod popPayMethod = new PopPayMethod(mContext, tvPayMethod) {
                    @Override
                    public void setPayId(PayMethodBean bean) {
                        setPayMethod(bean);
                    }
                };
                popPayMethod.setData(payMethodList, payId);
                popPayMethod.showPopupDownWindowNoBlack();
                break;
            case R.id.tv_bank_name_content:
                PopBankName popBankName = new PopBankName(mContext, tvBankNameContent) {
                    @Override
                    public void setBankData(DepositDataBean bean) {
                        initUI(bean);
                    }
                };
                if (list != null && list.size() > 0) {
                    popBankName.setData(list, tvBankNameContent.getText().toString().trim());
                    popBankName.showPopupDownWindowNoBlack();
                }
                break;
            case R.id.ll_upload:
                if (checkIsNeedRequestPermissions()) {
                    goChoicePic(mainActivity);
                } else {
                    requestPermission();
                }
                break;
            case R.id.tv_submit:
                String amountStr = edtAmount.getText().toString().trim();
                if (!TextUtils.isEmpty(amountStr)) {
                    int amount = Integer.parseInt(amountStr);
                    if (amount >= currentDepositDataBean.getMinDepositAmt()) {
                        String accName = tvAccountName.getText().toString().trim();
                        String accNumber = tvAccountNumber.getText().toString().trim();
                        String lstBank = tvBankNameContent.getText().toString().trim();
                        String remark = edtRemark.getText().toString().trim();
                        if (TextUtils.isEmpty(uploadImgStr)) {
                            ToastUtils.showLong("Please upload your receipt");
                            return;
                        }
                        presenter.submitDeposit(amountStr, accName, accNumber, lstBank, payId, remark, uploadImgStr);
                    } else {
                        ToastUtils.showLong("Min Deposit Amount: " + currentDepositDataBean.getMinDepositAmt());
                    }
                } else {
                    ToastUtils.showLong("Min Deposit Amount: " + currentDepositDataBean.getMinDepositAmt());
                }
                break;
        }
    }

    public boolean checkIsNeedRequestPermissions() {
        int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public void requestPermission() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        ActivityCompat.requestPermissions(
                getActivity(),
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
        );
    }

    public void goChoicePic(Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, 101);
    }

}
