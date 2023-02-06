package gaming178.com.casinogame.Bean;

/**
 * Created by Administrator on 2017/6/28.
 */

public class GameRefreshBean {
    String tableNum;
    String seatNum;
    String name;

    public GameRefreshBean(String tableNum, String seatNum, String name) {
        this.tableNum = tableNum;
        this.seatNum = seatNum;
        this.name = name;
    }

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
