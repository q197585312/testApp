package nanyang.com.dig88.Entity;

import java.io.Serializable;
/**
 * Created by Administrator on 2016/2/18.
 */
public class LiveGameBean {
    //base atrr
    String game_name;
    String game_type;
    Long count_down;
    String game_number;
    boolean game_status;
    String game_result;
    String game_perid;
    String game_opentime;
    int game_close;
    String opentime;
    //bigsmall
    Double bigsmall_minbet;
    Double bigsmall_maxbet;
    Double bigsmall_total;
    String bigsmall_factor;
    String bigsmall_factor2;
    String bigsmall_factor3;
    String bigsmall_factor4;
    String bigsmall_kei;
    String bigsmall_kei2;
    String bigsmall_kei3;
    String bigsmall_kei4;
    String bigsmall_discount;
    double bigsmall_bet_total = 0;
    //oddeven
    Double oddeven_mixbet;
    Double oddeven_maxbet;
    String oddeven_factor;
    String oddeven_factor2;
    String oddeven_factor3;
    String oddeven_factor4;
    String oddeven_kei;
    String oddeven_kei2;
    String oddeven_kei3;
    String oddeven_kei4;
    String oddeven_discount;
    Double oddeven_total;
    double oddeven_bet_total = 0;
    //combination
    Double combination_mixbet;
    Double combination_maxbet;
    String combination_factor;
    String combination_factor2;
    String combination_factor3;
    String combination_factor4;
    String combination_kei;
    String combination_kei2;
    String combination_kei3;
    String combination_kei4;
    String combination_discount;
    Double combination_total;
    double combination_bet_total=0;
    //color
    Double color_minbet;
    Double color_maxbet;
    String color_factor;
    String color_factor2;
    String color_factor3;
    String color_factor4;
    String color_kei;
    String color_kei2;
    String color_kei3;
    String color_kei4;
    String color_discount;
    Double color_total;
    double color_bet_total = 0;
    //sliver golod
    Double golodsliver_maxbet;
    Double golodsliver_mixbet;
    String golodsliver_factor;
    String golodsliver_factor2;
    String golodsliver_factor3;
    String golodsliver_factor4;
    String golodsliver_kei;
    String golodsliver_kei2;
    String golodsliver_kei3;
    String golodsliver_kei4;
    String golodsliver_discount;
    Double golodsliver_total;
    double golodsliver_bet_total = 0;
    //red black
    Double redblack_maxbet;
    Double redblack_mixbet;
    String redblack_factor;
    String redblack_factor2;
    String redblack_factor3;
    String redblack_factor4;
    String redblack_kei;
    String redblack_kei2;
    String redblack_kei3;
    String redblack_kei4;
    String redblack_discount;
    Double redblack_total;
    double redblack_bet_total = 0;
    //Group
    Double group_maxbet;
    Double group_mixbet;
    String group_factor;
    String group_factor2;
    String group_factor3;
    String group_factor4;
    String group_kei;
    String group_kei2;
    String group_kei3;
    String group_kei4;
    String group_discount;
    Double group_total;
    double group_bet_total = 0;
    //JACKPOT 12
    Double jackpot12_maxbet;
    Double jackpot12_mixbet;
    String jackpot12_factor;
    String jackpot12_factor2;
    String jackpot12_factor3;
    String jackpot12_factor4;
    String jackpot12_kei;
    String jackpot12_kei2;
    String jackpot12_kei3;
    String jackpot12_kei4;
    String jackpot12_discount;
    Double jackpot12_total;
    double jackpot12_bet_total;
    //COMBINATIONES 1
    Double comu48_maxbet;
    Double comu48_mixbet;
    String comu48_factor;
    String comu48_factor2;
    String comu48_factor3;
    String comu48_factor4;
    String comu48_kei;
    String comu48_kei2;
    String comu48_kei3;
    String comu48_kei4;
    String comu48_discount;
    Double comu48_total;
    double comu48_bet_total = 0;
    //COMBINATIONES 2
    Double comu482_maxbet;
    Double comu482_mixbet;
    String comu482_factor;
    String comu482_factor2;
    String comu482_factor3;
    String comu482_factor4;
    String comu482_kei;
    String comu482_kei2;
    String comu482_kei3;
    String comu482_kei4;
    String comu482_discount;
    Double comu482_total;
    double comu482_bet_total = 0;
    //COMBINATIONES 3
    Double comu483_maxbet;
    Double comu483_mixbet;
    String comu483_factor;
    String comu483_factor2;
    String comu483_factor3;
    String comu483_factor4;
    String comu483_kei;
    String comu483_kei2;
    String comu483_kei3;
    String comu483_kei4;
    String comu483_discount;
    Double comu483_total;
    double comu483_bet_total = 0;
    //COMBINATIONES 4
    Double comu484_maxbet;
    Double comu484_mixbet;
    String comu484_factor;
    String comu484_factor2;
    String comu484_factor3;
    String comu484_factor4;
    String comu484_kei;
    String comu484_kei2;
    String comu484_kei3;
    String comu484_kei4;
    String comu484_discount;
    Double comu484_total;
    double comu484_bet_total = 0;
    //COMBINATIONES 5
    Double comu485_maxbet;
    Double comu485_mixbet;
    String comu485_factor;
    String comu485_factor2;
    String comu485_factor3;
    String comu485_factor4;
    String comu485_kei;
    String comu485_kei2;
    String comu485_kei3;
    String comu485_kei4;
    String comu485_discount;
    Double comu485_total;
    double comu485_bet_total = 0;
    //COMBINATIONES 6
    Double comu486_maxbet;
    Double comu486_mixbet;
    String comu486_factor;
    String comu486_factor2;
    String comu486_factor3;
    String comu486_factor4;
    String comu486_kei;
    String comu486_kei2;
    String comu486_kei3;
    String comu486_kei4;
    String comu486_discount;
    Double comu486_total;
    double comu486_bet_total = 0;
    //THREE FORCES
    Double threeforces_maxbet;
    Double threeforces_mixbet;
    String threeforces_factor;
    String threeforces_factor2;
    String threeforces_factor3;
    String threeforces_factor4;
    String threeforces_kei;
    String threeforces_kei2;
    String threeforces_kei3;
    String threeforces_kei4;
    String threeforces_discount;
    Double threeforces_total;
    double threeforces_bet_total = 0;
    //48 simgle number
    Double simgnum48_maxbet;
    Double simgnum48_mixbet;
    String simgnum48_factor;
    String simgnum48_factor2;
    String simgnum48_factor3;
    String simgnum48_factor4;
    String simgnum48_kei;
    String simgnum48_kei2;
    String simgnum48_kei3;
    String simgnum48_kei4;
    String simgnum48_discount;
    Double simgnum48_total;
    double simgnum48_bet_total = 0;
    //1,2,3,4,5,6(live game)
    Double comnumbr_maxbet;
    Double comnumbr_mixbet;
    String comnumbr_factor;
    String comnumbr_factor2;
    String comnumbr_factor3;
    String comnumbr_factor4;
    String comnumbr_kei;
    String comnumbr_kei2;
    String comnumbr_kei3;
    String comnumbr_kei4;
    String comnumbr_discount;
    Double comnumbr_total;
    double comnumbr_bet_total = 0;
    //PAIRS
    Double pairs_maxbet;
    Double pairs_mixbet;
    String pairs_factor;
    String pairs_factor2;
    String pairs_factor3;
    String pairs_factor4;
    String pairs_kei;
    String pairs_kei2;
    String pairs_kei3;
    String pairs_kei4;
    String pairs_discount;
    Double pairs_total;
    double pairs_bet_total = 0;
    //WAI DICES
    Double waidices_maxbet;
    Double waidices_mixbet;
    String waidices_factor;
    String waidices_factor2;
    String waidices_factor3;
    String waidices_factor4;
    String waidices_kei;
    String waidices_kei2;
    String waidices_kei3;
    String waidices_kei4;
    String waidices_discount;
    Double waidices_total;
    double waidices_bet_total = 0;
    //NINEWAYCARD
    Double ninewaycard_maxbet;
    Double ninewaycard_mixbet;
    String ninewaycard_factor;
    String ninewaycard_factor2;
    String ninewaycard_factor3;
    String ninewaycard_factor4;
    String ninewaycard_kei;
    String ninewaycard_kei2;
    String ninewaycard_kei3;
    String ninewaycard_kei4;
    String ninewaycard_discount;
    Double ninewaycard_total;
    double ninewaycard_bet_total = 0;
    //NINEWAYCARD
    Double alldice_maxbet;
    Double alldice_mixbet;
    String alldice_factor;
    String alldice_factor2;
    String alldice_factor3;
    String alldice_factor4;
    String alldice_kei;
    String alldice_kei2;
    String alldice_kei3;
    String alldice_kei4;
    String alldice_discount;
    Double alldice_total;
    double alldice_bet_total = 0;
    //number bigsmall
    String number_bigsmall_mixbet;
    String number_bigsmall_maxbet;
    String number_bigsmall_factor;
    String number_bigbet_money;
    String number_smallbet_money;
    //number oddeven
    String number_oddeven_mixbet;
    String number_oddeven_maxbet;
    String number_oddeven_factor;
    String number_oddbet_money;
    String number_evenbet_money;
    //number combination
    String number_combination_mixbet;
    String numbet_combination_maxbet;
    String number_combination_factor;
    String number_bigeven_money;
    String number_bigodd_money;
    String number_smalleven_money;
    String number_smallodd_money;
    // all number
    String all_number_mixbet;
    String all_number_maxbet;
    String all_number_factor;
    String all_numberbet_money;
    //simgle number
    Double number_mixbet;
    Double number_maxbet;
    String number_factor;
    String number_factor2;
    String number_factor3;
    String number_factor4;
    String number_kei;
    String number_kei2;
    String number_kei3;
    String number_kei4;
    String number_discount;
    Double number_total;
    double number_bet_total = 0;
    private long openUrl;

