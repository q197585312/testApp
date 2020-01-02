package com.nanyang.app.main.home.sport.live;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ASUS on 2019/12/30.
 */

public class LiveSelectedHelper {
    public List<MenuItemInfo> getList() {
        return list;
    }

    List<MenuItemInfo> list;

    public HashMap<Boolean, MenuItemInfo> getLinkMap() {
        return linkMap;
    }

    HashMap<Boolean, MenuItemInfo> linkMap;

    public LiveSelectedHelper() {
        list = new ArrayList<>();
        linkMap = new HashMap<>();
        list.add(new MenuItemInfo(R.string.all, AfbApplication.getInstance().getString(R.string.all)));
        list.add(new MenuItemInfo(R.string.HDP_OU, AfbApplication.getInstance().getString(R.string.HDP_OU)));
        list.add(new MenuItemInfo(R.string.X1X2, AfbApplication.getInstance().getString(R.string.X1X2)));
        list.add(new MenuItemInfo(R.string.OE, AfbApplication.getInstance().getString(R.string.OE)));
        list.add(new MenuItemInfo(R.string.DC, AfbApplication.getInstance().getString(R.string.DC)));
        list.add(new MenuItemInfo(R.string.CS, AfbApplication.getInstance().getString(R.string.CS)));
        list.add(new MenuItemInfo(R.string.TG, AfbApplication.getInstance().getString(R.string.TG)));
        list.add(new MenuItemInfo(R.string.HTFT, AfbApplication.getInstance().getString(R.string.HTFT)));
        list.add(new MenuItemInfo(R.string.FGLG, AfbApplication.getInstance().getString(R.string.FGLG)));
        list.add(new MenuItemInfo(R.string.MM_HDP_OU, AfbApplication.getInstance().getString(R.string.MM_HDP_OU)));
        list.add(new MenuItemInfo(R.string.Home_Away_TG, AfbApplication.getInstance().getString(R.string.Home_Away_TG)));
        list.add(new MenuItemInfo(R.string.FT15, AfbApplication.getInstance().getString(R.string.FT15)));
        linkMap.put(true, list.get(0));
    }

    public void putIndex(int index) {
        linkMap.put(true, list.get(index));
    }
    public boolean isPositionSelected(int index) {
        return linkMap.get(true).getRes()==list.get(index).getRes();
    }
}
