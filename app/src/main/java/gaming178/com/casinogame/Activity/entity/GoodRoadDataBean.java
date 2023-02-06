package gaming178.com.casinogame.Activity.entity;

/**
 * Created by Administrator on 2020/6/12.
 */

public class GoodRoadDataBean {
    private String tableId;
    private String tableName;
    private int pic;

    public GoodRoadDataBean(String tableId, String tableName, int pic) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.pic = pic;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "GoodRoadDataBean{" +
                "tableId='" + tableId + '\'' +
                ", tableName='" + tableName + '\'' +
                ", pic=" + pic +
                '}';
    }
}
