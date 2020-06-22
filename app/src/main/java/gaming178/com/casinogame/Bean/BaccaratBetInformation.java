package gaming178.com.casinogame.Bean;

public class BaccaratBetInformation {
	private String shoeNumber;
	private String gameNumber;
	

	private int banker;
	private int player;
	private int tie;
	private int small;
	private int big;
	private int bankerPair;
	private int allBetMoney;
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
	public int getSmall() {
		return small;
	}
	public void setSmall(int small) {
		this.small = small;
	}
	public int getBig() {
		return big;
	}
	public void setBig(int big) {
		this.big = big;
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
		allBetMoney = 0;
	}
	public String getShoeNumber() {
		return shoeNumber;
	}
	public void setShoeNumber(String shoeNumber) {
		this.shoeNumber = shoeNumber;
	}
	public String getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(String gameNumber) {
		this.gameNumber = gameNumber;
	}

	public int getAllBetMoney() {
		return allBetMoney;
	}

	public void setAllBetMoney(int allBetMoney) {
		this.allBetMoney = allBetMoney;
	}
}
