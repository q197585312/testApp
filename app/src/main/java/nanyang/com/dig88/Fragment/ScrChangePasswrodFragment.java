package nanyang.com.dig88.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import nanyang.com.dig88.Entity.TransferScrDataBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2017/4/25.
 */

public class ScrChangePasswrodFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.edt_changPassword)
    EditText edt_changePassword;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    HttpClient httpClient;
    String requestUrl;
    String scrUrl = "http://app.info.dig88api.com/index.php?page=get_scr888_balance_submitter";//余额接口
    private boolean canChange = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                dismissBlockDialog();
                canChange = true;
                Toast.makeText(mContext, getString(R.string.change_passwor_succ), Toast.LENGTH_SHORT).show();
                edt_changePassword.setText("");
            } else {
                dismissBlockDialog();
                canChange = true;
                Toast.makeText(mContext, getString(R.string.change_passwor_fail), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_src_change_password;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setTitle(getString(R.string.xiumima));
        getAct().setleftViewEnable(true);
        httpClient = new HttpClient("");
        btn_submit.setOnClickListener(this);
        requestUrl = "http://app.info.dig88api.com/index.php?page=password_scr888_submitter";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            changePassword();
        } else if (v.getId() == R.id.back) {
//            ((MainTabActivity) mContext).changeFragment(new ScrChangePasswrodFragment(), true);
        }
    }

    public boolean checkPassword(String password) {
        if(password.matches("\\w+")){
            Pattern p1= Pattern.compile("[a-z]+");
            Pattern p2= Pattern.compile("[A-Z]+");
            Pattern p3= Pattern.compile("[0-9]+");
            Matcher m=p1.matcher(password);
            if(!m.find())
                return false;
            else{
                m.reset().usePattern(p2);
                if(!m.find())
                    return false;
                else{
                    m.reset().usePattern(p3);
                    if(!m.find())
                        return false;
                    else{
                        return true;
                    }
                }
            }
        }else{
            return false;
        }
    }

    private void changePassword() {
        final String password = edt_changePassword.getText().toString();
        if (canChange) {
            if (checkPassword(password)) {
                canChange = false;
                showBlockDialog();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        requestUrl += "&id_user=" + getUserInfo().getUser_id();
                        requestUrl += "&session_id=" + getUserInfo().getSession_id();
                        requestUrl += "&new_pass=" + password;
                        try {
                            String scrBlanceUrl = scrUrl + "&web_id=" + WebSiteUrl.WebId + "&id_user=" + getUserInfo().getUser_id()
                                    + "&session_id=" + getUserInfo().getSession_id() + "&scr888=1";
                            String scrResult = httpClient.sendPost(scrBlanceUrl, "");
                            Gson gson = new Gson();
                            TransferScrDataBean bean = gson.fromJson(scrResult, TransferScrDataBean.class);
                            requestUrl += "&scr888_id=" + bean.getData().getScr888_id();
                            String result = httpClient.sendPost(requestUrl, "");
                            if (result.contains("success")) {
                                handler.sendEmptyMessage(1);
                            } else {
                                handler.sendEmptyMessage(2);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            handler.sendEmptyMessage(2);
                        }
                    }
                }.start();
            } else {
                Toast.makeText(mContext, getString(R.string.regex_password), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
