package com.nanyang.app.main.DepositAndWithdraw;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.BetCenter.Bean.BaseParamBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.AutoDepositBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.DepositDataBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.DepositWithdrawParam;
import com.nanyang.app.main.DepositAndWithdraw.Bean.UploadImgBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawDataBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawHistoryBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawTakeAwayBean;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DepositWithdrawPresenter extends BaseRetrofitPresenter<DepositWithdrawBaseFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */

    private DepositWithdrawBaseFragment depositWithdrawBaseFragment;

    public DepositWithdrawPresenter(DepositWithdrawBaseFragment iBaseContext) {
        super(iBaseContext);
        depositWithdrawBaseFragment = iBaseContext;
    }

    public void getWithdrawData() {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("WithdrawH5GetTT", "wfTransferH5").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    WithdrawDataBean bean = gson.fromJson(jsonArrayData.get(0).toString(), WithdrawDataBean.class);
                    depositWithdrawBaseFragment.onGetWithdrawData(bean);
                }
            }
        });
    }

    public void submitWithdrawData(String amount) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new DepositWithdrawParam("WithdrawH5Save", "wfTransferH5", amount, "", "", "").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    WithdrawTakeAwayBean bean = null;
                    if (data.contains("successful") || data.contains("Successful")) {
                        JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                        bean = gson.fromJson(jsonArrayData.get(0).toString(), WithdrawTakeAwayBean.class);
                    } else if (data.contains("Sorry")) {
                        bean = new WithdrawTakeAwayBean();
                        bean.setLblStatus("Sorry, you have already made (deposit or withdraw) request. You can (deposit or withdraw) again after your current request is processed. Thank you.");
                    } else {
                        bean = new WithdrawTakeAwayBean();
                        bean.setLblStatus("Failed");
                    }
                    depositWithdrawBaseFragment.onGetSubmitWithdrawData(bean);
                }
            }
        });
    }

    public void getWithdrawHistoryData(String fromDate, String toDate) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new DepositWithdrawParam("Getdate", "wfTransferH5", fromDate, toDate, "w", "", "").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    JSONArray jsonArray2 = jsonArrayData.getJSONArray(0);
                    JSONArray jsonArray3 = jsonArray2.getJSONArray(2);
                    List<WithdrawHistoryBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArray3.length(); i++) {
                        JSONArray jsonArray1 = jsonArray3.getJSONArray(i);
                        WithdrawHistoryBean bean = new WithdrawHistoryBean(jsonArray1.getString(0), jsonArray1.getString(1), jsonArray1.getString(2), jsonArray1.getString(3));
                        list.add(bean);
                    }
                    depositWithdrawBaseFragment.onGetWithdrawHistoryData(list);
                }
            }
        });
    }

    public void uploadImg(String url, File file) {
        String name = file.getName();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", name, requestBody);
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().upload(url, body), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                String replace = data.replace("\\", "");
                String substring = replace.substring(1, replace.length() - 1);
                UploadImgBean uploadImgBean = gson.fromJson(substring, UploadImgBean.class);
                uploadImgBean.setImgName(name);
                depositWithdrawBaseFragment.onGetUploadImgData(uploadImgBean);
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
                depositWithdrawBaseFragment.onGetUploadImgData(new UploadImgBean());
            }
        });
    }

    public void getDepositData() {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetTT", "wfTransferH5").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    JSONArray jsonDepositStr = jsonArrayData.getJSONArray(1);
                    JSONArray jsonDepositList = jsonDepositStr.getJSONArray(2);
                    List<DepositDataBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonDepositList.length(); i++) {
                        JSONArray dataDetail = jsonDepositList.getJSONArray(i);
                        DepositDataBean bean = new DepositDataBean(dataDetail.getInt(0), dataDetail.getInt(1),
                                dataDetail.getString(2), dataDetail.getString(3), dataDetail.getString(4),
                                dataDetail.getInt(5), dataDetail.getInt(6), dataDetail.getInt(7),
                                dataDetail.getInt(8), dataDetail.getInt(9), dataDetail.getInt(10));
                        list.add(bean);
                    }
                    depositWithdrawBaseFragment.onGetDepositData(list);
                }
            }
        });
    }

    public void submitDeposit(String Daposit, String AccName, String AccNumber, String lstBank, String PayMethod, String Remark, String imgurl) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new DepositWithdrawParam("Save", "wfTransferH5", Daposit, AccName, AccNumber, lstBank, PayMethod, Remark, imgurl, "", "").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    String msg;
                    if (data.contains("successful") || data.contains("Successful")) {
                        msg = "Deposit request successful! Credit will be deposited into your account shortly. Alternatively, you can contact our live chat to confirm your credit update.";
                    } else if (data.contains("Sorry")) {
                        msg = "Sorry, you have already made (deposit or withdraw) request. You can (deposit or withdraw) again after your current request is processed. Thank you.";
                    } else {
                        msg = "Failed";
                    }
                    depositWithdrawBaseFragment.onGetSubmitDepositData(msg);
                }
            }
        });
    }

    public void getDepositHistoryData(String fromDate, String toDate) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new DepositWithdrawParam("Getdate", "wfTransferH5", fromDate, toDate, "d", "", "").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    JSONArray jsonArray2 = jsonArrayData.getJSONArray(0);
                    JSONArray jsonArray3 = jsonArray2.getJSONArray(2);
                    List<WithdrawHistoryBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArray3.length(); i++) {
                        JSONArray jsonArray1 = jsonArray3.getJSONArray(i);
                        WithdrawHistoryBean bean = new WithdrawHistoryBean(jsonArray1.getString(0), jsonArray1.getString(1), jsonArray1.getString(2), jsonArray1.getString(3));
                        list.add(bean);
                    }
                    depositWithdrawBaseFragment.onGetDepositHistoryData(list);
                }
            }
        });
    }

    public void getAutoDepositData() {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetTT", "wfTransferH5").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    JSONArray jsonDepositStr = jsonArrayData.getJSONArray(0);
                    JSONArray jsonDepositList = jsonDepositStr.getJSONArray(2);
                    List<AutoDepositBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonDepositList.length(); i++) {
                        JSONArray dataDetail = jsonDepositList.getJSONArray(i);
                        AutoDepositBean bean = new AutoDepositBean(dataDetail.getInt(0), dataDetail.getString(1),
                                dataDetail.getString(2), dataDetail.getString(3), dataDetail.getString(4),
                                dataDetail.getInt(5), dataDetail.getInt(6));
                        list.add(bean);
                    }
                    depositWithdrawBaseFragment.onGetAutoDepositData(list);
                }
            }
        });
    }

    public void submitAutoDeposit(String Daposit, String AccName, String AccNumber, String lstBank, String MinAmt) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new DepositWithdrawParam("AutoSave", "wfTransferH5", Daposit, AccName, AccNumber, lstBank, MinAmt, "", "").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                String replace = updateString.replace("'", "\"");
                JSONArray jsonArray = new JSONArray(replace);
                if (jsonArray.length() > 3) {
                    String msg;
                    if (data.contains("finalAmt")) {
                        JSONArray jsonArray1 = jsonArray.getJSONArray(3);
                        JSONObject jsonObject = jsonArray1.getJSONObject(0);
                        double finalAmt = jsonObject.getDouble("finalAmt");
                        msg = "Final Deposit Amount: " + finalAmt;
                    } else if (data.contains("Sorry")) {
                        msg = "Sorry, you have already made (deposit or withdraw) request. You can (deposit or withdraw) again after your current request is processed. Thank you.";
                    } else {
                        msg = "Failed";
                    }
                    depositWithdrawBaseFragment.onGetSubmitAutoDepositData(msg);
                }
            }
        });
    }

}