    public long getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(long openUrl) {
        this.openUrl = openUrl;
    }

    public double getBigsmall_bet_total() {
        return bigsmall_bet_total;
    }

    public void setBigsmall_bet_total(double bigsmall_bet_total) {
        this.bigsmall_bet_total = bigsmall_bet_total;
    }

    public double getOddeven_bet_total() {
        return oddeven_bet_total;
    }

    public void setOddeven_bet_total(double oddeven_bet_total) {
        this.oddeven_bet_total = oddeven_bet_total;
    }

    public double getCombination_bet_total() {
        return combination_bet_total;
    }

    public void setCombination_bet_total(double combination_bet_total) {
        this.combination_bet_total = combination_bet_total;
    }

    public double getGolodsliver_bet_total() {
        return golodsliver_bet_total;
    }

    public void setGolodsliver_bet_total(double golodsliver_bet_total) {
        this.golodsliver_bet_total = golodsliver_bet_total;
    }

    public double getRedblack_bet_total() {
        return redblack_bet_total;
    }

    public void setRedblack_bet_total(double redblack_bet_total) {
        this.redblack_bet_total = redblack_bet_total;
    }

    public double getGroup_bet_total() {
        return group_bet_total;
    }

    public void setGroup_bet_total(double group_bet_total) {
        this.group_bet_total = group_bet_total;
    }

    public double getJackpot12_bet_total() {
        return jackpot12_bet_total;
    }

    public void setJackpot12_bet_total(double jackpot12_bet_total) {
        this.jackpot12_bet_total = jackpot12_bet_total;
    }

    public double getComu48_bet_total() {
        return comu48_bet_total;
    }

    public void setComu48_bet_total(double comu48_bet_total) {
        this.comu48_bet_total = comu48_bet_total;
    }

    public double getComu482_bet_total() {
        return comu482_bet_total;
    }

    public void setComu482_bet_total(double comu482_bet_total) {
        this.comu482_bet_total = comu482_bet_total;
    }

    public double getComu483_bet_total() {
        return comu483_bet_total;
    }

    public void setComu483_bet_total(double comu483_bet_total) {
        this.comu483_bet_total = comu483_bet_total;
    }

    public double getComu484_bet_total() {
        return comu484_bet_total;
    }

    public void setComu484_bet_total(double comu484_bet_total) {
        this.comu484_bet_total = comu484_bet_total;
    }

    public double getComu485_bet_total() {
        return comu485_bet_total;
    }

    public void setComu485_bet_total(double comu485_bet_total) {
        this.comu485_bet_total = comu485_bet_total;
    }

    public double getComu486_bet_total() {
        return comu486_bet_total;
    }

    public void setComu486_bet_total(double comu486_bet_total) {
        this.comu486_bet_total = comu486_bet_total;
    }

    public double getThreeforces_bet_total() {
        return threeforces_bet_total;
    }

    public void setThreeforces_bet_total(double threeforces_bet_total) {
        this.threeforces_bet_total = threeforces_bet_total;
    }

    public double getSimgnum48_bet_total() {
        return simgnum48_bet_total;
    }

    public void setSimgnum48_bet_total(double simgnum48_bet_total) {
        this.simgnum48_bet_total = simgnum48_bet_total;
    }

    public double getComnumbr_bet_total() {
        return comnumbr_bet_total;
    }

    public void setComnumbr_bet_total(double comnumbr_bet_total) {
        this.comnumbr_bet_total = comnumbr_bet_total;
    }

    public double getPairs_bet_total() {
        return pairs_bet_total;
    }

    public void setPairs_bet_total(double pairs_bet_total) {
        this.pairs_bet_total = pairs_bet_total;
    }

    public double getWaidices_bet_total() {
        return waidices_bet_total;
    }

    public void setWaidices_bet_total(double waidices_bet_total) {
        this.waidices_bet_total = waidices_bet_total;
    }

    public double getNinewaycard_bet_total() {
        return ninewaycard_bet_total;
    }

    public void setNinewaycard_bet_total(double ninewaycard_bet_total) {
        this.ninewaycard_bet_total = ninewaycard_bet_total;
    }

    public double getAlldice_bet_total() {
        return alldice_bet_total;
    }

    public void setAlldice_bet_total(double alldice_bet_total) {
        this.alldice_bet_total = alldice_bet_total;
    }

    public double getColor_bet_total() {
        return color_bet_total;
    }

    public void setColor_bet_total(double color_bet_total) {
        this.color_bet_total = color_bet_total;
    }

    public double getNumber_bet_total() {
        return number_bet_total;
    }

    public void setNumber_bet_total(double number_bet_total) {
        this.number_bet_total = number_bet_total;
    }

    public int getGame_close() {
        return game_close;
    }

    public void setGame_close(int game_close) {
        this.game_close = game_close;
    }

    public Double getAlldice_maxbet() {
        return alldice_maxbet;
    }

    public void setAlldice_maxbet(Double alldice_maxbet) {
        this.alldice_maxbet = alldice_maxbet;
    }

    public Double getAlldice_mixbet() {
        return alldice_mixbet;
    }

    public void setAlldice_mixbet(Double alldice_mixbet) {
        this.alldice_mixbet = alldice_mixbet;
    }

    public String getAlldice_factor() {
        return alldice_factor;
    }

    public void setAlldice_factor(String alldice_factor) {
        this.alldice_factor = alldice_factor;
    }

    public String getAlldice_factor2() {
        return alldice_factor2;
    }

    public void setAlldice_factor2(String alldice_factor2) {
        this.alldice_factor2 = alldice_factor2;
    }

    public String getAlldice_factor3() {
        return alldice_factor3;
    }

    public void setAlldice_factor3(String alldice_factor3) {
        this.alldice_factor3 = alldice_factor3;
    }

    public String getAlldice_factor4() {
        return alldice_factor4;
    }

    public void setAlldice_factor4(String alldice_factor4) {
        this.alldice_factor4 = alldice_factor4;
    }

    public String getAlldice_kei2() {
        return alldice_kei2;
    }

    public void setAlldice_kei2(String alldice_kei2) {
        this.alldice_kei2 = alldice_kei2;
    }

    public String getAlldice_kei() {
        return alldice_kei;
    }

    public void setAlldice_kei(String alldice_kei) {
        this.alldice_kei = alldice_kei;
    }

    public String getAlldice_kei3() {
        return alldice_kei3;
    }

    public void setAlldice_kei3(String alldice_kei3) {
        this.alldice_kei3 = alldice_kei3;
    }

    public String getAlldice_kei4() {
        return alldice_kei4;
    }

    public void setAlldice_kei4(String alldice_kei4) {
        this.alldice_kei4 = alldice_kei4;
    }

    public String getAlldice_discount() {
        return alldice_discount;
    }

    public void setAlldice_discount(String alldice_discount) {
        this.alldice_discount = alldice_discount;
    }

    public Double getAlldice_total() {
        return alldice_total;
    }

    public void setAlldice_total(Double alldice_total) {
        this.alldice_total = alldice_total;
    }

    public Double getThreeforces_maxbet() {
        return threeforces_maxbet;
    }

    public void setThreeforces_maxbet(Double threeforces_maxbet) {
        this.threeforces_maxbet = threeforces_maxbet;
    }

    public Double getThreeforces_mixbet() {
        return threeforces_mixbet;
    }

    public void setThreeforces_mixbet(Double threeforces_mixbet) {
        this.threeforces_mixbet = threeforces_mixbet;
    }

    public String getThreeforces_factor2() {
        return threeforces_factor2;
    }

    public void setThreeforces_factor2(String threeforces_factor2) {
        this.threeforces_factor2 = threeforces_factor2;
    }

    public String getThreeforces_factor() {
        return threeforces_factor;
    }

    public void setThreeforces_factor(String threeforces_factor) {
        this.threeforces_factor = threeforces_factor;
    }

    public String getThreeforces_factor3() {
        return threeforces_factor3;
    }

    public void setThreeforces_factor3(String threeforces_factor3) {
        this.threeforces_factor3 = threeforces_factor3;
    }

    public String getThreeforces_factor4() {
        return threeforces_factor4;
    }

    public void setThreeforces_factor4(String threeforces_factor4) {
        this.threeforces_factor4 = threeforces_factor4;
    }

    public String getThreeforces_kei() {
        return threeforces_kei;
    }

    public void setThreeforces_kei(String threeforces_kei) {
        this.threeforces_kei = threeforces_kei;
    }

    public String getThreeforces_kei2() {
        return threeforces_kei2;
    }

    public void setThreeforces_kei2(String threeforces_kei2) {
        this.threeforces_kei2 = threeforces_kei2;
    }

    public String getThreeforces_kei3() {
        return threeforces_kei3;
    }

    public void setThreeforces_kei3(String threeforces_kei3) {
        this.threeforces_kei3 = threeforces_kei3;
    }

    public String getThreeforces_discount() {
        return threeforces_discount;
    }

    public void setThreeforces_discount(String threeforces_discount) {
        this.threeforces_discount = threeforces_discount;
    }

    public String getThreeforces_kei4() {
        return threeforces_kei4;
    }

