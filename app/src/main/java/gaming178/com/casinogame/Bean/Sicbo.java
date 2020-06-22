package gaming178.com.casinogame.Bean;


public class Sicbo {
	private String tableName;//桌号名称21
	private String gameNumber;//局号
	private String gameType;//游戏编号
	private String road;//路子
	private String roadOld;//路子
	private int timer;//倒计时
	private String videoUrlIndex;//视频源Index
	private int status;//桌状态
	private String serverTime;//服务器时间
	private int gameStatus;
	private double tableLimit;//桌红，表示这一桌可以下注的最大值
	private int even;
	private int odd;
	private int big;
	private int small;
	private int waidic;
	private double wonMoney;
	
	// 每一桌游戏有4条限红
	private SicboLimit sicboLimit1;
	private SicboLimit sicboLimit2;
	private SicboLimit sicboLimit3;
	private SicboLimit sicboLimit4;
	// 选中的哪一条限红
	private int limitIndex=1;// 1表示第一条，2表示第二条
	// 下注记录
	private SicboBetInformation sicboBetInformation;
	private SicboBetInformation sicboBetRepeatInformation;
	

	
	// 彩池
	private SicboPool sicboPool;
	private String poolString = "";
	// 结果
	private String result;
	public Sicbo() {
		super();
		sicboLimit1 = new SicboLimit();
		sicboLimit2 = new SicboLimit();
		sicboLimit3 = new SicboLimit();
		sicboLimit4 = new SicboLimit();
		sicboBetInformation = new SicboBetInformation();
		sicboBetRepeatInformation = new SicboBetInformation();

		sicboPool = new SicboPool();
		
	}
	public void Init()
	{
		sicboBetInformation.Init();
		sicboPool.Init();
		result = "";
	}
	public void getTotal(String luzi)
	{
		if (luzi == null || luzi.length() <= 0 || "null".equals(luzi) || "null#".equals(luzi)) {
			return ;
		}
		String luziInfo[] = luzi.split("\\#");
		if (luziInfo.length <= 0 && luziInfo.length >100) {//数据格式不对
			return  ;
		}


		int index = luziInfo.length ;
		int point = 0;
		even = 0;
		odd = 0;
		big = 0;
		small = 0;
		waidic = 0;
	//	Log.i(WebSiteUrl.Tag,"getTotal = "+index);
		for (int i=0; i<index; i++)
		{
			String luziInfoDetail[] = luziInfo[i].split("\\-");//路子相信信息9-8-7
			int res = Integer.parseInt(luziInfoDetail[0]);
			//只有骰宝才这样执行
			if(luziInfoDetail.length == 3)
			{
				point = Integer.parseInt(luziInfoDetail[0]) + Integer.parseInt(luziInfoDetail[1]) + Integer.parseInt(luziInfoDetail[2]);
				if((Integer.parseInt(luziInfoDetail[0]) == Integer.parseInt(luziInfoDetail[1])) && (Integer.parseInt(luziInfoDetail[0])==Integer.parseInt(luziInfoDetail[2])))
					waidic++;


				if(point > 10){
					big++;
				}else
					small++;

				if(point%2 ==0){
					even++;
				}else
					odd++;


			}
		}

	}
	public SicboLimit getSicboLimit(int index)
	{
		switch (index)
		{
			case 1:
				return sicboLimit1;
			case 2:
				return sicboLimit2;
			case 3:
				return sicboLimit3;
			case 4:
				return sicboLimit4;
			default:
				return null;
		}
	}
	public double getTableLimit() {
		return tableLimit;
	}

	public void setTableLimit(double tableLimit) {
		this.tableLimit = tableLimit;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(String gameNumber) {
		this.gameNumber = gameNumber;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public String getVideoUrlIndex() {
		return videoUrlIndex;
	}
	public void setVideoUrlIndex(String videoUrlIndex) {
		this.videoUrlIndex = videoUrlIndex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getServerTime() {
		return serverTime;
	}
	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}
	public SicboLimit getSicboLimit1() {
		return sicboLimit1;
	}
	public void setSicboLimit1(SicboLimit sicboLimit1) {
		this.sicboLimit1 = sicboLimit1;
	}
	public SicboLimit getSicboLimit2() {
		return sicboLimit2;
	}
	public void setSicboLimit2(SicboLimit sicboLimit2) {
		this.sicboLimit2 = sicboLimit2;
	}
	public SicboLimit getSicboLimit3() {
		return sicboLimit3;
	}
	public void setSicboLimit3(SicboLimit sicboLimit3) {
		this.sicboLimit3 = sicboLimit3;
	}
	public SicboLimit getSicboLimit4() {
		return sicboLimit4;
	}
	public void setSicboLimit4(SicboLimit sicboLimit4) {
		this.sicboLimit4 = sicboLimit4;
	}
	public int getLimitIndex() {
		return limitIndex;
	}
	public void setLimitIndex(int limitIndex) {
		this.limitIndex = limitIndex;
	}
	
	public SicboPool getSicboPool() {
		return sicboPool;
	}
	public void setSicboPool(SicboPool sicboPool) {
		this.sicboPool = sicboPool;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public SicboBetInformation getSicboBetInformation() {
		return sicboBetInformation;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String getRoadOld() {
		return roadOld;
	}

	public void setRoadOld(String roadOld) {
		this.roadOld = roadOld;
	}
	public int getEven() {
		return even;
	}

	public void setEven(int even) {
		this.even = even;
	}

	public int getOdd() {
		return odd;
	}

	public void setOdd(int odd) {
		this.odd = odd;
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

	public int getWaidic() {
		return waidic;
	}

	public void setWaidic(int waidic) {
		this.waidic = waidic;
	}

	public double getWonMoney() {
		return wonMoney;
	}

	public void setWonMoney(double wonMoney) {
		this.wonMoney = wonMoney;
	}

	public String getPoolString() {
		return poolString;
	}

	public void setPoolString(String poolString) {
		this.poolString = poolString;
	}

	public SicboBetInformation getSicboBetRepeatInformation() {
		return sicboBetRepeatInformation;
	}


}
