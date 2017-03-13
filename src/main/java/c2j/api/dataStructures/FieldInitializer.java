package c2j.api.dataStructures;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by kanschje on 13.03.2017.
 */
public class FieldInitializer {

  public void initializeFields(Object instance) {
    try {
      Class klazz = instance.getClass();
      for (Field field : klazz.getDeclaredFields()) {
        field.setAccessible(true);
        for (Annotation annotation : field.getAnnotations()) {
        	if (annotation.annotationType() == NumericRecord.class) {
        		NumericRecord nr = (NumericRecord) annotation;
                CobolRecord child = new NumericCobolRecord(nr.pic(), nr.value());
                field.set(instance, child);
        	}
        	if (annotation.annotationType() == DisplayRecord.class) {
        		DisplayRecord dr = (DisplayRecord) annotation;
                CobolRecord child = new AlphaNumericCobolRecordImpl(dr.pic(), dr.value());
                field.set(instance, child);
        	}
        	if (annotation.annotationType() == Group.class) {
                GroupImpl value = (GroupImpl) field.getType().newInstance();
                field.set(instance, value);
                value.init();
        	}        	
        }
      }
    } catch (IllegalArgumentException | IllegalAccessException |InstantiationException e) {
      throw new RuntimeException("Fields couldn't be initialised.", e);
    }
  }

}
