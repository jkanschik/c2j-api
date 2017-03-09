package c2j.api.dataStructures;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Group implements CobolRecord {

	private int size;
	private List<CobolRecord> children = new ArrayList<CobolRecord>();
	
	protected void init() {
		try {
			Class klazz = this.getClass();
			for (Field field : klazz.getDeclaredFields()) {
				for (Class interfaceClass : field.getType().getInterfaces()) {
					if (interfaceClass.equals(CobolRecord.class)) {
						field.setAccessible(true);
						CobolRecord child = (CobolRecord) field.get(this);
						children.add(child);
						size += child.getSize();
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("Group couldn't be initialised.", e);
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public byte[] getValue() {
		byte[] value = new byte[size];
		int offset = 0;
		for (CobolRecord child : children) {
			byte[] childValue = child.getValue();
			System.arraycopy(childValue, 0, value, offset, childValue.length);
			offset += childValue.length;
		}
		return value;
	}
	
	public void write(byte[] src) {
		write(src, 0);
	}
	
	public void write(byte[] src, int position) {
//		int offset = 0;
//		for (CobolRecord child : children) {
//			
//			byte[] childValue = child.getValue();
//			System.arraycopy(childValue, 0, value, offset, childValue.length);
//			offset += childValue.length;
//		}
	}
}
