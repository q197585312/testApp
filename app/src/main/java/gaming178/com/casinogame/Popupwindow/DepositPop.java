package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Bean.User;
import gaming178.com.casinogame.Control.GdThreadHander;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.mylibrary.allinone.util.BlockDialog;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.RequestBean;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2016/8/12.
 */
public class DepositPop extends BasePopupWindow {
    private TextView tvUsername;
    private TextView tvChoiceBank;
    private EditText edtBankAccount;
    private TextView edtBankNumber;
    private TextView tvChoiceBank2;
    private TextView tvBankAccount2;
    private TextView tvBankNumber2;
    private Button btnConfirm;
    private Button btnCancel;
    private User user;
    public EditText edtAmount;
    private EditText edtRemark;
    List<BankInfo> bankList = new ArrayList<>();
    private BankInfo bank2;
    private BankInfo myBank;
    private ListView lstbank1;
    private ListView lstbank2;
    private BlockDialog dialog;
    private ImageView ivClose;
    private RelativeLayout rl_title;
    private TextView tv_bottom_title;

    public DepositPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    public void setDialog(BlockDialog dialog) {
        this.dialog = dialog;
    }

    public void setUser(final User user) {
        this.user = user;

        tvUsername.setText(user.getName());

        GdThreadHander threadHander = new GdThreadHander(context) {
            @Override
            protected RequestBean<String> getRequestBean() {

                return new RequestBean<String>(WebSiteUrl.GetDeposit_Url, "Usid=" + user.getName());
            }

            @Override
            public void successEnd(String obj) {
                dialog.dismiss();
                /*Results=ok^RAGA03^^BNI^gdctest^01432424242^BNI^HERMAN SUSILO^0383407688^BNI#BNI#HERMAN SUSILO#0383407688#BRI#BRI#GUNAWAN#060001009122502#DANAMON#DANAMON#RITA KOSASIH#003582651729#MANDIRI#MANDIRI#HASAN KOSASIH#9000033369886#BCA#BCA#LINA#8250013885#^BNI#HERMAN SUSILO#0383407688#BRI#GUNAWAN#060001009122502#DANAMON#RITA KOSASIH#003582651729#MANDIRI#HASAN KOSASIH#9000033369886#BCA#LINA#8250013885#^*/
                String tableInfo[] = obj.split("\\^");
                if (tableInfo.length > 10) {
                    if (!tableInfo[3].isEmpty()) {
                        myBank = new BankInfo(tableInfo[3], tableInfo[4], tableInfo[5]);
                        setMyBank(myBank);
                    }

                    String bankTable[] = tableInfo[10].split("#");
                    bankList.clear();
                    for (int i = 0; i < bankTable.length / 3; i++) {
                        int start = i * 3;
                        BankInfo bankInfo = new BankInfo();
                        bankInfo.setBankName(bankTable[start]);
                        if (start + 1 < bankTable.length) {
                            bankInfo.setBankAccount(bankTable[start + 1]);
                        }
                        if (start + 2 < bankTable.length) {
                            bankInfo.setBankNumber(bankTable[start + 2]);
                        }
                        bankList.add(bankInfo);
                        if (!tableInfo[3].isEmpty()) {
                            if (tableInfo[3].equals(bankTable[start])) {
                                setBank2(bankInfo);
                            }
                        }
                    }
                }
                if (bankList != null && bankList.size() > 0) {
                    if (bank2 == null) {
                        setBank2(bankList.get(0));
                    }
                }
                setBankListAdapter();
            }

            @Override
            public void errorEnd(String obj) {
                dialog.dismiss();
            }
        };

        threadHander.startThread(null);
        dialog.show();
    }

    private void setBankListAdapter() {
        AdapterViewContent<BankInfo> content1 = new AdapterViewContent<>(context, lstbank1);
        QuickAdapterImp adapterImp = new QuickAdapterImp<BankInfo>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_text;
            }

