package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Bean.User;
import gaming178.com.casinogame.Control.GdThreadHander;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.mylibrary.allinone.util.BlockDialog;
import gaming178.com.mylibrary.base.RequestBean;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2016/8/12.
 */
public class WithdrawPop extends BasePopupWindow {
    private TextView tvUsername;
    private TextView tvChoiceBank;
    private TextView edtBankAccount;
    private TextView edtBankNumber;
    private Button btnConfirm;
    private Button btnCancel;
    private User user;
    public EditText edtAmount;
    private BankInfo myBank;
    private BlockDialog dialog;
    private EditText edtPassword;
    private ImageView ivClose;
    private EditText edtRemark;
    private LinearLayout llRemark;
    private RelativeLayout rl_title;
    private TextView tv_bottom_title;

    public WithdrawPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }
    public void setDialog(BlockDialog  dialog){
        this.dialog=dialog;
    }

    public void  setUser(final User user){
        this.user=user;

        tvUsername.setText(user.getName());
        GdThreadHander threadHander=new GdThreadHander(context) {
            @Override
            protected RequestBean<String> getRequestBean() {

                return new RequestBean<String>(WebSiteUrl.GetWithdraw_Url,"Usid="+user.getName());
            }

            @Override
            public void successEnd(String obj) {
                dialog.dismiss();
                /*Results=ok^RAGA03^^BNI^gdctest^01432424242^BNI^HERMAN SUSILO^0383407688^BNI#BNI#HERMAN SUSILO#0383407688#BRI#BRI#GUNAWAN#060001009122502#DANAMON#DANAMON#RITA KOSASIH#003582651729#MANDIRI#MANDIRI#HASAN KOSASIH#9000033369886#BCA#BCA#LINA#8250013885#^BNI#HERMAN SUSILO#0383407688#BRI#GUNAWAN#060001009122502#DANAMON#RITA KOSASIH#003582651729#MANDIRI#HASAN KOSASIH#9000033369886#BCA#LINA#8250013885#^*/
                String tableInfo[] = obj.split("\\^");
                if(tableInfo==null||tableInfo.length<6)
                    return;
                if(!tableInfo[3].isEmpty()){
                    myBank=new BankInfo(tableInfo[3],tableInfo[4],tableInfo[5]);
                    setMyBank(myBank);
                }
            }

            @Override
            public void errorEnd(String obj) {
                dialog.dismiss();
            }
        };

        threadHander.startThread(null);
        dialog.show();
    }


    private void setMyBank(BankInfo bank) {
        tvChoiceBank.setText(bank.getBankName());
        if(myBank!=null&&!myBank.getBankName().isEmpty()&&bank.getBankName().equals(myBank.getBankName())){
            edtBankAccount.setText(myBank.getBankAccount());
            edtBankNumber.setText(myBank.getBankNumber());
            tvChoiceBank.setEnabled(false);
            edtBankAccount.setEnabled(false);
            edtBankNumber.setEnabled(false);
        }else{
            edtBankAccount.setText("");
            edtBankNumber.setText("");
        }
    }


    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_popupwindow_withdraw;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rl_title = view.findViewById(R.id.gd_rl_title);
        tv_bottom_title = view.findViewById(R.id.gd_tv_bottom_title);
        if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            rl_title.setBackgroundColor(ContextCompat.getColor(context, R.color.login_color));
            tv_bottom_title.setBackgroundColor(ContextCompat.getColor(context, R.color.login_color));
        }
        edtAmount=(EditText)view.findViewById(R.id.gd__edt_pop_withdraw_amount);
        tvUsername=(TextView)view.findViewById(R.id.gd__tv_pop_withdraw_username);
        tvChoiceBank=(TextView)view.findViewById(R.id.gd__tv_pop_withdraw_choice_bank);
        edtBankAccount=(TextView)view.findViewById(R.id.gd__edt_pop_withdraw_bank_account);
        edtBankNumber=(TextView)view.findViewById(R.id.gd__edt_pop_withdraw_bank_number);
        edtPassword=(EditText)view.findViewById(R.id.gd__edt_pop_withdraw_password);
        ivClose=(ImageView)view.findViewById(R.id.gd__iv_pop_withdraw_close);
        edtRemark = (EditText) view.findViewById(R.id.gd__edt_remark);
        llRemark = view.findViewById(R.id.ll_remark);
        if (BuildConfig.FLAVOR.equals("ahlicasino")){
            llRemark.setVisibility(View.GONE);
        }
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        btnConfirm=(Button)view.findViewById(R.id.gd__btn_pop_withdraw_confirm);
        btnCancel=(Button)view.findViewById(R.id.gd__btn_pop_withdraw_cancel);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postWithdraw();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });

    }

    private void postWithdraw() {
        final StringBuilder builder=new StringBuilder();
        if(tvUsername.getText().toString().trim().isEmpty())
            return;
        else{
            builder.append("withdraw1=");
            builder.append(tvUsername.getText().toString().trim());
            builder.append("&");
        }
        if(edtAmount.getText().toString().trim().isEmpty()){
            edtAmount.requestFocus();
            return;
        }
        else{
            builder.append("withdraw2=");
            builder.append(edtAmount.getText().toString().trim());
            builder.append("&");
        }
        if(tvChoiceBank.getText().toString().trim().isEmpty())
            return;
        else{
            builder.append("withdraw3=");
            builder.append(tvChoiceBank.getText().toString().trim());
            builder.append("&");
        }
        if(edtBankAccount.getText().toString().trim().isEmpty()){
            edtBankAccount.requestFocus();
            return;
        }else{
            builder.append("withdraw4=");
            builder.append(edtBankAccount.getText().toString().trim());
            builder.append("&");
        }
        if(edtBankNumber.getText().toString().trim().isEmpty()){
            edtBankNumber.requestFocus();
            return;
        }else{
            builder.append("withdraw5=");
            builder.append(/*edtBankNumber.getText().toString().trim()*/myBank.getBankNumber());
            builder.append("&");
        }
        if(edtPassword.getText().toString().trim().isEmpty()){
            edtPassword.requestFocus();
            return;
        }else{
            builder.append("withdraw6=");
            builder.append(edtPassword.getText().toString().trim());
        }
        builder.append("&remark=");
        builder.append(edtRemark.getText().toString().trim());
        GdThreadHander threadHander=new GdThreadHander(context) {
            @Override
            protected RequestBean<String> getRequestBean() {
                return new RequestBean<>(WebSiteUrl.Withdraw_Url,builder.toString());
            }

            @Override
            public void successEnd(String obj) {
                Toast.makeText(context,"withdraw success"+edtAmount.getText(),Toast.LENGTH_LONG).show();
                dialog.dismiss();
                closePopupWindow();
            }

            @Override
            public void errorEnd(String obj) {
                String[] split = obj.split("\\^");
                if (split.length >= 2) {
                    Toast.makeText(context, split[1], Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "withdraw fail", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        };
        threadHander.startThread(null);
        dialog.show();
    }
}
