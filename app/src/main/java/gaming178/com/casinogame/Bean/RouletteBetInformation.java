package gaming178.com.casinogame.Bean;

import java.util.ArrayList;
import java.util.List;

public class RouletteBetInformation {

	private int allBetMoney;




	private List<BetDetail> betDetail;


	public RouletteBetInformation() {
		super();

		betDetail = new ArrayList<BetDetail>();

	}
	public void Init()
	{

		betDetail.clear();
		allBetMoney = 0;

		
	}
	//直注
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






	public List<BetDetail> getBetDetail() {
		return betDetail;
	}
}
