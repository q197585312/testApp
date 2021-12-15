package com.nanyang.app.main.DepositAndWithdraw.Bean;

public class UploadImgBean {

    /**
     * result : 1
     * filepath : /MBTransferuploadfile/2021/12/6a69687a-3413-46e1-b10e-80f84e593405-Screenshot_2021-12-14-23-29-47-908_com.miui.home.jpg
     * msg : 上传成功
     */
    //https://test.khsport.net:7011/MBTransferuploadfile/2021/12/f1ab977f-c4d7-4c2a-8a3a-86fcc4d8147f-ttt.png
    private String result;
    private String filepath;
    private String msg;
    private String imgName;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
