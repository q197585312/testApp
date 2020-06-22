package gaming178.com.casinogame.Bean;


public class Roulette {
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

	private int red;
	private int black;
	private int zero;
	private int even;
	private int odd;
	private int small;
	private int big;
	private double wonMoney;

	
	// 每一桌游戏有4条限红
	private RouletteLimit rouletteLimit1;
	private RouletteLimit rouletteLimit2;
	private RouletteLimit rouletteLimit3;
	private RouletteLimit rouletteLimit4;
	// 选中的哪一条限红
	private int limitIndex=1;// 1表示第一条，2表示第二条
	// 下注记录
	private RouletteBetInformation rouletteBetInformation;
	// 下注记录
	private RouletteBetInformation rouletteBetRepeatInformation;

	// 彩池
	private RoulettePool roulettePool;
	private String poolString = "";
	// 结果
	private String result;
	public Roulette() {
		super();
		rouletteLimit1 = new RouletteLimit();
		rouletteLimit2 = new RouletteLimit();
		rouletteLimit3 = new RouletteLimit();
		rouletteLimit4 = new RouletteLimit();
		rouletteBetInformation = new RouletteBetInformation();
		rouletteBetRepeatInformation = new RouletteBetInformation();
		roulettePool = new RoulettePool();
	}

	// 每一局完毕，下面的信息要初始化一次
	public void Init() {
		rouletteBetInformation.Init();
		roulettePool.Init();
		result = "";
	}

    public void getTotal(String luzi)
	{
		try{
			String roadDetail[] = luzi.split("\\#");
			if(roadDetail != null) {

				red = 0;
				black = 0;
				zero = 0;
				even = 0;
				odd = 0;
				small = 0;
				big = 0;
				for (int i = 78; i < roadDetail.length; i++) {
					switch (roadDetail[i])
					{
						case "2":
						case "4":
						case "6":
						case "8":
						case "10":
						case "11":
						case "13":
						case "15":
						case "17":
						case "20":
						case "22":
						case "24":
						case "26":
						case "28":
						case "29":
						case "31":
						case "33":
						case "35":
							black++;
							break;
						case "0":
							zero++;
							break;
						default:
							red++;
							break;
					}
					if(!"0".endsWith(roadDetail[i])){
						if(Integer.parseInt(roadDetail[i]) %2 ==0){
							even++;
						}else
							odd++;
						if(Integer.parseInt(roadDetail[i]) > 18){
							big++;
						}else
							small++;
					}
				}

			}
		}catch (Exception e)
		{

		}

	}
	public RouletteLimit getRouletteLimit(int index)
	{
		switch (index)
		{
			case 1:
				return rouletteLimit1;
			case 2:
				return rouletteLimit2;
			case 3:
				return rouletteLimit3;
			case 4:
				return rouletteLimit4;
			default:
				return rouletteLimit1;
		}
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

	public double getTableLimit() {
		return tableLimit;
	}

	public void setTableLimit(double tableLimit) {
		this.tableLimit = tableLimit;
	}

	public RouletteLimit getRouletteLimit1() {
		return rouletteLimit1;
	}

	public void setRouletteLimit1(RouletteLimit rouletteLimit1) {
		this.rouletteLimit1 = rouletteLimit1;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(int gameStatus) {
		this.gameStatus = gameStatus;
	}

	public RouletteLimit getRouletteLimit2() {
		return rouletteLimit2;
	}

	public void setRouletteLimit2(RouletteLimit rouletteLimit2) {
		this.rouletteLimit2 = rouletteLimit2;
	}

	public RouletteLimit getRouletteLimit3() {
		return rouletteLimit3;
	}

	public void setRouletteLimit3(RouletteLimit rouletteLimit3) {
		this.rouletteLimit3 = rouletteLimit3;
	}

	public RouletteLimit getRouletteLimit4() {
		return rouletteLimit4;
	}

	public void setRouletteLimit4(RouletteLimit rouletteLimit4) {
		this.rouletteLimit4 = rouletteLimit4;
	}

	public int getLimitIndex() {
		return limitIndex;
	}

	public void setLimitIndex(int limitIndex) {
		this.limitIndex = limitIndex;
	}

	

	public RoulettePool getRoulettePool() {
		return roulettePool;
	}

	public void setRoulettePool(RoulettePool roulettePool) {
		this.roulettePool = roulettePool;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public RouletteBetInformation getRouletteBetInformation() {
		return rouletteBetInformation;
	}


	public String getRoadOld() {
		return roadOld;
	}

	public void setRoadOld(String roadOld) {
		this.roadOld = roadOld;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getBlack() {
		return black;
	}

	public void setBlack(int black) {
		this.black = black;
	}

	public int getZero() {
		return zero;
	}

	public void setZero(int zero) {
		this.zero = zero;
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

	public String getPoolString() {
		return poolString;
	}

	public void setPoolString(String poolString) {
		this.poolString = poolString;
	}

	public double getWonMoney() {
		return wonMoney;
	}

	public void setWonMoney(double wonMoney) {
		this.wonMoney = wonMoney;
	}

	public RouletteBetInformation getRouletteBetRepeatInformation() {
		return rouletteBetRepeatInformation;
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
}
