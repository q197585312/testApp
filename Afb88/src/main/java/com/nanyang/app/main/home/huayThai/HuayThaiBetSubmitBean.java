package com.nanyang.app.main.home.huayThai;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 */

public class HuayThaiBetSubmitBean {
    String lstDraw;
    String txtNumber;
    String txtAmt;
    String txtNumber2;
    String txtAmt2;
    String txtNumber3;
    String txtAmt3;
    String txtNumber4;
    String txtAmt4;
    String txtNumber5;
    String txtAmt5;
    String txtNumber6;
    String txtAmt6;
    String txtNumber7;
    String txtAmt7;
    String txtNumber8;
    String txtAmt8;
    String txtNumber9;
    String txtAmt9;
    String txtNumber10;
    String txtAmt10;

    public HuayThaiBetSubmitBean(String txtNumber, String txtAmt, String txtNumber2, String txtAmt2, String lstDraw, String txtNumber3, String txtAmt3, String txtNumber4, String txtAmt4, String txtNumber5, String txtAmt5, String txtNumber6, String txtAmt6, String txtNumber7, String txtAmt7, String txtNumber8, String txtAmt8, String txtNumber9, String txtAmt9, String txtNumber10, String txtAmt10) {
        this.txtNumber = txtNumber;
        this.txtAmt = txtAmt;
        this.txtNumber2 = txtNumber2;
        this.txtAmt2 = txtAmt2;
        this.lstDraw = lstDraw;
        this.txtNumber3 = txtNumber3;
        this.txtAmt3 = txtAmt3;
        this.txtNumber4 = txtNumber4;
        this.txtAmt4 = txtAmt4;
        this.txtNumber5 = txtNumber5;
        this.txtAmt5 = txtAmt5;
        this.txtNumber6 = txtNumber6;
        this.txtAmt6 = txtAmt6;
        this.txtNumber7 = txtNumber7;
        this.txtAmt7 = txtAmt7;
        this.txtNumber8 = txtNumber8;
        this.txtAmt8 = txtAmt8;
        this.txtNumber9 = txtNumber9;
        this.txtAmt9 = txtAmt9;
        this.txtNumber10 = txtNumber10;
        this.txtAmt10 = txtAmt10;
    }

    public HuayThaiBetSubmitBean() {
    }


    public String getLstDraw() {
        return lstDraw;
    }

    public void setLstDraw(String lstDraw) {
        this.lstDraw = lstDraw;
    }

    public String getTxtNumber() {
        return txtNumber;
    }

    public void setTxtNumber(String txtNumber) {
        this.txtNumber = txtNumber;
    }

    public String getTxtNumber2() {
        return txtNumber2;
    }

    public void setTxtNumber2(String txtNumber2) {
        this.txtNumber2 = txtNumber2;
    }

    public String getTxtAmt() {
        return txtAmt;
    }

    public void setTxtAmt(String txtAmt) {
        this.txtAmt = txtAmt;
    }

    public String getTxtAmt2() {
        return txtAmt2;
    }

    public void setTxtAmt2(String txtAmt2) {
        this.txtAmt2 = txtAmt2;
    }

    public String getTxtNumber3() {
        return txtNumber3;
    }

    public void setTxtNumber3(String txtNumber3) {
        this.txtNumber3 = txtNumber3;
    }

    public String getTxtAmt3() {
        return txtAmt3;
    }

    public void setTxtAmt3(String txtAmt3) {
        this.txtAmt3 = txtAmt3;
    }

    public String getTxtNumber4() {
        return txtNumber4;
    }

    public void setTxtNumber4(String txtNumber4) {
        this.txtNumber4 = txtNumber4;
    }

    public String getTxtNumber5() {
        return txtNumber5;
    }

    public void setTxtNumber5(String txtNumber5) {
        this.txtNumber5 = txtNumber5;
    }

    public String getTxtAmt4() {
        return txtAmt4;
    }

    public void setTxtAmt4(String txtAmt4) {
        this.txtAmt4 = txtAmt4;
    }

    public String getTxtAmt5() {
        return txtAmt5;
    }

    public void setTxtAmt5(String txtAmt5) {
        this.txtAmt5 = txtAmt5;
    }

    public String getTxtNumber6() {
        return txtNumber6;
    }

    public void setTxtNumber6(String txtNumber6) {
        this.txtNumber6 = txtNumber6;
    }

    public String getTxtAmt6() {
        return txtAmt6;
    }

    public void setTxtAmt6(String txtAmt6) {
        this.txtAmt6 = txtAmt6;
    }

    public String getTxtNumber7() {
        return txtNumber7;
    }

    public void setTxtNumber7(String txtNumber7) {
        this.txtNumber7 = txtNumber7;
    }

    public String getTxtAmt7() {
        return txtAmt7;
    }

    public void setTxtAmt7(String txtAmt7) {
        this.txtAmt7 = txtAmt7;
    }

    public String getTxtNumber8() {
        return txtNumber8;
    }

    public void setTxtNumber8(String txtNumber8) {
        this.txtNumber8 = txtNumber8;
    }

    public String getTxtAmt8() {
        return txtAmt8;
    }

    public void setTxtAmt8(String txtAmt8) {
        this.txtAmt8 = txtAmt8;
    }

    public String getTxtNumber9() {
        return txtNumber9;
    }

    public void setTxtNumber9(String txtNumber9) {
        this.txtNumber9 = txtNumber9;
    }

    public String getTxtAmt9() {
        return txtAmt9;
    }

    public void setTxtAmt9(String txtAmt9) {
        this.txtAmt9 = txtAmt9;
    }

    public String getTxtNumber10() {
        return txtNumber10;
    }

    public void setTxtNumber10(String txtNumber10) {
        this.txtNumber10 = txtNumber10;
    }

    public String getTxtAmt10() {
        return txtAmt10;
    }

    public void setTxtAmt10(String txtAmt10) {
        this.txtAmt10 = txtAmt10;
    }

    Map<String, String> map;

    public Map<String, String> getThaiThousandBetSubmitMap() {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("lstDraw", lstDraw);
        map.put("txtNumber", txtNumber);
        map.put("txtAmt", txtAmt);
        map.put("txtNumber2", txtNumber2);
        map.put("txtAmt2", txtAmt2);
        map.put("txtNumber3", txtNumber3);
        map.put("txtAmt3", txtAmt3);
        map.put("txtNumber4", txtNumber4);
        map.put("txtAmt4", txtAmt4);
        map.put("txtNumber5", txtNumber5);
        map.put("txtAmt5", txtAmt5);
        map.put("txtNumber6", txtNumber6);
        map.put("txtAmt6", txtAmt6);
        map.put("txtNumber7", txtNumber7);
        map.put("txtAmt7", txtAmt7);
        map.put("txtNumber8", txtNumber8);
        map.put("txtAmt8", txtAmt8);
        map.put("txtNumber9", txtNumber9);
        map.put("txtAmt9", txtAmt9);
        map.put("txtNumber10", txtNumber10);
        map.put("txtAmt10", txtAmt10);
        return map;
    }
}