    public void setThreeforces_kei4(String threeforces_kei4) {
        this.threeforces_kei4 = threeforces_kei4;
    }

    public Double getThreeforces_total() {
        return threeforces_total;
    }

    public void setThreeforces_total(Double threeforces_total) {
        this.threeforces_total = threeforces_total;
    }

    public Double getComu482_maxbet() {
        return comu482_maxbet;
    }

    public void setComu482_maxbet(Double comu482_maxbet) {
        this.comu482_maxbet = comu482_maxbet;
    }

    public Double getComu482_mixbet() {
        return comu482_mixbet;
    }

    public void setComu482_mixbet(Double comu482_mixbet) {
        this.comu482_mixbet = comu482_mixbet;
    }

    public String getComu482_factor() {
        return comu482_factor;
    }

    public void setComu482_factor(String comu482_factor) {
        this.comu482_factor = comu482_factor;
    }

    public String getComu482_factor2() {
        return comu482_factor2;
    }

    public void setComu482_factor2(String comu482_factor2) {
        this.comu482_factor2 = comu482_factor2;
    }

    public String getComu482_factor3() {
        return comu482_factor3;
    }

    public void setComu482_factor3(String comu482_factor3) {
        this.comu482_factor3 = comu482_factor3;
    }

    public String getComu482_kei() {
        return comu482_kei;
    }

    public void setComu482_kei(String comu482_kei) {
        this.comu482_kei = comu482_kei;
    }

    public String getComu482_kei2() {
        return comu482_kei2;
    }

    public void setComu482_kei2(String comu482_kei2) {
        this.comu482_kei2 = comu482_kei2;
    }

    public String getComu482_kei3() {
        return comu482_kei3;
    }

    public void setComu482_kei3(String comu482_kei3) {
        this.comu482_kei3 = comu482_kei3;
    }

    public String getComu482_discount() {
        return comu482_discount;
    }

    public void setComu482_discount(String comu482_discount) {
        this.comu482_discount = comu482_discount;
    }

    public Double getComu482_total() {
        return comu482_total;
    }

    public void setComu482_total(Double comu482_total) {
        this.comu482_total = comu482_total;
    }

    public Double getComu483_maxbet() {
        return comu483_maxbet;
    }

    public void setComu483_maxbet(Double comu483_maxbet) {
        this.comu483_maxbet = comu483_maxbet;
    }

    public Double getComu483_mixbet() {
        return comu483_mixbet;
    }

    public void setComu483_mixbet(Double comu483_mixbet) {
        this.comu483_mixbet = comu483_mixbet;
    }

    public String getComu483_factor() {
        return comu483_factor;
    }

    public void setComu483_factor(String comu483_factor) {
        this.comu483_factor = comu483_factor;
    }

    public String getComu483_factor2() {
        return comu483_factor2;
    }

    public void setComu483_factor2(String comu483_factor2) {
        this.comu483_factor2 = comu483_factor2;
    }

    public String getComu483_factor3() {
        return comu483_factor3;
    }

    public void setComu483_factor3(String comu483_factor3) {
        this.comu483_factor3 = comu483_factor3;
    }

    public String getComu483_factor4() {
        return comu483_factor4;
    }

    public void setComu483_factor4(String comu483_factor4) {
        this.comu483_factor4 = comu483_factor4;
    }

    public String getComu483_kei() {
        return comu483_kei;
    }

    public void setComu483_kei(String comu483_kei) {
        this.comu483_kei = comu483_kei;
    }

    public String getComu483_kei2() {
        return comu483_kei2;
    }

    public void setComu483_kei2(String comu483_kei2) {
        this.comu483_kei2 = comu483_kei2;
    }

    public String getComu483_kei3() {
        return comu483_kei3;
    }

    public void setComu483_kei3(String comu483_kei3) {
        this.comu483_kei3 = comu483_kei3;
    }

    public String getComu483_kei4() {
        return comu483_kei4;
    }

    public void setComu483_kei4(String comu483_kei4) {
        this.comu483_kei4 = comu483_kei4;
    }

    public String getComu483_discount() {
        return comu483_discount;
    }

    public void setComu483_discount(String comu483_discount) {
        this.comu483_discount = comu483_discount;
    }

    public Double getComu483_total() {
        return comu483_total;
    }

    public void setComu483_total(Double comu483_total) {
        this.comu483_total = comu483_total;
    }

    public Double getComu484_maxbet() {
        return comu484_maxbet;
    }

    public void setComu484_maxbet(Double comu484_maxbet) {
        this.comu484_maxbet = comu484_maxbet;
    }

    public Double getComu484_mixbet() {
        return comu484_mixbet;
    }

    public void setComu484_mixbet(Double comu484_mixbet) {
        this.comu484_mixbet = comu484_mixbet;
    }

    public String getComu484_factor() {
        return comu484_factor;
    }

    public void setComu484_factor(String comu484_factor) {
        this.comu484_factor = comu484_factor;
    }

    public String getComu484_factor2() {
        return comu484_factor2;
    }

    public void setComu484_factor2(String comu484_factor2) {
        this.comu484_factor2 = comu484_factor2;
    }

    public String getComu484_factor3() {
        return comu484_factor3;
    }

    public void setComu484_factor3(String comu484_factor3) {
        this.comu484_factor3 = comu484_factor3;
    }

    public String getComu484_factor4() {
        return comu484_factor4;
    }

    public void setComu484_factor4(String comu484_factor4) {
        this.comu484_factor4 = comu484_factor4;
    }

    public String getComu484_kei() {
        return comu484_kei;
    }

    public void setComu484_kei(String comu484_kei) {
        this.comu484_kei = comu484_kei;
    }

    public String getComu484_kei2() {
        return comu484_kei2;
    }

    public void setComu484_kei2(String comu484_kei2) {
        this.comu484_kei2 = comu484_kei2;
    }

    public String getComu484_kei4() {
        return comu484_kei4;
    }

    public void setComu484_kei4(String comu484_kei4) {
        this.comu484_kei4 = comu484_kei4;
    }

    public String getComu484_discount() {
        return comu484_discount;
    }

    public void setComu484_discount(String comu484_discount) {
        this.comu484_discount = comu484_discount;
    }

    public Double getComu484_total() {
        return comu484_total;
    }

    public void setComu484_total(Double comu484_total) {
        this.comu484_total = comu484_total;
    }

    public Double getComu485_maxbet() {
        return comu485_maxbet;
    }

    public void setComu485_maxbet(Double comu485_maxbet) {
        this.comu485_maxbet = comu485_maxbet;
    }

    public Double getComu485_mixbet() {
        return comu485_mixbet;
    }

    public void setComu485_mixbet(Double comu485_mixbet) {
        this.comu485_mixbet = comu485_mixbet;
    }

    public String getComu485_factor() {
        return comu485_factor;
    }

    public void setComu485_factor(String comu485_factor) {
        this.comu485_factor = comu485_factor;
    }

    public String getComu485_factor2() {
        return comu485_factor2;
    }

    public void setComu485_factor2(String comu485_factor2) {
        this.comu485_factor2 = comu485_factor2;
    }

    public String getComu485_factor3() {
        return comu485_factor3;
    }

    public void setComu485_factor3(String comu485_factor3) {
        this.comu485_factor3 = comu485_factor3;
    }

    public String getComu485_factor4() {
        return comu485_factor4;
    }

    public void setComu485_factor4(String comu485_factor4) {
        this.comu485_factor4 = comu485_factor4;
    }

    public String getComu485_kei() {
        return comu485_kei;
    }

    public void setComu485_kei(String comu485_kei) {
        this.comu485_kei = comu485_kei;
    }

    public String getComu485_kei2() {
        return comu485_kei2;
    }

    public void setComu485_kei2(String comu485_kei2) {
        this.comu485_kei2 = comu485_kei2;
    }

    public String getComu485_kei3() {
        return comu485_kei3;
    }

    public void setComu485_kei3(String comu485_kei3) {
        this.comu485_kei3 = comu485_kei3;
    }

    public String getComu485_kei4() {
        return comu485_kei4;
    }

    public void setComu485_kei4(String comu485_kei4) {
        this.comu485_kei4 = comu485_kei4;
    }

    public String getComu485_discount() {
        return comu485_discount;
    }

    public void setComu485_discount(String comu485_discount) {
        this.comu485_discount = comu485_discount;
    }

    public Double getComu485_total() {
        return comu485_total;
    }

    public void setComu485_total(Double comu485_total) {
        this.comu485_total = comu485_total;
    }

    public Double getComu486_maxbet() {
        return comu486_maxbet;
    }

    public void setComu486_maxbet(Double comu486_maxbet) {
        this.comu486_maxbet = comu486_maxbet;
    }

    public Double getComu486_mixbet() {
        return comu486_mixbet;
    }

    public void setComu486_mixbet(Double comu486_mixbet) {
        this.comu486_mixbet = comu486_mixbet;
    }

    public String getComu486_factor() {
        return comu486_factor;
    }

    public void setComu486_factor(String comu486_factor) {
        this.comu486_factor = comu486_factor;
    }

    public String getComu486_factor3() {
        return comu486_factor3;
    }

    public void setComu486_factor3(String comu486_factor3) {
        this.comu486_factor3 = comu486_factor3;
    }

    public String getComu486_factor4() {
        return comu486_factor4;
    }

    public void setComu486_factor4(String comu486_factor4) {
        this.comu486_factor4 = comu486_factor4;
    }

    public String getComu486_kei() {
        return comu486_kei;
    }

