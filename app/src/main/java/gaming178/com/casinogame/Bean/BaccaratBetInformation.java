package gaming178.com.casinogame.Bean;

public class BaccaratBetInformation {
	private String shoeNumber;
	private String gameNumber;


	private int banker;
	private int player;
	private int tie;
	private int lucky;
	private int any;
	private int playerN;
	private int perfect;
	private int bankerN;
	private int cowBanker;
	private int cowTie;
	private int cowPlayer;
	private int small;
	private int big;
	private int bankerPair;
	private int allBetMoney;
	private int playerPair;

	public int getLucky() {
		return lucky;
	}

	public void setLucky(int lucky) {
		this.lucky = lucky;
	}

	public int getAny() {
		return any;
	}

	public void setAny(int any) {
		this.any = any;
	}

	public int getPlayerN() {
		return playerN;
	}

	public void setPlayerN(int playerN) {
		this.playerN = playerN;
	}

	public int getPerfect() {
		return perfect;
	}

	public void setPerfect(int perfect) {
		this.perfect = perfect;
	}

	public int getBankerN() {
		return bankerN;
	}

	public void setBankerN(int bankerN) {
		this.bankerN = bankerN;
	}

	public int getCowBanker() {
		return cowBanker;
	}

	public void setCowBanker(int cowBanker) {
		this.cowBanker = cowBanker;
	}

	public int getCowTie() {
		return cowTie;
	}

	public void setCowTie(int cowTie) {
		this.cowTie = cowTie;
	}

	public int getCowPlayer() {
		return cowPlayer;
	}

	public void setCowPlayer(int cowPlayer) {
		this.cowPlayer = cowPlayer;
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
		lucky= 0;
		any= 0;
		playerN= 0;
		perfect= 0;
		bankerN= 0;
		cowBanker= 0;
		cowTie= 0;
		cowPlayer= 0;
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
