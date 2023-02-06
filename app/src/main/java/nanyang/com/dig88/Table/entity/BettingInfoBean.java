package nanyang.com.dig88.Table.entity;

/**
 * Created by Administrator on 2015/11/24.
 */
public class BettingInfoBean {
    String moduleTitle;
    String away;
    String home;
    String hdp;
    String gt;//游戏类型
    String b;
    String sc;
    String SocOddsId;
    String SocOddsId_FH;
    String Odds;
    int isFH;
    boolean isRunning=false;
    boolean isHomeGiven=false;

    public BettingInfoBean(String gt, String b, String sc,String hdp, String odds,String home,String away,   String moduleTitle,  String socOddsId, String socOddsId_FH,int isFH, boolean isRunning,boolean isHomeGiven) {
        this.away = away;
        this.b = b;
        this.Odds=odds;
        this.gt = gt;
        this.hdp = hdp;
        this.home = home;
        this.isFH = isFH;
        this.isRunning = isRunning;
        this.moduleTitle = moduleTitle;
        this.sc = sc;
        SocOddsId = socOddsId;
        SocOddsId_FH = socOddsId_FH;
        this.isHomeGiven=isHomeGiven;
    }

    public boolean isHomeGiven() {
        return isHomeGiven;
    }

    public void setHomeGiven(boolean homeGiven) {
        isHomeGiven = homeGiven;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getHdp() {
        return hdp;
    }

    public void setHdp(String hdp) {
        this.hdp = hdp;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public int getIsFH() {
        return isFH;
    }

    public void setIsFH(int isFH) {
        this.isFH = isFH;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getOdds() {
        return Odds;
    }

    public void setOdds(String odds) {
        Odds = odds;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getSocOddsId() {
        return SocOddsId;
    }

    public void setSocOddsId(String socOddsId) {
        SocOddsId = socOddsId;
    }

    public String getSocOddsId_FH() {
        return SocOddsId_FH;
    }

    public void setSocOddsId_FH(String socOddsId_FH) {
        SocOddsId_FH = socOddsId_FH;
    }
}
