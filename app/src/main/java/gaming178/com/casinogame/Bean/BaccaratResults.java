package gaming178.com.casinogame.Bean;

public class BaccaratResults {
	private int banker_palyer_tie;

	private int bankerPair;
	private int playerPair;
	private int big_small;

	public void Init()
	{
		banker_palyer_tie = 0;
		bankerPair = 0;
		playerPair = 0;
		big_small = 0;
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
