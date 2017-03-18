package com.nanyang.app.main.home.sport.model;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class VsTableRowBean extends SportInfo {
    private String Sc;
    private String b;
    String ModuleTitle;
    String leftTitle;
    String centerTitle;
    String rightTitle;
    boolean hasHead;
    List<VsCellBean> rows;
    boolean hasFoot;


    public String getSc() {
        return Sc;
    }

    public void setSc(String sc) {
        Sc = sc;
    }

    public String getModuleTitle() {
        return ModuleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        ModuleTitle = moduleTitle;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public String getCenterTitle() {
        return centerTitle;
    }

    public void setCenterTitle(String centerTitle) {
        this.centerTitle = centerTitle;
    }

    public String getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
    }

    public boolean isHasHead() {
        return hasHead;
    }

    public void setHasHead(boolean hasHead) {
        this.hasHead = hasHead;
    }

    public List<VsCellBean> getRows() {
        return rows;
    }

    public void setRows(List<VsCellBean> rows) {
        this.rows = rows;
    }

    public boolean isHasFoot() {
        return hasFoot;
    }

    public void setHasFoot(boolean hasFoot) {
        this.hasFoot = hasFoot;
    }

    public String getB() {
        return b;
    }


    public void setB(String b) {
        this.b = b;
    }
    public VsTableRowBean(List<VsCellBean> rows) {
        this.rows = rows;
    }

    public VsTableRowBean(String b, List<VsCellBean> rows) {
        this(rows);
        this.b=b;

    }
    public VsTableRowBean(String b, String sc, List<VsCellBean> rows) {
       this(b,rows);
        this.Sc=sc;

    }
    public VsTableRowBean(List<VsCellBean> rows, boolean hasHead , boolean hasFoot, String moduleTitle, String leftTitle, String centerTitle, String rightTitle) {
        this(rows,hasFoot);
        this.hasHead = hasHead;
        this.rightTitle = rightTitle;
        this.centerTitle = centerTitle;
        this.leftTitle = leftTitle;
        ModuleTitle = moduleTitle;
    }
    public VsTableRowBean(String b, List<VsCellBean> rows, boolean hasHead , boolean hasFoot, String moduleTitle, String leftTitle, String centerTitle, String rightTitle) {
        this(rows,hasHead,hasFoot,moduleTitle,leftTitle,centerTitle,rightTitle);
        this. b =  b;

    }
    public VsTableRowBean(String b, String sc, List<VsCellBean> rows, boolean hasHead , boolean hasFoot, String moduleTitle, String leftTitle, String centerTitle, String rightTitle) {
        this(b,rows,hasHead,hasFoot,moduleTitle,leftTitle,centerTitle,rightTitle);
        this.Sc=sc;
    }
    public VsTableRowBean(List<VsCellBean> rows, boolean hasFoot) {
        this(rows);
        this.hasFoot = hasFoot;

    }
    public VsTableRowBean(String b, List<VsCellBean> rows, boolean hasFoot) {
        this(rows,hasFoot);
        this.b=b;

    }
    public VsTableRowBean(String b, String sc, List<VsCellBean> rows, boolean hasFoot) {
        this(b,sc,rows);
        this.hasFoot=hasFoot;

    }
}
