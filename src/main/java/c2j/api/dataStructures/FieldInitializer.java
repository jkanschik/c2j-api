package c2j.api.dataStructures;

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
        for (DisplayRecord dr : field.getAnnotationsByType(DisplayRecord.class)) {
          CobolRecord child = new AlphaNumericCobolRecordImpl(dr.pic(), dr.value());
          field.set(instance, child);
        }
        for (Group group : field.getAnnotationsByType(Group.class)) {
          GroupImpl value = (GroupImpl) field.getType().newInstance();
          field.set(instance, value);
          value.init();
        }
      }
    } catch (IllegalArgumentException | IllegalAccessException |InstantiationException e) {
      throw new RuntimeException("Fields couldn't be initialised.", e);
    }
  }

}
