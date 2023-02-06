package nanyang.com.dig88.Table.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class TableBallgameBean<T> {
    String tableString;
    String tableId;

    String tableDrawableRes;
    List<T> items;

    public TableBallgameBean(String tableId ,String tableString, List<T> tableItemBeans) {
        this.tableId=tableId;
        this.tableString=tableString;
        this.items=tableItemBeans;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableString() {
        return tableString;
    }

    public void setTableString(String tableString) {
        this.tableString = tableString;
    }

    public String getTableDrawableRes() {
        return tableDrawableRes;
    }

    public void setTableDrawableRes(String tableDrawableRes) {
        this.tableDrawableRes = tableDrawableRes;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
