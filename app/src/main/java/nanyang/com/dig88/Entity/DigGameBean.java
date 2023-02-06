package nanyang.com.dig88.Entity;

import java.io.Serializable;
/**
 * Created by Administrator on 2016/2/18.
 */
public class DigGameBean implements Serializable{
    //base atrr
    String game_name;
    String game_type;
    Long count_down;
    String game_number;
    boolean game_status;
    String game_result;
    String game_perid;
    String game_opentime;
    String opentime;
    int gameclose;
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
    double combination_bet_total = 0;
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
    //lanjian
    private boolean bGetResults ;

    public long getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(long openUrl) {
        this.openUrl = openUrl;
    }

    public boolean isbGetResults() {
        return bGetResults;
    }

    public void setbGetResults(boolean bGetResults) {
        this.bGetResults = bGetResults;
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

    public int getGameclose() {
        return gameclose;
    }

    public void setGameclose(int gameclose) {
        this.gameclose = gameclose;
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
