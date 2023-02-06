package gaming178.com.casinogame.Bean;

public class DragonTigerResults {
	private int dragon_tiger_tie;
    private int dragon_odd_even;
    private int dragon_red_black;
    private int tiger_odd_even;
    private int tiger_red_black;
    public void init()
    {
        dragon_tiger_tie = 0;
        dragon_odd_even = 0;
        dragon_red_black = 0;
        tiger_odd_even = 0;
        tiger_red_black = 0;
    }
    public int getDragon_tiger_tie() {
        return dragon_tiger_tie;
    }

    public void setDragon_tiger_tie(int dragon_tiger_tie) {
        this.dragon_tiger_tie = dragon_tiger_tie;
    }

    public int getDragon_odd_even() {
        return dragon_odd_even;
    }

    public void setDragon_odd_even(int dragon_odd_even) {
        this.dragon_odd_even = dragon_odd_even;
    }

    public int getDragon_red_black() {
        return dragon_red_black;
    }

    public void setDragon_red_black(int dragon_red_black) {
        this.dragon_red_black = dragon_red_black;
    }

    public int getTiger_odd_even() {
        return tiger_odd_even;
    }

    public void setTiger_odd_even(int tiger_odd_even) {
        this.tiger_odd_even = tiger_odd_even;
    }

    public int getTiger_red_black() {
        return tiger_red_black;
    }

    public void setTiger_red_black(int tiger_red_black) {
        this.tiger_red_black = tiger_red_black;
    }
}
