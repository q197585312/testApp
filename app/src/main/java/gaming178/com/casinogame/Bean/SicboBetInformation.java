package gaming178.com.casinogame.Bean;

import java.util.ArrayList;
import java.util.List;

public class SicboBetInformation {
	private String gameNumber;
	private int big;
	private int small;
	private int odd;
	private int even;
	private int allDices;
	private int allBetMoney;
	
	private List<BetDetail> pairs;
	private List<BetDetail> waiDices;
	private List<BetDetail> nineway;
	private List<BetDetail> threeforces;
	private List<BetDetail> combinations;
	public SicboBetInformation() {
		super();
		pairs = new ArrayList<BetDetail>();
		waiDices = new ArrayList<BetDetail>();
		nineway = new ArrayList<BetDetail>();
		threeforces = new ArrayList<BetDetail>();
		combinations = new ArrayList<BetDetail>();
	}
	public void Init()
	{
		pairs.clear();
		waiDices.clear();
		nineway.clear();
		threeforces.clear();
		combinations.clear();
		big = 0;
		small = 0;
		odd = 0;
		even = 0;
		allDices = 0;
		allBetMoney = 0;
	}
	public int getWaidicesBetMoney(String type) {
		int betMoney = 0;
		for(int i=0;i<waiDices.size();i++)
		{
			if(type.equals(waiDices.get(i).getNumber())) {
				betMoney = waiDices.get(i).getMoney();
				break;
			}

		}
		return betMoney;
	}
	public int getPairsBetMoney(String type) {
		int betMoney = 0;
		for(int i=0;i<pairs.size();i++)
		{
			if(type.equals(pairs.get(i).getNumber())) {
				betMoney = pairs.get(i).getMoney();
				break;
			}

		}
		return betMoney;
	}
	public int getPointsBetMoney(String type) {
		int betMoney = 0;
		for(int i=0;i<combinations.size();i++)
		{
			if(type.equals(combinations.get(i).getNumber())) {
				betMoney = combinations.get(i).getMoney();
				break;
			}

		}
		return betMoney;
	}
	public int getNinewayBetMoney(String type) {
		int betMoney = 0;
		for(int i=0;i<nineway.size();i++)
		{
			if(type.equals(nineway.get(i).getNumber())) {
				betMoney = nineway.get(i).getMoney();
				break;
			}

		}
		return betMoney;
	}
	public int getThreeforceBetMoney(String type) {
		int betMoney = 0;
		for(int i=0;i<threeforces.size();i++)
		{
			if(type.equals(threeforces.get(i).getNumber())) {
				betMoney = threeforces.get(i).getMoney();
				break;
			}

		}
		return betMoney;
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
	public int getOdd() {
		return odd;
	}
	public void setOdd(int odd) {
		this.odd = odd;
	}
	public int getEven() {
		return even;
	}
	public void setEven(int even) {
		this.even = even;
	}
	public int getAllDices() {
		return allDices;
	}
	public void setAllDices(int allDices) {
		this.allDices = allDices;
	}
	public List<BetDetail> getPairs() {
		return pairs;
	}
	public List<BetDetail> getWaiDices() {
		return waiDices;
	}
	public List<BetDetail> getNineway() {
		return nineway;
	}
	public List<BetDetail> getThreeforces() {
		return threeforces;
	}
	public List<BetDetail> getCombinations() {
		return combinations;
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
