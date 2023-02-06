package gaming178.com.casinogame.Bean;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String realName;
	private String password;
	private double balance;
	private String videoUrl;
	private String videoUrlDefault;
	private String videoPoker;
	private double exchangeRate;//汇率
	private int status;//会员状态，1正常，2暂停，3关闭
	private String areaNumber;//区位号
	private String seatNumber;//桌位号
	private String currency;//币别
	private String site;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {

		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getVideoUrlDefault() {
		return videoUrlDefault;
	}
	public void setVideoUrlDefault(String videoUrlDefault) {
		this.videoUrlDefault = videoUrlDefault;
	}
	public String getVideoPoker() {
		return videoPoker;
	}
	public void setVideoPoker(String videoPoker) {
		this.videoPoker = videoPoker;
	}
	public double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAreaNumber() {
		return areaNumber;
	}
	public void setAreaNumber(String areaNumber) {
		this.areaNumber = areaNumber;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
}