    public void setComu486_kei(String comu486_kei) {
        this.comu486_kei = comu486_kei;
    }

    public String getComu486_kei2() {
        return comu486_kei2;
    }

    public void setComu486_kei2(String comu486_kei2) {
        this.comu486_kei2 = comu486_kei2;
    }

    public String getComu486_kei3() {
        return comu486_kei3;
    }

    public void setComu486_kei3(String comu486_kei3) {
        this.comu486_kei3 = comu486_kei3;
    }

    public Double getComu486_total() {
        return comu486_total;
    }

    public void setComu486_total(Double comu486_total) {
        this.comu486_total = comu486_total;
    }

    public String getComu486_discount() {
        return comu486_discount;
    }

    public void setComu486_discount(String comu486_discount) {
        this.comu486_discount = comu486_discount;
    }

    public String getComu486_kei4() {
        return comu486_kei4;
    }

    public void setComu486_kei4(String comu486_kei4) {
        this.comu486_kei4 = comu486_kei4;
    }

    public String getComu486_factor2() {
        return comu486_factor2;
    }

    public void setComu486_factor2(String comu486_factor2) {
        this.comu486_factor2 = comu486_factor2;
    }

    public String getComu484_kei3() {
        return comu484_kei3;
    }

    public void setComu484_kei3(String comu484_kei3) {
        this.comu484_kei3 = comu484_kei3;
    }

    public String getComu482_kei4() {
        return comu482_kei4;
    }

    public void setComu482_kei4(String comu482_kei4) {
        this.comu482_kei4 = comu482_kei4;
    }

    public String getComu482_factor4() {
        return comu482_factor4;
    }

    public void setComu482_factor4(String comu482_factor4) {
        this.comu482_factor4 = comu482_factor4;
    }

    public Double getJackpot12_maxbet() {
        return jackpot12_maxbet;
    }

    public void setJackpot12_maxbet(Double jackpot12_maxbet) {
        this.jackpot12_maxbet = jackpot12_maxbet;
    }

    public Double getJackpot12_mixbet() {
        return jackpot12_mixbet;
    }

    public void setJackpot12_mixbet(Double jackpot12_mixbet) {
        this.jackpot12_mixbet = jackpot12_mixbet;
    }

    public String getJackpot12_factor() {
        return jackpot12_factor;
    }

    public void setJackpot12_factor(String jackpot12_factor) {
        this.jackpot12_factor = jackpot12_factor;
    }

    public String getJackpot12_factor2() {
        return jackpot12_factor2;
    }

    public void setJackpot12_factor2(String jackpot12_factor2) {
        this.jackpot12_factor2 = jackpot12_factor2;
    }

    public String getJackpot12_factor3() {
        return jackpot12_factor3;
    }

    public void setJackpot12_factor3(String jackpot12_factor3) {
        this.jackpot12_factor3 = jackpot12_factor3;
    }

    public String getJackpot12_factor4() {
        return jackpot12_factor4;
    }

    public void setJackpot12_factor4(String jackpot12_factor4) {
        this.jackpot12_factor4 = jackpot12_factor4;
    }

    public String getJackpot12_kei() {
        return jackpot12_kei;
    }

    public void setJackpot12_kei(String jackpot12_kei) {
        this.jackpot12_kei = jackpot12_kei;
    }

    public String getJackpot12_kei2() {
        return jackpot12_kei2;
    }

    public void setJackpot12_kei2(String jackpot12_kei2) {
        this.jackpot12_kei2 = jackpot12_kei2;
    }

    public String getJackpot12_kei3() {
        return jackpot12_kei3;
    }

    public void setJackpot12_kei3(String jackpot12_kei3) {
        this.jackpot12_kei3 = jackpot12_kei3;
    }

    public String getJackpot12_kei4() {
        return jackpot12_kei4;
    }

    public void setJackpot12_kei4(String jackpot12_kei4) {
        this.jackpot12_kei4 = jackpot12_kei4;
    }

    public String getJackpot12_discount() {
        return jackpot12_discount;
    }

    public void setJackpot12_discount(String jackpot12_discount) {
        this.jackpot12_discount = jackpot12_discount;
    }

    public Double getJackpot12_total() {
        return jackpot12_total;
    }

    public void setJackpot12_total(Double jackpot12_total) {
        this.jackpot12_total = jackpot12_total;
    }

    public Double getComu48_maxbet() {
        return comu48_maxbet;
    }

    public void setComu48_maxbet(Double comu48_maxbet) {
        this.comu48_maxbet = comu48_maxbet;
    }

    public Double getComu48_mixbet() {
        return comu48_mixbet;
    }

    public void setComu48_mixbet(Double comu48_mixbet) {
        this.comu48_mixbet = comu48_mixbet;
    }

    public String getComu48_factor() {
        return comu48_factor;
    }

    public void setComu48_factor(String comu48_factor) {
        this.comu48_factor = comu48_factor;
    }

    public String getComu48_factor2() {
        return comu48_factor2;
    }

    public void setComu48_factor2(String comu48_factor2) {
        this.comu48_factor2 = comu48_factor2;
    }

    public String getComu48_factor3() {
        return comu48_factor3;
    }

    public void setComu48_factor3(String comu48_factor3) {
        this.comu48_factor3 = comu48_factor3;
    }

    public String getComu48_factor4() {
        return comu48_factor4;
    }

    public void setComu48_factor4(String comu48_factor4) {
        this.comu48_factor4 = comu48_factor4;
    }

    public String getComu48_kei() {
        return comu48_kei;
    }

    public void setComu48_kei(String comu48_kei) {
        this.comu48_kei = comu48_kei;
    }

    public String getComu48_kei2() {
        return comu48_kei2;
    }

    public void setComu48_kei2(String comu48_kei2) {
        this.comu48_kei2 = comu48_kei2;
    }

    public String getComu48_kei3() {
        return comu48_kei3;
    }

    public void setComu48_kei3(String comu48_kei3) {
        this.comu48_kei3 = comu48_kei3;
    }

    public String getComu48_kei4() {
        return comu48_kei4;
    }

    public void setComu48_kei4(String comu48_kei4) {
        this.comu48_kei4 = comu48_kei4;
    }

    public String getComu48_discount() {
        return comu48_discount;
    }

    public void setComu48_discount(String comu48_discount) {
        this.comu48_discount = comu48_discount;
    }

    public Double getComu48_total() {
        return comu48_total;
    }

    public void setComu48_total(Double comu48_total) {
        this.comu48_total = comu48_total;
    }

    public Double getPairs_maxbet() {
        return pairs_maxbet;
    }

    public void setPairs_maxbet(Double pairs_maxbet) {
        this.pairs_maxbet = pairs_maxbet;
    }

    public Double getPairs_mixbet() {
        return pairs_mixbet;
    }

    public void setPairs_mixbet(Double pairs_mixbet) {
        this.pairs_mixbet = pairs_mixbet;
    }

    public String getPairs_factor() {
        return pairs_factor;
    }

    public void setPairs_factor(String pairs_factor) {
        this.pairs_factor = pairs_factor;
    }

    public String getPairs_factor2() {
        return pairs_factor2;
    }

    public void setPairs_factor2(String pairs_factor2) {
        this.pairs_factor2 = pairs_factor2;
    }

    public String getPairs_factor3() {
        return pairs_factor3;
    }

    public void setPairs_factor3(String pairs_factor3) {
        this.pairs_factor3 = pairs_factor3;
    }

    public String getPairs_factor4() {
        return pairs_factor4;
    }

    public void setPairs_factor4(String pairs_factor4) {
        this.pairs_factor4 = pairs_factor4;
    }

    public String getPairs_kei() {
        return pairs_kei;
    }

    public void setPairs_kei(String pairs_kei) {
        this.pairs_kei = pairs_kei;
    }

    public String getPairs_kei2() {
        return pairs_kei2;
    }

    public void setPairs_kei2(String pairs_kei2) {
        this.pairs_kei2 = pairs_kei2;
    }

    public String getPairs_kei3() {
        return pairs_kei3;
    }

    public void setPairs_kei3(String pairs_kei3) {
        this.pairs_kei3 = pairs_kei3;
    }

    public String getPairs_kei4() {
        return pairs_kei4;
    }

    public void setPairs_kei4(String pairs_kei4) {
        this.pairs_kei4 = pairs_kei4;
    }

    public String getPairs_discount() {
        return pairs_discount;
    }

    public void setPairs_discount(String pairs_discount) {
        this.pairs_discount = pairs_discount;
    }

    public Double getPairs_total() {
        return pairs_total;
    }

    public void setPairs_total(Double pairs_total) {
        this.pairs_total = pairs_total;
    }

    public Double getWaidices_maxbet() {
        return waidices_maxbet;
    }

    public void setWaidices_maxbet(Double waidices_maxbet) {
        this.waidices_maxbet = waidices_maxbet;
    }

    public Double getWaidices_mixbet() {
        return waidices_mixbet;
    }

    public void setWaidices_mixbet(Double waidices_mixbet) {
        this.waidices_mixbet = waidices_mixbet;
    }

    public String getWaidices_factor() {
        return waidices_factor;
    }

    public void setWaidices_factor(String waidices_factor) {
        this.waidices_factor = waidices_factor;
    }

    public String getWaidices_factor2() {
        return waidices_factor2;
    }

    public void setWaidices_factor2(String waidices_factor2) {
        this.waidices_factor2 = waidices_factor2;
    }

    public String getWaidices_factor3() {
        return waidices_factor3;
    }

