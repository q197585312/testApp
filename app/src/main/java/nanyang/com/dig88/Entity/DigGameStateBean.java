package nanyang.com.dig88.Entity;

import java.io.Serializable;

/**
 * 
 *  person game state
 * @author Administrator
 *
 */
public class DigGameStateBean implements Serializable {
	private static final long serialVersionUID = 1L;
     String type2;
	 String game_name;	
	 String open_time;	
	 String open_rule;
	String period;
	String now_time;
	String touzhutime;
	String count_down;

	public String getCount_down() {
		return count_down;
	}

	public void setCount_down(String count_down) {
		this.count_down = count_down;
	}

	public String getTouzhutime() {
		return touzhutime;
	}

	public void setTouzhutime(String touzhutime) {
		this.touzhutime = touzhutime;
	}

	public String getNow_time() {
		return now_time;
	}
	public void setNow_time(String now_time) {
		this.now_time = now_time;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}
	public String getOpen_rule() {
		return open_rule;
	}
	public void setOpen_rule(String open_rule) {
		this.open_rule = open_rule;
	}

	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	@Override
	public String toString() {
		return "DigGameStateBean{" +
				"type2='" + type2 + '\'' +
				", game_name='" + game_name + '\'' +
				", open_time='" + open_time + '\'' +
				", open_rule='" + open_rule + '\'' +
				", period='" + period + '\'' +
				", now_time='" + now_time + '\'' +
				'}';
	}
}
