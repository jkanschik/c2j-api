package c2j.api.dataStructures;


public interface CobolRecord {

	public int getSize();

	public byte[] getValue();

	public void write(byte[] src);
	public void write(byte[] src, int offset);

	public void set(String str);

}