    public void setWaidices_factor3(String waidices_factor3) {
        this.waidices_factor3 = waidices_factor3;
    }

    public String getWaidices_factor4() {
        return waidices_factor4;
    }

    public void setWaidices_factor4(String waidices_factor4) {
        this.waidices_factor4 = waidices_factor4;
    }

    public String getWaidices_kei() {
        return waidices_kei;
    }

    public void setWaidices_kei(String waidices_kei) {
        this.waidices_kei = waidices_kei;
    }

    public String getWaidices_kei2() {
        return waidices_kei2;
    }

    public void setWaidices_kei2(String waidices_kei2) {
        this.waidices_kei2 = waidices_kei2;
    }

    public String getWaidices_kei3() {
        return waidices_kei3;
    }

    public void setWaidices_kei3(String waidices_kei3) {
        this.waidices_kei3 = waidices_kei3;
    }

    public String getWaidices_kei4() {
        return waidices_kei4;
    }

    public void setWaidices_kei4(String waidices_kei4) {
        this.waidices_kei4 = waidices_kei4;
    }

    public String getWaidices_discount() {
        return waidices_discount;
    }

    public void setWaidices_discount(String waidices_discount) {
        this.waidices_discount = waidices_discount;
    }

    public Double getWaidices_total() {
        return waidices_total;
    }

    public void setWaidices_total(Double waidices_total) {
        this.waidices_total = waidices_total;
    }

    public Double getNinewaycard_maxbet() {
        return ninewaycard_maxbet;
    }

    public void setNinewaycard_maxbet(Double ninewaycard_maxbet) {
        this.ninewaycard_maxbet = ninewaycard_maxbet;
    }

    public Double getNinewaycard_mixbet() {
        return ninewaycard_mixbet;
    }

    public void setNinewaycard_mixbet(Double ninewaycard_mixbet) {
        this.ninewaycard_mixbet = ninewaycard_mixbet;
    }

    public String getNinewaycard_factor() {
        return ninewaycard_factor;
    }

    public void setNinewaycard_factor(String ninewaycard_factor) {
        this.ninewaycard_factor = ninewaycard_factor;
    }

    public String getNinewaycard_factor2() {
        return ninewaycard_factor2;
    }

    public void setNinewaycard_factor2(String ninewaycard_factor2) {
        this.ninewaycard_factor2 = ninewaycard_factor2;
    }

    public String getNinewaycard_factor3() {
        return ninewaycard_factor3;
    }

    public void setNinewaycard_factor3(String ninewaycard_factor3) {
        this.ninewaycard_factor3 = ninewaycard_factor3;
    }

    public String getNinewaycard_factor4() {
        return ninewaycard_factor4;
    }

    public void setNinewaycard_factor4(String ninewaycard_factor4) {
        this.ninewaycard_factor4 = ninewaycard_factor4;
    }

    public String getNinewaycard_kei() {
        return ninewaycard_kei;
    }

    public void setNinewaycard_kei(String ninewaycard_kei) {
        this.ninewaycard_kei = ninewaycard_kei;
    }

    public String getNinewaycard_kei2() {
        return ninewaycard_kei2;
    }

    public void setNinewaycard_kei2(String ninewaycard_kei2) {
        this.ninewaycard_kei2 = ninewaycard_kei2;
    }

    public String getNinewaycard_kei3() {
        return ninewaycard_kei3;
    }

    public void setNinewaycard_kei3(String ninewaycard_kei3) {
        this.ninewaycard_kei3 = ninewaycard_kei3;
    }

    public String getNinewaycard_kei4() {
        return ninewaycard_kei4;
    }

    public void setNinewaycard_kei4(String ninewaycard_kei4) {
        this.ninewaycard_kei4 = ninewaycard_kei4;
    }

    public String getNinewaycard_discount() {
        return ninewaycard_discount;
    }

    public void setNinewaycard_discount(String ninewaycard_discount) {
        this.ninewaycard_discount = ninewaycard_discount;
    }

    public Double getNinewaycard_total() {
        return ninewaycard_total;
    }

    public void setNinewaycard_total(Double ninewaycard_total) {
        this.ninewaycard_total = ninewaycard_total;
    }

    public Double getSimgnum48_maxbet() {
        return simgnum48_maxbet;
    }

    public void setSimgnum48_maxbet(Double simgnum48_maxbet) {
        this.simgnum48_maxbet = simgnum48_maxbet;
    }

    public Double getSimgnum48_mixbet() {
        return simgnum48_mixbet;
    }

    public void setSimgnum48_mixbet(Double simgnum48_mixbet) {
        this.simgnum48_mixbet = simgnum48_mixbet;
    }

    public String getSimgnum48_factor() {
        return simgnum48_factor;
    }

    public void setSimgnum48_factor(String simgnum48_factor) {
        this.simgnum48_factor = simgnum48_factor;
    }

    public Double getGroup_maxbet() {
        return group_maxbet;
    }

    public void setGroup_maxbet(Double group_maxbet) {
        this.group_maxbet = group_maxbet;
    }

    public String getSimgnum48_factor2() {
        return simgnum48_factor2;
    }

    public void setSimgnum48_factor2(String simgnum48_factor2) {
        this.simgnum48_factor2 = simgnum48_factor2;
    }

    public String getSimgnum48_factor3() {
        return simgnum48_factor3;
    }

    public void setSimgnum48_factor3(String simgnum48_factor3) {
        this.simgnum48_factor3 = simgnum48_factor3;
    }

    public String getSimgnum48_factor4() {
        return simgnum48_factor4;
    }

    public void setSimgnum48_factor4(String simgnum48_factor4) {
        this.simgnum48_factor4 = simgnum48_factor4;
    }

    public String getSimgnum48_kei() {
        return simgnum48_kei;
    }

    public void setSimgnum48_kei(String simgnum48_kei) {
        this.simgnum48_kei = simgnum48_kei;
    }

    public String getSimgnum48_kei2() {
        return simgnum48_kei2;
    }

    public void setSimgnum48_kei2(String simgnum48_kei2) {
        this.simgnum48_kei2 = simgnum48_kei2;
    }

    public String getSimgnum48_kei3() {
        return simgnum48_kei3;
    }

    public void setSimgnum48_kei3(String simgnum48_kei3) {
        this.simgnum48_kei3 = simgnum48_kei3;
    }

    public String getSimgnum48_kei4() {
        return simgnum48_kei4;
    }

    public void setSimgnum48_kei4(String simgnum48_kei4) {
        this.simgnum48_kei4 = simgnum48_kei4;
    }

    public String getSimgnum48_discount() {
        return simgnum48_discount;
    }

    public void setSimgnum48_discount(String simgnum48_discount) {
        this.simgnum48_discount = simgnum48_discount;
    }

    public Double getSimgnum48_total() {
        return simgnum48_total;
    }

    public void setSimgnum48_total(Double simgnum48_total) {
        this.simgnum48_total = simgnum48_total;
    }

    public Double getGroup_mixbet() {
        return group_mixbet;
    }

    public void setGroup_mixbet(Double group_mixbet) {
        this.group_mixbet = group_mixbet;
    }

    public String getGroup_factor() {
        return group_factor;
    }

    public void setGroup_factor(String group_factor) {
        this.group_factor = group_factor;
    }

    public String getGroup_factor2() {
        return group_factor2;
    }

    public void setGroup_factor2(String group_factor2) {
        this.group_factor2 = group_factor2;
    }

    public String getGroup_factor3() {
        return group_factor3;
    }

    public void setGroup_factor3(String group_factor3) {
        this.group_factor3 = group_factor3;
    }

    public String getGroup_factor4() {
        return group_factor4;
    }

    public void setGroup_factor4(String group_factor4) {
        this.group_factor4 = group_factor4;
    }

    public String getGroup_kei() {
        return group_kei;
    }

    public void setGroup_kei(String group_kei) {
        this.group_kei = group_kei;
    }

    public String getGroup_kei2() {
        return group_kei2;
    }

    public void setGroup_kei2(String group_kei2) {
        this.group_kei2 = group_kei2;
    }

    public String getGroup_kei3() {
        return group_kei3;
    }

    public void setGroup_kei3(String group_kei3) {
        this.group_kei3 = group_kei3;
    }

    public String getGroup_kei4() {
        return group_kei4;
    }

    public void setGroup_kei4(String group_kei4) {
        this.group_kei4 = group_kei4;
    }

    public String getGroup_discount() {
        return group_discount;
    }

    public void setGroup_discount(String group_discount) {
        this.group_discount = group_discount;
    }

    public Double getGroup_total() {
        return group_total;
    }

    public void setGroup_total(Double group_total) {
        this.group_total = group_total;
    }

    public Double getGolodsliver_maxbet() {
        return golodsliver_maxbet;
    }

    public void setGolodsliver_maxbet(Double golodsliver_maxbet) {
        this.golodsliver_maxbet = golodsliver_maxbet;
    }

    public Double getGolodsliver_mixbet() {
        return golodsliver_mixbet;
    }

    public void setGolodsliver_mixbet(Double golodsliver_mixbet) {
        this.golodsliver_mixbet = golodsliver_mixbet;
    }


    public String getGolodsliver_factor2() {
        return golodsliver_factor2;
    }

    public void setGolodsliver_factor2(String golodsliver_factor2) {
        this.golodsliver_factor2 = golodsliver_factor2;
    }

    public String getGolodsliver_factor3() {
        return golodsliver_factor3;
    }

    public void setGolodsliver_factor3(String golodsliver_factor3) {
        this.golodsliver_factor3 = golodsliver_factor3;
    }

