package gaming178.com.casinogame.Bean;

public class DragonTiger {
	private String tableName;//桌号名称1，2，3，71
	private String shoeNumber;//靴号
	private String gameNumber;//局号
	private String gameType;//游戏编号
	private String bigRoad;//路子
	private String bigRoadOld;//路子
    private String bigEyesRoad;//大眼路
    private String smallEyesRoad;//小眼路
    private String roachRoad;//小强路
	private int timer;//倒计时
	private String videoUrlIndex;//视频源Index
	private int status;//桌状态
	private int gameStatus;
	private String serverTime;//服务器时间
	private double tableLimit;//桌红，表示这一桌可以下注的最大值
	private int totalDragon;
	private int totalTiger;
	private int totalTie;
	private int totalAll;
	private double wonMoney = 0;
	
	
	
	//每一桌游戏有4条限红
	private DragonTigerLimit dragonTigerLimit1;
	private DragonTigerLimit dragonTigerLimit2;
	private DragonTigerLimit dragonTigerLimit3;
	private DragonTigerLimit dragonTigerLimit4;
	//选中的哪一条限红
	private int limitIndex=1;//1表示第一条，2表示第二条
	//下注记录
	private DragonTigerBetInformation dragonTigerBetInformation;
	private DragonTigerBetInformation dragonTigerBetRepeatInformation;
	

	//彩池
	private DragonTigerPool dragonTigerPool;
	private String poolString ="";
	//结果
	private DragonTigerResults dragonTigerResults;
	//牌
	private DragonTigerPoker dragonTigerPoker;
	private String shoeNumberOld;


	public DragonTiger() {
		super();
		dragonTigerLimit1 = new DragonTigerLimit();
		dragonTigerLimit2 = new DragonTigerLimit();
		dragonTigerLimit3 = new DragonTigerLimit();
		dragonTigerLimit4 = new DragonTigerLimit();
		dragonTigerBetInformation = new DragonTigerBetInformation();
		dragonTigerBetRepeatInformation = new DragonTigerBetInformation();
		dragonTigerPool = new DragonTigerPool();
		dragonTigerPoker = new DragonTigerPoker();
		dragonTigerResults = new DragonTigerResults();
	}
	
	public void Init() 
	{
		dragonTigerPool.Init();
		dragonTigerPoker.Init();
		dragonTigerBetInformation.Init();
		dragonTigerResults.init();
	}
	/**
	 *
	 * @param road 大路
	 * @param startCol1 开始位置，大眼路从第二列开始startCol1是1，startCol2是2，小眼路(2,3),小强路(3,4)
	 * @param startCol2 前面的startCol1上无路子，从startCol2开始
	 * @param compareCol1 比对齐整的列 ,大眼路2，小眼路3，小强路4
	 * @param compareCol2 比对有无的列，大眼路1，小眼路2，小强路3
	 * @return
	 */
	public String GetSmallRoad(int [][] road,int startCol1,int startCol2,int compareCol1,int compareCol2)
	{
		String smallLuzi = "";
		int row = 0;
		int col = 0;
		int iStart = 0;
		if(road[1][startCol1] > 0){//从第二行第二列开始判断
			switch(startCol1)
			{
				case 1:
					iStart = 41;
					break;
				case 2:
					iStart = 71;
					break;
				case 3:
					iStart = 101;
					break;
				default:
					iStart = 0;
					break;
			}



		}else if(road[0][startCol2] > 0){

			switch(startCol2)
			{
				case 2:
					iStart = 80;
					break;
				case 3:
					iStart = 120;
					break;
				case 4:
					iStart = 160;
					break;
				default:
					iStart = 0;
					break;
			}
		}else{
			iStart = 0;
		}
		if(iStart > 0){
			row = 0;
			col = 0;
			for(int i=iStart;i<1600;i++)// 从第31个数开始，要计算出来第31属于几行几列，求余数计算出行,整除计算出列数
			{
				row = i%40;
				col = i/40;
				//最先要判断第0行是否有数据，如果没有数据后后面的就不需要再循环了，循环结束,如果不是第0行，只需要跳出本次循环即可
				if(road[0][col] == 0)
					break;
				else if(road[row][col] == 0){
					continue;
				}
				//先判断是否是第0行，如果是就需要判断齐整，如果不是先判断有无，再判断是否是直落
				if(row == 0)//齐整
				{
					//判断前一列，跟前前一列路子个数是否相等
					if((col-1) >= 0 && (col-compareCol1) >= 0 ){
						int iCount1=0;//前一列路子个数
						int iCount2=0;//前前一列路子个数
						for(int j=0;j<40;j++)
						{
							if(road[j][col-1] > 0)
								iCount1++;
							else
								break;
						}
						for(int j=0;j<40;j++)
						{
							if(road[j][col-compareCol1] > 0)
								iCount2++;
							else
								break;
						}
						if(iCount1==iCount2){//齐整
							smallLuzi +="1#";
						}else{
							smallLuzi +="5#";
						}

					}

				}
				else//不需要判断齐整
				{
					//先判断有有无
					if(road[row][col-compareCol2] > 0)//有
						smallLuzi +="1#";
					else//如果是无，则再需要判断直落
					{
						if(road[row-1][col-compareCol2] == 0)//此参照点的上一个前一 个路子也是无，那么是直落
						{
							smallLuzi +="1#";
						}else
						{
							smallLuzi +="5#";
						}
					}
				}
			}
		}
		return smallLuzi;
	}

