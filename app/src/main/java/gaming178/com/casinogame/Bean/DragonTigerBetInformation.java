package gaming178.com.casinogame.Bean;

import java.util.ArrayList;
import java.util.List;

public class DragonTigerBetInformation {
	private String shoeNumber;
	private String gameNumber;
	private List<BetDetail> betDetail;
	private int allBetMoney;
	public void Init()
	{
		betDetail.clear();
		allBetMoney = 0;
	}
	public DragonTigerBetInformation() {
		super();

		betDetail = new ArrayList<BetDetail>();

	}
	public int getNumberBetMoney(String type) {
		int betMoney = 0;
		for(int i=0;i<betDetail.size();i++)
		{
			if(type.equals(betDetail.get(i).getNumber())) {
				betMoney = betDetail.get(i).getMoney();
				break;
			}

		}
		return betMoney;
	}
	public int getAllBetMoney() {
		return allBetMoney;
	}

	public void setAllBetMoney(int allBetMoney) {
		this.allBetMoney = allBetMoney;
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

	public List<BetDetail> getBetDetail() {
		return betDetail;
	}
}