    public String getGolodsliver_factor4() {
        return golodsliver_factor4;
    }

    public void setGolodsliver_factor4(String golodsliver_factor4) {
        this.golodsliver_factor4 = golodsliver_factor4;
    }

    public String getGolodsliver_kei() {
        return golodsliver_kei;
    }

    public void setGolodsliver_kei(String golodsliver_kei) {
        this.golodsliver_kei = golodsliver_kei;
    }

    public String getGolodsliver_kei2() {
        return golodsliver_kei2;
    }

    public void setGolodsliver_kei2(String golodsliver_kei2) {
        this.golodsliver_kei2 = golodsliver_kei2;
    }

    public String getGolodsliver_kei3() {
        return golodsliver_kei3;
    }

    public void setGolodsliver_kei3(String golodsliver_kei3) {
        this.golodsliver_kei3 = golodsliver_kei3;
    }

    public String getGolodsliver_kei4() {
        return golodsliver_kei4;
    }

    public void setGolodsliver_kei4(String golodsliver_kei4) {
        this.golodsliver_kei4 = golodsliver_kei4;
    }

    public String getGolodsliver_discount() {
        return golodsliver_discount;
    }

    public void setGolodsliver_discount(String golodsliver_discount) {
        this.golodsliver_discount = golodsliver_discount;
    }

    public Double getGolodsliver_total() {
        return golodsliver_total;
    }

    public void setGolodsliver_total(Double golodsliver_total) {
        this.golodsliver_total = golodsliver_total;
    }

    public Double getRedblack_maxbet() {
        return redblack_maxbet;
    }

    public void setRedblack_maxbet(Double redblack_maxbet) {
        this.redblack_maxbet = redblack_maxbet;
    }

    public Double getRedblack_mixbet() {
        return redblack_mixbet;
    }

    public void setRedblack_mixbet(Double redblack_mixbet) {
        this.redblack_mixbet = redblack_mixbet;
    }


    public String getRedblack_factor2() {
        return redblack_factor2;
    }

    public void setRedblack_factor2(String redblack_factor2) {
        this.redblack_factor2 = redblack_factor2;
    }

    public String getRedblack_factor3() {
        return redblack_factor3;
    }

    public void setRedblack_factor3(String redblack_factor3) {
        this.redblack_factor3 = redblack_factor3;
    }

    public String getRedblack_factor4() {
        return redblack_factor4;
    }

    public void setRedblack_factor4(String redblack_factor4) {
        this.redblack_factor4 = redblack_factor4;
    }

    public String getRedblack_kei() {
        return redblack_kei;
    }

    public void setRedblack_kei(String redblack_kei) {
        this.redblack_kei = redblack_kei;
    }

    public String getRedblack_kei2() {
        return redblack_kei2;
    }

    public void setRedblack_kei2(String redblack_kei2) {
        this.redblack_kei2 = redblack_kei2;
    }

    public String getRedblack_kei3() {
        return redblack_kei3;
    }

    public void setRedblack_kei3(String redblack_kei3) {
        this.redblack_kei3 = redblack_kei3;
    }

    public String getRedblack_kei4() {
        return redblack_kei4;
    }

    public void setRedblack_kei4(String redblack_kei4) {
        this.redblack_kei4 = redblack_kei4;
    }

    public String getRedblack_discount() {
        return redblack_discount;
    }

    public void setRedblack_discount(String redblack_discount) {
        this.redblack_discount = redblack_discount;
    }

    public Double getRedblack_total() {
        return redblack_total;
    }

    public void setRedblack_total(Double redblack_total) {
        this.redblack_total = redblack_total;
    }

    public Double getComnumbr_maxbet() {
        return comnumbr_maxbet;
    }

    public void setComnumbr_maxbet(Double comnumbr_maxbet) {
        this.comnumbr_maxbet = comnumbr_maxbet;
    }

    public Double getComnumbr_mixbet() {
        return comnumbr_mixbet;
    }

    public void setComnumbr_mixbet(Double comnumbr_mixbet) {
        this.comnumbr_mixbet = comnumbr_mixbet;
    }

    public String getGolodsliver_factor() {
        return golodsliver_factor;
    }

    public void setGolodsliver_factor(String golodsliver_factor) {
        this.golodsliver_factor = golodsliver_factor;
    }

    public String getRedblack_factor() {
        return redblack_factor;
    }

    public void setRedblack_factor(String redblack_factor) {
        this.redblack_factor = redblack_factor;
    }

    public String getComnumbr_factor() {
        return comnumbr_factor;
    }

    public void setComnumbr_factor(String comnumbr_factor) {
        this.comnumbr_factor = comnumbr_factor;
    }

    public String getComnumbr_factor2() {
        return comnumbr_factor2;
    }

    public void setComnumbr_factor2(String comnumbr_factor2) {
        this.comnumbr_factor2 = comnumbr_factor2;
    }

    public String getComnumbr_factor3() {
        return comnumbr_factor3;
    }

    public void setComnumbr_factor3(String comnumbr_factor3) {
        this.comnumbr_factor3 = comnumbr_factor3;
    }

    public String getComnumbr_factor4() {
        return comnumbr_factor4;
    }

    public void setComnumbr_factor4(String comnumbr_factor4) {
        this.comnumbr_factor4 = comnumbr_factor4;
    }

    public String getComnumbr_kei() {
        return comnumbr_kei;
    }

    public void setComnumbr_kei(String comnumbr_kei) {
        this.comnumbr_kei = comnumbr_kei;
    }

    public String getComnumbr_kei2() {
        return comnumbr_kei2;
    }

    public void setComnumbr_kei2(String comnumbr_kei2) {
        this.comnumbr_kei2 = comnumbr_kei2;
    }

    public String getComnumbr_kei3() {
        return comnumbr_kei3;
    }

    public void setComnumbr_kei3(String comnumbr_kei3) {
        this.comnumbr_kei3 = comnumbr_kei3;
    }

    public String getComnumbr_kei4() {
        return comnumbr_kei4;
    }

    public void setComnumbr_kei4(String comnumbr_kei4) {
        this.comnumbr_kei4 = comnumbr_kei4;
    }

    public String getComnumbr_discount() {
        return comnumbr_discount;
    }

    public void setComnumbr_discount(String comnumbr_discount) {
        this.comnumbr_discount = comnumbr_discount;
    }

    public Double getComnumbr_total() {
        return comnumbr_total;
    }

    public void setComnumbr_total(Double comnumbr_total) {
        this.comnumbr_total = comnumbr_total;
    }

    public Long getCount_down() {
        return count_down;
    }

