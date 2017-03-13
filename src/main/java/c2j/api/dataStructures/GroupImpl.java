package c2j.api.dataStructures;

import java.lang.reflect.Field;

public class GroupImpl extends AbstractCobolRecord implements CobolRecord {

	protected void init() {
		try {
			instantiateFields();
			this.value = new byte[this.size];
			initChildValues();
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			throw new RuntimeException("Group couldn't be initialised.", e);
		}
	}
	
	private void instantiateFields() throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		Class klazz = this.getClass();
		
		for (Field field : klazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(DisplayRecord.class)) {
				DisplayRecord dr = field.getAnnotationsByType(DisplayRecord.class)[0];
				AbstractCobolRecord child = new AlphaNumericCobolRecordImpl(dr.pic());
				child.setParentReference(this, size);
				field.set(this, child);
				size += child.getSize();
			}
			if (field.isAnnotationPresent(Group.class)) {
				GroupImpl child = (GroupImpl) field.getType().newInstance();
				child.setParentReference(this, size);
				child.init();
		        field.set(this, child);
				size += child.getSize();
			}
		}
	}
	
	protected void initChildValues() throws IllegalArgumentException, IllegalAccessException {
		Class klazz = this.getClass();

		for (Field field : klazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(DisplayRecord.class)) {
				DisplayRecord dr = field.getAnnotationsByType(DisplayRecord.class)[0];
				((AlphaNumericCobolRecordImpl) field.get(this)).setValue(dr.value());
			}
			if (field.isAnnotationPresent(Group.class)) {
				((GroupImpl) field.get(this)).initChildValues();
			}
		}
	}

}
