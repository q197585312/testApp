package gaming178.com.casinogame.Bean;

public class SicboPool {
	private int bigSmall;
	private int threeforces;
	private int pair;
	private int allDices;
	private int waiDices;
	private int nineway;
	private int combination;
	private int minMax;
	public void Init()
	{
		minMax = 0;
		bigSmall = 0;
		threeforces = 0;
		pair = 0;
		allDices = 0;
		waiDices = 0;
		nineway = 0;
		combination = 0;
	}
	public int getBigSmall() {
		return bigSmall;
	}
	public void setBigSmall(int bigSmall) {
		this.bigSmall = bigSmall;
	}
	public int getThreeforces() {
		return threeforces;
	}
	public void setThreeforces(int threeforces) {
		this.threeforces = threeforces;
	}
	public int getPair() {
		return pair;
	}
	public void setPair(int pair) {
		this.pair = pair;
	}
	public int getAllDices() {
		return allDices;
	}
	public void setAllDices(int allDices) {
		this.allDices = allDices;
	}
	public int getWaiDices() {
		return waiDices;
	}
	public void setWaiDices(int waiDices) {
		this.waiDices = waiDices;
	}
	public int getNineway() {
		return nineway;
	}
	public void setNineway(int nineway) {
		this.nineway = nineway;
	}
	public int getCombination() {
		return combination;
	}
	public void setCombination(int combination) {
		this.combination = combination;
	}

	public int getMinMax() {
		return minMax;
	}

	public void setMinMax(int minMax) {
		this.minMax = minMax;
	}
}