	public  void setDragonTigerCount(String luzi)
	{
		if (luzi == null || "null".equals(luzi) || luzi.length() <= 0 || "null#".equals(luzi)) {

			return;
		}


		String []array = luzi.split("\\#");
		if (array.length == 0) {
			return;
		}
		totalDragon = 0;
		totalTiger = 0;
		totalTie = 0;


		//NSLog(@"baccaratTableinfo_array:%@",array);
		for(int iNum=0;iNum<array.length;iNum++)
		{
			String sValue = array[iNum];
			if (sValue == null || sValue.length() < 1) {
				break;
			}

			String []arrayDetail = sValue.split("\\-");
			String res = arrayDetail[0];


			//     String resBigSmall = arrayDetail[1];

			if("1".equals(res)||
					"2".equals(res)||
					"3".equals(res)||
					"4".equals(res))
			{
				totalDragon++;

			}
			else if("5".equals(res)||
					"6".equals(res)||
					"7".equals(res)||
					"8".equals(res))
			{
				totalTiger++;

			}
			else if("9".equals(res)||
					"10".equals(res)||
					"11".equals(res)||
					"12".equals(res))
			{
				totalTie++;

			}
         /*   if ("5".equals(resBigSmall)) {
            	totalBig ++;
            }else {
            	totalSmall ++;
            }*/
		}

	}
	public DragonTigerLimit getDragonTigerLimit(int index)
	{
		switch (index)
		{
			case 1:
				return dragonTigerLimit1;
			case 2:
				return dragonTigerLimit2;
			case 3:
				return dragonTigerLimit3;
			case 4:
				return dragonTigerLimit4;
			default:
				return null;
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getBigRoad() {
		return bigRoad;
	}

	public void setBigRoad(String bigRoad) {
		this.bigRoad = bigRoad;
	}

	public String getBigEyesRoad() {
		return bigEyesRoad;
	}

	public void setBigEyesRoad(String bigEyesRoad) {
		this.bigEyesRoad = bigEyesRoad;
	}

	public double getTableLimit() {
		return tableLimit;
	}

	public void setTableLimit(double tableLimit) {
		this.tableLimit = tableLimit;
	}

	public String getSmallEyesRoad() {
		return smallEyesRoad;
	}

	public void setSmallEyesRoad(String smallEyesRoad) {
		this.smallEyesRoad = smallEyesRoad;
	}

	public String getRoachRoad() {
		return roachRoad;
	}

	public void setRoachRoad(String roachRoad) {
		this.roachRoad = roachRoad;
	}

	public int getTimer() {
		return timer;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
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

	public int getTotalDragon() {
		return totalDragon;
	}

	public void setTotalDragon(int totalDragon) {
		this.totalDragon = totalDragon;
	}

	public int getTotalTiger() {
		return totalTiger;
	}

	public void setTotalTiger(int totalTiger) {
		this.totalTiger = totalTiger;
	}

	public int getTotalTie() {
		return totalTie;
	}

	public void setTotalTie(int totalTie) {
		this.totalTie = totalTie;
	}

	public int getTotalAll() {
		return totalAll;
	}

	public void setTotalAll(int totalAll) {
		this.totalAll = totalAll;
	}

	public DragonTigerLimit getDragonTigerLimit1() {
		return dragonTigerLimit1;
	}

	public void setDragonTigerLimit1(DragonTigerLimit dragonTigerLimit1) {
		this.dragonTigerLimit1 = dragonTigerLimit1;
	}

	public DragonTigerLimit getDragonTigerLimit2() {
		return dragonTigerLimit2;
	}

	public void setDragonTigerLimit2(DragonTigerLimit dragonTigerLimit2) {
		this.dragonTigerLimit2 = dragonTigerLimit2;
	}

	public DragonTigerLimit getDragonTigerLimit3() {
		return dragonTigerLimit3;
	}

	public void setDragonTigerLimit3(DragonTigerLimit dragonTigerLimit3) {
		this.dragonTigerLimit3 = dragonTigerLimit3;
	}

	public DragonTigerLimit getDragonTigerLimit4() {
		return dragonTigerLimit4;
	}

	public void setDragonTigerLimit4(DragonTigerLimit dragonTigerLimit4) {
		this.dragonTigerLimit4 = dragonTigerLimit4;
	}

	public int getLimitIndex() {
		return limitIndex;
	}

	public void setLimitIndex(int limitIndex) {
		this.limitIndex = limitIndex;
	}

	public DragonTigerBetInformation getDragonTigerBetInformation() {
		return dragonTigerBetInformation;
	}

	public void setDragonTigerBetInformation(
			DragonTigerBetInformation dragonTigerBetInformation) {
		this.dragonTigerBetInformation = dragonTigerBetInformation;
	}

	public DragonTigerPool getDragonTigerPool() {
		return dragonTigerPool;
	}

	public void setDragonTigerPool(DragonTigerPool dragonTigerPool) {
		this.dragonTigerPool = dragonTigerPool;
	}

	public DragonTigerResults getDragonTigerResults() {
		return dragonTigerResults;
	}

	public DragonTigerPoker getDragonTigerPoker() {
		return dragonTigerPoker;
	}

	public void setDragonTigerPoker(DragonTigerPoker dragonTigerPoker) {
		this.dragonTigerPoker = dragonTigerPoker;
	}

	public String getPoolString() {
		return poolString;
	}

	public void setPoolString(String poolString) {
		this.poolString = poolString;
	}

	public DragonTigerBetInformation getDragonTigerBetRepeatInformation() {
		return dragonTigerBetRepeatInformation;
	}


	public void setShoeNumberOld(String shoeNumberOld) {
		this.shoeNumberOld = shoeNumberOld;
	}

	public String getBigRoadOld() {
		return bigRoadOld;
	}

	public void setBigRoadOld(String bigRoadOld) {
		this.bigRoadOld = bigRoadOld;
	}

	public String getShoeNumberOld() {
		return shoeNumberOld;
	}

	public double getWonMoney() {
		return wonMoney;
	}

	public void setWonMoney(double wonMoney) {
		this.wonMoney = wonMoney;
	}
}