            @Override
            public void convert(ViewHolder helper, BankInfo item, int position) {
                helper.setText(R.id.gd__text_tv1, item.getBankName());
                helper.setTextSize(R.id.gd__text_tv1, 12);
            }
        };
        content1.setBaseAdapter(adapterImp);
        content1.setItemClick(new ItemCLickImp<BankInfo>() {
            @Override
            public void itemCLick(View view, BankInfo bankInfo, int position) {
                setMyBank(bankInfo);
                setBank2(bankInfo);
                lstbank1.setVisibility(View.GONE);
            }
        });
        AdapterViewContent<BankInfo> content2 = new AdapterViewContent<>(context, lstbank2);
        content2.setBaseAdapter(new QuickAdapterImp<BankInfo>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_text;
            }

            @Override
            public void convert(ViewHolder helper, BankInfo item, int position) {
                helper.setText(R.id.text_tv1, item.getBankName());
                helper.setTextSize(R.id.text_tv1, 12);
                helper.setTextColor(R.id.text_tv1, ContextCompat.getColor(context, R.color.gray_dark));
            }
        });
        content2.setItemClick(new ItemCLickImp<BankInfo>() {
            @Override
            public void itemCLick(View view, BankInfo bankInfo, int position) {
                setBank2(bankInfo);
                lstbank2.setVisibility(View.GONE);
            }
        });
        content1.setData(bankList);
        content2.setData(bankList);
    }

    private void setMyBank(BankInfo bank) {
        tvChoiceBank.setText(bank.getBankName());
        if (myBank != null && !myBank.getBankName().isEmpty() && bank.getBankName().equals(myBank.getBankName())) {
            edtBankAccount.setText(myBank.getBankAccount());
            edtBankNumber.setText(myBank.getBankNumber());
            tvChoiceBank.setEnabled(false);
            edtBankAccount.setEnabled(false);
            edtBankNumber.setEnabled(false);
        } else {
            edtBankAccount.setText("");
            edtBankNumber.setText("");
        }
    }


    private void setBank2(BankInfo bankInfo) {
        this.bank2 = bankInfo;
        tvChoiceBank2.setText(bankInfo.getBankName());
        tvBankAccount2.setText(bankInfo.getBankAccount());
        tvBankNumber2.setText(bankInfo.getBankNumber());
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_popupwindow_deposit;
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
        edtAmount = (EditText) view.findViewById(R.id.gd__edt_pop_deposit_amount);
        tvUsername = (TextView) view.findViewById(R.id.gd__tv_pop_deposit_username);
        tvChoiceBank = (TextView) view.findViewById(R.id.gd__tv_pop_deposit_choice_bank);
        edtBankAccount = (EditText) view.findViewById(R.id.gd__edt_pop_deposit_bank_account);
        edtBankNumber = (TextView) view.findViewById(R.id.gd__edt_pop_deposit_bank_number);
        tvChoiceBank2 = (TextView) view.findViewById(R.id.gd__tv_pop_deposit_choice_bank2);
        tvBankAccount2 = (TextView) view.findViewById(R.id.gd__tv_pop_deposit_bank_account2);
        tvBankNumber2 = (TextView) view.findViewById(R.id.gd__tv_pop_deposit_bank_number2);
        btnConfirm = (Button) view.findViewById(R.id.gd__btn_pop_deposit_confirm);
        btnCancel = (Button) view.findViewById(R.id.gd__btn_pop_deposit_cancel);
        lstbank1 = (ListView) view.findViewById(R.id.gd__lv_bank_list1);
        lstbank2 = (ListView) view.findViewById(R.id.gd__lv_bank_list2);
        ivClose = (ImageView) view.findViewById(R.id.gd__iv_pop_deposit_close);
        edtRemark = (EditText) view.findViewById(R.id.gd__edt_remark);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDeposit();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        tvChoiceBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBankPopList(lstbank1);
            }
        });
        tvChoiceBank2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBankPopList(lstbank2);
            }
        });

    }

    private void postDeposit() {
        final StringBuilder builder = new StringBuilder();
        if (tvUsername.getText().toString().trim().isEmpty())
            return;
        else {
            builder.append("deposit1=");
            builder.append(tvUsername.getText().toString().trim());
            builder.append("&");
        }
        if (edtAmount.getText().toString().trim().isEmpty()) {
            edtAmount.requestFocus();
            return;
        } else {
            builder.append("deposit2=");
            builder.append(edtAmount.getText().toString().trim());
            builder.append("&");
        }
        if (tvChoiceBank.getText().toString().trim().isEmpty())
            return;
        else {
            builder.append("deposit3=");
            builder.append(tvChoiceBank.getText().toString().trim());
            builder.append("&");
        }
        if (edtBankAccount.getText().toString().trim().isEmpty()) {
            edtBankAccount.requestFocus();
            return;
        } else {
            builder.append("deposit4=");
            builder.append(edtBankAccount.getText().toString().trim());
            builder.append("&");
        }
        if (edtBankNumber.getText().toString().trim().isEmpty()) {
            edtBankNumber.requestFocus();
            return;
        } else {
            builder.append("deposit5=");
            builder.append(edtBankNumber.getText().toString().trim());
            builder.append("&");
        }
        if (tvChoiceBank2.getText().toString().trim().isEmpty())
            return;
        else {
            builder.append("deposit6=");
            builder.append(tvChoiceBank2.getText().toString().trim());
            builder.append("&");
        }
        if (tvBankAccount2.getText().toString().trim().isEmpty()) {
            return;
        } else {
            builder.append("deposit7=");
            builder.append(tvBankAccount2.getText().toString().trim());
            builder.append("&");
        }
        if (tvBankNumber2.getText().toString().trim().isEmpty()) {
            return;
        } else {
            builder.append("deposit8=");
            builder.append(tvBankNumber2.getText().toString().trim());
        }
        builder.append("&remark=");
        builder.append(edtRemark.getText().toString().trim());
        GdThreadHander threadHander = new GdThreadHander(context) {
            @Override
            protected RequestBean<String> getRequestBean() {
                return new RequestBean<>(WebSiteUrl.Deposit_Url, builder.toString());
            }

            @Override
            public void successEnd(String obj) {
                Toast.makeText(context, "Deposit success " + edtAmount.getText(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
                closePopupWindow();
            }

            @Override
            public void errorEnd(String obj) {
                //Results=error^Minimum deposit amount 10000
                String[] split = obj.split("\\^");
                if (split.length >= 2) {
                    Toast.makeText(context, split[1], Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Deposit fail", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        };
        threadHander.startThread(null);
        dialog.show();
    }

    private void initBankPopList(View v) {
        if (v.getVisibility() == View.VISIBLE) {
            v.setVisibility(View.GONE);
        } else {
            v.setVisibility(View.VISIBLE);
        }
    }
}
