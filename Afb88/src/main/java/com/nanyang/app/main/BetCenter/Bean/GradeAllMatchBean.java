package com.nanyang.app.main.BetCenter.Bean;


import java.util.List;

/**
 * Created by Administrator on 2019/4/6.
 */

public class GradeAllMatchBean {
    private int index0;
    private int index1;
    private String index2;
    private List<GradeOpenDataBean> gradeOpenDataBeanlist;

    public List<GradeOpenDataBean> getGradeOpenDataBeanlist() {
        return gradeOpenDataBeanlist;
    }

    public void setGradeOpenDataBeanlist(List<GradeOpenDataBean> gradeOpenDataBeanlist) {
        this.gradeOpenDataBeanlist = gradeOpenDataBeanlist;
    }

    public int getIndex0() {
        return index0;
    }

    public void setIndex0(int index0) {
        this.index0 = index0;
    }

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public String getIndex2() {
        return index2;
    }

    public void setIndex2(String index2) {
        this.index2 = index2;
    }

    public GradeAllMatchBean(int index0, int index1, String index2) {
        this.index0 = index0;
        this.index1 = index1;
        this.index2 = index2;
    }
}
