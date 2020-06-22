package gaming178.com.casinogame.Bean;

public class BaccaratPool {
	private int banker;
	private int player;
	private int tie;
	private int big;
	private int small;
	private int bankerPair;
	private int playerPair;
	public int getBanker() {
		return banker;
	}
	public void setBanker(int banker) {
		this.banker = banker;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public int getTie() {
		return tie;
	}
	public void setTie(int tie) {
		this.tie = tie;
	}
	public int getBig() {
		return big;
	}
	public void setBig(int big) {
		this.big = big;
	}
	public int getSmall() {
		return small;
	}
	public void setSmall(int small) {
		this.small = small;
	}
	public int getBankerPair() {
		return bankerPair;
	}
	public void setBankerPair(int bankerPair) {
		this.bankerPair = bankerPair;
	}
	public int getPlayerPair() {
		return playerPair;
	}
	public void setPlayerPair(int playerPair) {
		this.playerPair = playerPair;
	}
	public void Init()
	{
		banker = 0;
		player = 0;
		bankerPair = 0;
		playerPair = 0;
		tie = 0;
		big = 0;
		small = 0;
	}

}
