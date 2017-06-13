package com.nanyang.app.main.center.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public class ResultDicDataList implements Serializable {
    List<ResultInfo> dicData;

    public List<ResultInfo> getDicData() {
        return dicData;
    }

    public void setDicData(List<ResultInfo> dicData) {
        this.dicData = dicData;
    }
}
