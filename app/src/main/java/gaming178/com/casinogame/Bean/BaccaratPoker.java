package gaming178.com.casinogame.Bean;

public class BaccaratPoker {
	private int banker1;
	private int banker2;
	private int banker3;
	private int player1;
	private int player2;
	private int player3;
	public int getBanker1() {
		return banker1;
	}
	public void setBanker1(int banker1) {
		this.banker1 = banker1;
	}
	public int getBanker2() {
		return banker2;
	}
	public void setBanker2(int banker2) {
		this.banker2 = banker2;
	}
	public int getBanker3() {
		return banker3;
	}
	public void setBanker3(int banker3) {
		this.banker3 = banker3;
	}
	public int getPlayer1() {
		return player1;
	}
	public void setPlayer1(int player1) {
		this.player1 = player1;
	}
	public int getPlayer2() {
		return player2;
	}
	public void setPlayer2(int player2) {
		this.player2 = player2;
	}
	public int getPlayer3() {
		return player3;
	}
	public void setPlayer3(int player3) {
		this.player3 = player3;
	}
	public void Init(){
		banker1 = 0;
		banker2 = 0;
		banker3 = 0;
		player1 = 0;
		player2 = 0;
		player3 = 0;
	}

}
