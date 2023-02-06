package gaming178.com.casinogame.Bean;

public class BaccaratResults {
	private int banker_palyer_tie;

	private int bankerPair;
	private int playerPair;
	private int big_small;
	private int lucky6;
	private int anyPairs;
	private int perfectPairs;
	private int nBanker;
	private int nPlayer;

	public void Init()
	{
		banker_palyer_tie = 0;
		bankerPair = 0;
		playerPair = 0;
		big_small = 0;
		lucky6 = 0;
		anyPairs = 0;
		perfectPairs = 0;
		nBanker = 0;
		nPlayer = 0;
	}

	public int getLucky6() {
		return lucky6;
	}

	public void setLucky6(int lucky6) {
		this.lucky6 = lucky6;
	}

	public int getAnyPairs() {
		return anyPairs;
	}

	public void setAnyPairs(int anyPairs) {
		this.anyPairs = anyPairs;
	}

	public int getPerfectPairs() {
		return perfectPairs;
	}

	public void setPerfectPairs(int perfectPairs) {
		this.perfectPairs = perfectPairs;
	}

	public int getnBanker() {
		return nBanker;
	}

	public void setnBanker(int nBanker) {
		this.nBanker = nBanker;
	}

	public int getnPlayer() {
		return nPlayer;
	}

	public void setnPlayer(int nPlayer) {
		this.nPlayer = nPlayer;
	}

	public int getBanker_palyer_tie() {
		return banker_palyer_tie;
	}

	public void setBanker_palyer_tie(int banker_palyer_tie) {
		this.banker_palyer_tie = banker_palyer_tie;
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

	public int getBig_small() {
		return big_small;
	}

	public void setBig_small(int big_small) {
		this.big_small = big_small;
	}
}
