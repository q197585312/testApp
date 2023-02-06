package gaming178.com.casinogame.Bean;

public class RoulettePool {
	private int number;// 直注
	private int split;//分注
	private int street;//街注/三数
	private int corner;//角注/四个号码
	private int line;//线注
	private int column;//列注
	private int even;//红/黑/单/双/低注/高注
	private int dozen;//打注
	private int red_black_odd_even_big_small;
	public void Init()
	{
		number = 0;
		split = 0;
		street = 0;
		corner = 0;
		line = 0;
		column = 0;
		even = 0;
		dozen = 0;
		red_black_odd_even_big_small =0;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSplit() {
		return split;
	}
	public void setSplit(int split) {
		this.split = split;
	}
	public int getStreet() {
		return street;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	public int getCorner() {
		return corner;
	}
	public void setCorner(int corner) {
		this.corner = corner;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getEven() {
		return even;
	}
	public void setEven(int even) {
		this.even = even;
	}
	public int getDozen() {
		return dozen;
	}
	public void setDozen(int dozen) {
		this.dozen = dozen;
	}

	public int getRed_black_odd_even_big_small() {
		return red_black_odd_even_big_small;
	}

	public void setRed_black_odd_even_big_small(int red_black_odd_even_big_small) {
		this.red_black_odd_even_big_small = red_black_odd_even_big_small;
	}
}
