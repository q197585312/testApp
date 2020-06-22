package gaming178.com.mylibrary.lib.service;


import gaming178.com.mylibrary.lib.util.AtomicIntegerUtil;

/**
 * 
 * @author Administrator
 *
 */
public class Packet {
	
	private int id= AtomicIntegerUtil.getIncrementID();
	private byte[] data;

	public String getTxt() {
		return txt;
	}

	private String txt;
	
	public int getId() {
		return id;
	}

	public void pack(String txt)
	{
		this.txt=txt+"\n";
		data=txt.getBytes();
	}
	
	public byte[] getPacket()
	{
		return data;
	}
}