    public void setCount_down(Long count_down) {
        this.count_down = count_down;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getGame_perid() {
        return game_perid;
    }

    public void setGame_perid(String game_perid) {
        this.game_perid = game_perid;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getGame_number() {
        return game_number;
    }

    public void setGame_number(String game_number) {
        this.game_number = game_number;
    }

    public boolean isGame_status() {
        return game_status;
    }

    public void setGame_status(boolean game_status) {
        this.game_status = game_status;
    }

    public String getGame_result() {
        return game_result;
    }

    public void setGame_result(String game_result) {
        this.game_result = game_result;
    }



    public String getBigsmall_factor() {
        return bigsmall_factor;
    }

    public void setBigsmall_factor(String bigsmall_factor) {
        this.bigsmall_factor = bigsmall_factor;
    }






    public String getOddeven_factor() {
        return oddeven_factor;
    }

    public void setOddeven_factor(String oddeven_factor) {
        this.oddeven_factor = oddeven_factor;
    }





    public String getCombination_factor() {
        return combination_factor;
    }

    public void setCombination_factor(String combination_factor) {
        this.combination_factor = combination_factor;
    }





    public String getColor_factor() {
        return color_factor;
    }

    public void setColor_factor(String color_factor) {
        this.color_factor = color_factor;
    }



    public String getNumber_bigsmall_mixbet() {
        return number_bigsmall_mixbet;
    }

    public void setNumber_bigsmall_mixbet(String number_bigsmall_mixbet) {
        this.number_bigsmall_mixbet = number_bigsmall_mixbet;
    }

    public String getNumber_bigsmall_maxbet() {
        return number_bigsmall_maxbet;
    }

    public void setNumber_bigsmall_maxbet(String number_bigsmall_maxbet) {
        this.number_bigsmall_maxbet = number_bigsmall_maxbet;
    }

    public String getNumber_bigsmall_factor() {
        return number_bigsmall_factor;
    }

    public void setNumber_bigsmall_factor(String number_bigsmall_factor) {
        this.number_bigsmall_factor = number_bigsmall_factor;
    }

    public String getNumber_bigbet_money() {
        return number_bigbet_money;
    }

    public void setNumber_bigbet_money(String number_bigbet_money) {
        this.number_bigbet_money = number_bigbet_money;
    }

    public String getNumber_smallbet_money() {
        return number_smallbet_money;
    }

    public void setNumber_smallbet_money(String number_smallbet_money) {
        this.number_smallbet_money = number_smallbet_money;
    }

    public String getNumber_oddeven_mixbet() {
        return number_oddeven_mixbet;
    }

    public void setNumber_oddeven_mixbet(String number_oddeven_mixbet) {
        this.number_oddeven_mixbet = number_oddeven_mixbet;
    }

    public String getNumber_oddeven_maxbet() {
        return number_oddeven_maxbet;
    }

    public void setNumber_oddeven_maxbet(String number_oddeven_maxbet) {
        this.number_oddeven_maxbet = number_oddeven_maxbet;
    }

    public String getNumber_oddeven_factor() {
        return number_oddeven_factor;
    }

    public void setNumber_oddeven_factor(String number_oddeven_factor) {
        this.number_oddeven_factor = number_oddeven_factor;
    }

    public String getNumber_oddbet_money() {
        return number_oddbet_money;
    }

    public void setNumber_oddbet_money(String number_oddbet_money) {
        this.number_oddbet_money = number_oddbet_money;
    }

    public String getNumber_evenbet_money() {
        return number_evenbet_money;
    }

    public void setNumber_evenbet_money(String number_evenbet_money) {
        this.number_evenbet_money = number_evenbet_money;
    }

    public String getNumber_combination_mixbet() {
        return number_combination_mixbet;
    }

    public void setNumber_combination_mixbet(String number_combination_mixbet) {
        this.number_combination_mixbet = number_combination_mixbet;
    }

    public String getNumbet_combination_maxbet() {
        return numbet_combination_maxbet;
    }

    public void setNumbet_combination_maxbet(String numbet_combination_maxbet) {
        this.numbet_combination_maxbet = numbet_combination_maxbet;
    }

    public String getNumber_combination_factor() {
        return number_combination_factor;
    }

    public void setNumber_combination_factor(String number_combination_factor) {
        this.number_combination_factor = number_combination_factor;
    }

    public String getNumber_bigeven_money() {
        return number_bigeven_money;
    }

    public void setNumber_bigeven_money(String number_bigeven_money) {
        this.number_bigeven_money = number_bigeven_money;
    }

    public String getNumber_bigodd_money() {
        return number_bigodd_money;
    }

    public void setNumber_bigodd_money(String number_bigodd_money) {
        this.number_bigodd_money = number_bigodd_money;
    }

    public String getNumber_smalleven_money() {
        return number_smalleven_money;
    }

    public void setNumber_smalleven_money(String number_smalleven_money) {
        this.number_smalleven_money = number_smalleven_money;
    }

    public String getNumber_smallodd_money() {
        return number_smallodd_money;
    }

    public void setNumber_smallodd_money(String number_smallodd_money) {
        this.number_smallodd_money = number_smallodd_money;
    }

    public String getAll_number_mixbet() {
        return all_number_mixbet;
    }

    public void setAll_number_mixbet(String all_number_mixbet) {
        this.all_number_mixbet = all_number_mixbet;
    }

    public String getAll_number_maxbet() {
        return all_number_maxbet;
    }

    public void setAll_number_maxbet(String all_number_maxbet) {
        this.all_number_maxbet = all_number_maxbet;
    }

    public String getAll_number_factor() {
        return all_number_factor;
    }

    public void setAll_number_factor(String all_number_factor) {
        this.all_number_factor = all_number_factor;
    }

    public String getAll_numberbet_money() {
        return all_numberbet_money;
    }

    public void setAll_numberbet_money(String all_numberbet_money) {
        this.all_numberbet_money = all_numberbet_money;
    }


    public String getNumber_factor() {
        return number_factor;
    }

    public void setNumber_factor(String number_factor) {
        this.number_factor = number_factor;
    }



    public String getBigsmall_factor2() {
        return bigsmall_factor2;
    }

    public void setBigsmall_factor2(String bigsmall_factor2) {
        this.bigsmall_factor2 = bigsmall_factor2;
    }

    public String getBigsmall_factor3() {
        return bigsmall_factor3;
    }

    public void setBigsmall_factor3(String bigsmall_factor3) {
        this.bigsmall_factor3 = bigsmall_factor3;
    }

    public String getBigsmall_factor4() {
        return bigsmall_factor4;
    }

    public void setBigsmall_factor4(String bigsmall_factor4) {
        this.bigsmall_factor4 = bigsmall_factor4;
    }

    public String getBigsmall_kei() {
        return bigsmall_kei;
    }

    public void setBigsmall_kei(String bigsmall_kei) {
        this.bigsmall_kei = bigsmall_kei;
    }

    public String getBigsmall_kei2() {
        return bigsmall_kei2;
    }

    public void setBigsmall_kei2(String bigsmall_kei2) {
        this.bigsmall_kei2 = bigsmall_kei2;
    }

    public String getBigsmall_kei3() {
        return bigsmall_kei3;
    }

    public void setBigsmall_kei3(String bigsmall_kei3) {
        this.bigsmall_kei3 = bigsmall_kei3;
    }

    public String getBigsmall_kei4() {
        return bigsmall_kei4;
    }

    public void setBigsmall_kei4(String bigsmall_kei4) {
        this.bigsmall_kei4 = bigsmall_kei4;
    }

    public String getBigsmall_discount() {
        return bigsmall_discount;
    }

    public void setBigsmall_discount(String bigsmall_discount) {
        this.bigsmall_discount = bigsmall_discount;
    }

    public String getOddeven_factor2() {
        return oddeven_factor2;
    }

    public void setOddeven_factor2(String oddeven_factor2) {
        this.oddeven_factor2 = oddeven_factor2;
    }

    public String getOddeven_factor3() {
        return oddeven_factor3;
    }

    public void setOddeven_factor3(String oddeven_factor3) {
        this.oddeven_factor3 = oddeven_factor3;
    }

    public String getOddeven_factor4() {
        return oddeven_factor4;
    }

    public void setOddeven_factor4(String oddeven_factor4) {
        this.oddeven_factor4 = oddeven_factor4;
    }

    public String getOddeven_kei() {
        return oddeven_kei;
    }

    public void setOddeven_kei(String oddeven_kei) {
        this.oddeven_kei = oddeven_kei;
    }

    public String getOddeven_kei2() {
        return oddeven_kei2;
    }

    public void setOddeven_kei2(String oddeven_kei2) {
        this.oddeven_kei2 = oddeven_kei2;
    }

    public String getOddeven_kei3() {
        return oddeven_kei3;
    }

    public void setOddeven_kei3(String oddeven_kei3) {
        this.oddeven_kei3 = oddeven_kei3;
    }

    public String getOddeven_kei4() {
        return oddeven_kei4;
    }

    public void setOddeven_kei4(String oddeven_kei4) {
        this.oddeven_kei4 = oddeven_kei4;
    }

    public String getOddeven_discount() {
        return oddeven_discount;
    }

    public void setOddeven_discount(String oddeven_discount) {
        this.oddeven_discount = oddeven_discount;
    }


    public String getCombination_factor2() {
        return combination_factor2;
    }

    public void setCombination_factor2(String combination_factor2) {
        this.combination_factor2 = combination_factor2;
    }

    public String getCombination_factor3() {
        return combination_factor3;
    }

    public void setCombination_factor3(String combination_factor3) {
        this.combination_factor3 = combination_factor3;
    }

    public String getCombination_factor4() {
        return combination_factor4;
    }

    public void setCombination_factor4(String combination_factor4) {
        this.combination_factor4 = combination_factor4;
    }

    public String getCombination_kei() {
        return combination_kei;
    }

    public void setCombination_kei(String combination_kei) {
        this.combination_kei = combination_kei;
    }

    public String getCombination_kei2() {
        return combination_kei2;
    }

    public void setCombination_kei2(String combination_kei2) {
        this.combination_kei2 = combination_kei2;
    }

    public String getCombination_kei3() {
        return combination_kei3;
    }

    public void setCombination_kei3(String combination_kei3) {
        this.combination_kei3 = combination_kei3;
    }

    public String getCombination_kei4() {
        return combination_kei4;
    }

    public void setCombination_kei4(String combination_kei4) {
        this.combination_kei4 = combination_kei4;
    }

    public String getCombination_discount() {
        return combination_discount;
    }

    public void setCombination_discount(String combination_discount) {
        this.combination_discount = combination_discount;
    }


    public String getColor_factor2() {
        return color_factor2;
    }

    public void setColor_factor2(String color_factor2) {
        this.color_factor2 = color_factor2;
    }

    public String getColor_factor3() {
        return color_factor3;
    }

    public void setColor_factor3(String color_factor3) {
        this.color_factor3 = color_factor3;
    }

    public String getColor_factor4() {
        return color_factor4;
    }

    public void setColor_factor4(String color_factor4) {
        this.color_factor4 = color_factor4;
    }

    public String getColor_kei() {
        return color_kei;
    }

    public void setColor_kei(String color_kei) {
        this.color_kei = color_kei;
    }

    public String getColor_kei2() {
        return color_kei2;
    }

    public void setColor_kei2(String color_kei2) {
        this.color_kei2 = color_kei2;
    }

    public String getColor_kei3() {
        return color_kei3;
    }

    public void setColor_kei3(String color_kei3) {
        this.color_kei3 = color_kei3;
    }

    public String getColor_kei4() {
        return color_kei4;
    }

    public void setColor_kei4(String color_kei4) {
        this.color_kei4 = color_kei4;
    }

    public String getColor_discount() {
        return color_discount;
    }

    public void setColor_discount(String color_discount) {
        this.color_discount = color_discount;
    }



    public String getNumber_factor2() {
        return number_factor2;
    }

    public void setNumber_factor2(String number_factor2) {
        this.number_factor2 = number_factor2;
    }

    public String getNumber_factor3() {
        return number_factor3;
    }

    public void setNumber_factor3(String number_factor3) {
        this.number_factor3 = number_factor3;
    }

    public String getNumber_factor4() {
        return number_factor4;
    }

    public void setNumber_factor4(String number_factor4) {
        this.number_factor4 = number_factor4;
    }

    public String getNumber_kei() {
        return number_kei;
    }

    public void setNumber_kei(String number_kei) {
        this.number_kei = number_kei;
    }

    public String getNumber_kei2() {
        return number_kei2;
    }

    public void setNumber_kei2(String number_kei2) {
        this.number_kei2 = number_kei2;
    }

    public String getNumber_kei3() {
        return number_kei3;
    }

    public void setNumber_kei3(String number_kei3) {
        this.number_kei3 = number_kei3;
    }

    public String getNumber_kei4() {
        return number_kei4;
    }

    public void setNumber_kei4(String number_kei4) {
        this.number_kei4 = number_kei4;
    }

    public String getNumber_discount() {
        return number_discount;
    }

    public void setNumber_discount(String number_discount) {
        this.number_discount = number_discount;
    }


    public String getGame_opentime() {
        return game_opentime;
    }

    public void setGame_opentime(String game_opentime) {
        this.game_opentime = game_opentime;
    }

    public Double getBigsmall_minbet() {
        return bigsmall_minbet;
    }

    public void setBigsmall_minbet(Double bigsmall_minbet) {
        this.bigsmall_minbet = bigsmall_minbet;
    }

    public Double getBigsmall_maxbet() {
        return bigsmall_maxbet;
    }

    public void setBigsmall_maxbet(Double bigsmall_maxbet) {
        this.bigsmall_maxbet = bigsmall_maxbet;
    }

    public Double getBigsmall_total() {
        return bigsmall_total;
    }

    public void setBigsmall_total(Double bigsmall_total) {
        this.bigsmall_total = bigsmall_total;
    }

    public Double getOddeven_mixbet() {
        return oddeven_mixbet;
    }

    public void setOddeven_mixbet(Double oddeven_mixbet) {
        this.oddeven_mixbet = oddeven_mixbet;
    }

    public Double getOddeven_maxbet() {
        return oddeven_maxbet;
    }

    public void setOddeven_maxbet(Double oddeven_maxbet) {
        this.oddeven_maxbet = oddeven_maxbet;
    }

    public Double getOddeven_total() {
        return oddeven_total;
    }

    public void setOddeven_total(Double oddeven_total) {
        this.oddeven_total = oddeven_total;
    }

    public Double getCombination_mixbet() {
        return combination_mixbet;
    }

    public void setCombination_mixbet(Double combination_mixbet) {
        this.combination_mixbet = combination_mixbet;
    }

    public Double getCombination_maxbet() {
        return combination_maxbet;
    }

    public void setCombination_maxbet(Double combination_maxbet) {
        this.combination_maxbet = combination_maxbet;
    }

    public Double getCombination_total() {
        return combination_total;
    }

    public void setCombination_total(Double combination_total) {
        this.combination_total = combination_total;
    }

    public Double getColor_minbet() {
        return color_minbet;
    }

    public void setColor_minbet(Double color_minbet) {
        this.color_minbet = color_minbet;
    }

    public Double getColor_maxbet() {
        return color_maxbet;
    }

    public void setColor_maxbet(Double color_maxbet) {
        this.color_maxbet = color_maxbet;
    }

    public Double getColor_total() {
        return color_total;
    }

    public void setColor_total(Double color_total) {
        this.color_total = color_total;
    }

    public Double getNumber_mixbet() {
        return number_mixbet;
    }

    public void setNumber_mixbet(Double number_mixbet) {
        this.number_mixbet = number_mixbet;
    }

    public Double getNumber_maxbet() {
        return number_maxbet;
    }

    public void setNumber_maxbet(Double number_maxbet) {
        this.number_maxbet = number_maxbet;
    }

    public Double getNumber_total() {
        return number_total;
    }

    public void setNumber_total(Double number_total) {
        this.number_total = number_total;
    }

    @Override
    public String toString() {
        return "DigGameBean{" +
                "game_name='" + game_name + '\'' +
                ", game_type='" + game_type + '\'' +
                ", count_down='" + count_down + '\'' +
                ", game_number='" + game_number + '\'' +
                ", game_status='" + game_status + '\'' +
                ", game_result='" + game_result + '\'' +
                ", bigsmall_minbet='" + bigsmall_minbet + '\'' +
                ", bigsmall_maxbet='" + bigsmall_maxbet + '\'' +
                ", bigsmall_total='" + bigsmall_total + '\'' +
                ", bigsmall_factor='" + bigsmall_factor + '\'' +
                ", bigsmall_factor2='" + bigsmall_factor2 + '\'' +
                ", bigsmall_factor3='" + bigsmall_factor3 + '\'' +
                ", bigsmall_factor4='" + bigsmall_factor4 + '\'' +
                ", bigsmall_kei='" + bigsmall_kei + '\'' +
                ", bigsmall_kei2='" + bigsmall_kei2 + '\'' +
                ", bigsmall_kei3='" + bigsmall_kei3 + '\'' +
                ", bigsmall_kei4='" + bigsmall_kei4 + '\'' +
                ", bigsmall_discount='" + bigsmall_discount + '\'' +

                ", oddeven_mixbet='" + oddeven_mixbet + '\'' +
                ", oddeven_maxbet='" + oddeven_maxbet + '\'' +
                ", oddeven_factor='" + oddeven_factor + '\'' +

                ", oddeven_factor2='" + oddeven_factor2 + '\'' +
                ", oddeven_factor3='" + oddeven_factor3 + '\'' +
                ", oddeven_factor4='" + oddeven_factor4 + '\'' +
                ", oddeven_kei='" + oddeven_kei + '\'' +
                ", oddeven_kei2='" + oddeven_kei2 + '\'' +
                ", oddeven_kei3='" + oddeven_kei3 + '\'' +
                ", oddeven_kei4='" + oddeven_kei4 + '\'' +
                ", oddeven_discount='" + oddeven_discount + '\'' +
                ", oddeven_total='" + oddeven_total + '\'' +
                ", combination_mixbet='" + combination_mixbet + '\'' +
                ", combination_maxbet='" + combination_maxbet + '\'' +
                ", combination_factor='" + combination_factor + '\'' +

                ", combination_factor2='" + combination_factor2 + '\'' +
                ", combination_factor3='" + combination_factor3 + '\'' +
                ", combination_factor4='" + combination_factor4 + '\'' +
                ", combination_kei='" + combination_kei + '\'' +
                ", combination_kei2='" + combination_kei2 + '\'' +
                ", combination_kei3='" + combination_kei3 + '\'' +
                ", combination_kei4='" + combination_kei4 + '\'' +
                ", combination_discount='" + combination_discount + '\'' +
                ", combination_total='" + combination_total + '\'' +
                ", color_minbet='" + color_minbet + '\'' +
                ", color_maxbet='" + color_maxbet + '\'' +
                ", color_factor='" + color_factor + '\'' +

                ", color_factor2='" + color_factor2 + '\'' +
                ", color_factor3='" + color_factor3 + '\'' +
                ", color_factor4='" + color_factor4 + '\'' +
                ", color_kei='" + color_kei + '\'' +
                ", color_kei2='" + color_kei2 + '\'' +
                ", color_kei3='" + color_kei3 + '\'' +
                ", color_kei4='" + color_kei4 + '\'' +
                ", color_discount='" + color_discount + '\'' +
                ", color_total='" + color_total + '\'' +
                ", number_bigsmall_mixbet='" + number_bigsmall_mixbet + '\'' +
                ", number_bigsmall_maxbet='" + number_bigsmall_maxbet + '\'' +
                ", number_bigsmall_factor='" + number_bigsmall_factor + '\'' +
                ", number_bigbet_money='" + number_bigbet_money + '\'' +
                ", number_smallbet_money='" + number_smallbet_money + '\'' +
                ", number_oddeven_mixbet='" + number_oddeven_mixbet + '\'' +
                ", number_oddeven_maxbet='" + number_oddeven_maxbet + '\'' +
                ", number_oddeven_factor='" + number_oddeven_factor + '\'' +
                ", number_oddbet_money='" + number_oddbet_money + '\'' +
                ", number_evenbet_money='" + number_evenbet_money + '\'' +
                ", number_combination_mixbet='" + number_combination_mixbet + '\'' +
                ", numbet_combination_maxbet='" + numbet_combination_maxbet + '\'' +
                ", number_combination_factor='" + number_combination_factor + '\'' +
                ", number_bigeven_money='" + number_bigeven_money + '\'' +
                ", number_bigodd_money='" + number_bigodd_money + '\'' +
                ", number_smalleven_money='" + number_smalleven_money + '\'' +
                ", number_smallodd_money='" + number_smallodd_money + '\'' +
                ", all_number_mixbet='" + all_number_mixbet + '\'' +
                ", all_number_maxbet='" + all_number_maxbet + '\'' +
                ", all_number_factor='" + all_number_factor + '\'' +
                ", all_numberbet_money='" + all_numberbet_money + '\'' +
                ", number_mixbet='" + number_mixbet + '\'' +
                ", number_maxbet='" + number_maxbet + '\'' +
                ", number_factor='" + number_factor + '\'' +
                ", number_factor2='" + number_factor2 + '\'' +
                ", number_factor3='" + number_factor3 + '\'' +
                ", number_factor4='" + number_factor4 + '\'' +
                ", number_kei='" + number_kei + '\'' +
                ", number_kei2='" + number_kei2 + '\'' +
                ", number_kei3='" + number_kei3 + '\'' +
                ", number_kei4='" + number_kei4 + '\'' +
                ", number_discount='" + number_discount + '\'' +
                ", number_total='" + number_total + '\'' +

                '}';
    }
}
