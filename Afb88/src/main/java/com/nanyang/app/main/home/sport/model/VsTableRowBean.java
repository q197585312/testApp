package com.nanyang.app.main.home.sport.model;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class VsTableRowBean extends SportInfo {
    public boolean isFh() {
        return isFh;
    }

    public VsTableRowBean setFh(boolean fh) {
        isFh = fh;
        return this;
    }

    boolean isFh;
    private CharSequence Sc;
    private CharSequence b;
    CharSequence ModuleTitle;
    CharSequence leftTitle;
    CharSequence centerTitle;
    CharSequence rightTitle;
    boolean hasHead;
    List<VsCellBean> rows;
    boolean hasFoot;


    public CharSequence getSc() {
        return Sc;
    }

    public void setSc(CharSequence sc) {
        Sc = sc;
    }

    public CharSequence getModuleTitle() {
        return ModuleTitle;
    }

    public void setModuleTitle(CharSequence moduleTitle) {
        ModuleTitle = moduleTitle;
    }

    public CharSequence getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(CharSequence leftTitle) {
        this.leftTitle = leftTitle;
    }

    public CharSequence getCenterTitle() {
        return centerTitle;
    }

    public void setCenterTitle(CharSequence centerTitle) {
        this.centerTitle = centerTitle;
    }

    public CharSequence getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(CharSequence rightTitle) {
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

    public CharSequence getB() {
        return b;
    }


    public void setB(CharSequence b) {
        this.b = b;
    }

    public VsTableRowBean(List<VsCellBean> rows) {
        this.rows = rows;
    }

    public VsTableRowBean(CharSequence b, List<VsCellBean> rows) {
        this(rows);
        this.b = b;

    }

    public VsTableRowBean(CharSequence b, CharSequence sc, List<VsCellBean> rows) {
        this(b, rows);
        this.Sc = sc;

    }

    public VsTableRowBean(List<VsCellBean> rows, boolean hasHead, boolean hasFoot, CharSequence moduleTitle, CharSequence leftTitle, CharSequence centerTitle, CharSequence rightTitle) {
        this(rows, hasFoot);
        this.hasHead = hasHead;
        this.rightTitle = rightTitle;
        this.centerTitle = centerTitle;
        this.leftTitle = leftTitle;
        ModuleTitle = moduleTitle;
    }

    public VsTableRowBean(CharSequence b, List<VsCellBean> rows, boolean hasHead, boolean hasFoot, CharSequence moduleTitle, CharSequence leftTitle, CharSequence centerTitle, CharSequence rightTitle) {
        this(rows, hasHead, hasFoot, moduleTitle, leftTitle, centerTitle, rightTitle);
        this.b = b;

    }

    public VsTableRowBean(CharSequence b, CharSequence sc, List<VsCellBean> rows, boolean hasHead, boolean hasFoot, CharSequence moduleTitle, CharSequence leftTitle, CharSequence centerTitle, CharSequence rightTitle) {
        this(b, rows, hasHead, hasFoot, moduleTitle, leftTitle, centerTitle, rightTitle);
        this.Sc = sc;
    }

    public VsTableRowBean(List<VsCellBean> rows, boolean hasFoot) {
        this(rows);
        this.hasFoot = hasFoot;

    }

    public VsTableRowBean(CharSequence b, List<VsCellBean> rows, boolean hasFoot) {
        this(rows, hasFoot);
        this.b = b;

    }

    public VsTableRowBean(CharSequence b, CharSequence sc, List<VsCellBean> rows, boolean hasFoot) {
        this(b, sc, rows);
        this.hasFoot = hasFoot;
    }

}
