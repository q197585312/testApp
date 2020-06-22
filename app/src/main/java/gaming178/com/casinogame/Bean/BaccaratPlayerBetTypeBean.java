package gaming178.com.casinogame.Bean;

/**
 * Created by Administrator on 2017/7/6.
 */

public class BaccaratPlayerBetTypeBean {
    String name;
    int money;

    public BaccaratPlayerBetTypeBean(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
