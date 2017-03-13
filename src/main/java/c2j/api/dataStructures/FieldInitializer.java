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
          Object value = field.getType().newInstance();
          initializeGroup((GroupImpl) value);
          field.set(instance, value);
        }
      }
    } catch (IllegalArgumentException | IllegalAccessException |InstantiationException e) {
      throw new RuntimeException("Fields couldn't be initialised.", e);
    }
  }

  public void initializeGroup(GroupImpl group) {
    int parentOffset = 0;
    try {
      Class klazz = group.getClass();
      // Determine size of group to allocate the correct memory amount:



      for (Field field : klazz.getDeclaredFields()) {
        field.setAccessible(true);
        for (DisplayRecord dr : field.getAnnotationsByType(DisplayRecord.class)) {
          CobolRecord child = new AlphaNumericCobolRecordImpl(dr.pic(), dr.value(), group, parentOffset);
          parentOffset += child.getSize();
          field.set(group, child);
        }
        for (Group subGroup : field.getAnnotationsByType(Group.class)) {
          Object value = field.getType().getClass().newInstance();
          initializeGroup((GroupImpl) value);
          field.set(group, value);
        }
      }
    } catch (IllegalArgumentException | IllegalAccessException |InstantiationException e) {
      throw new RuntimeException("Fields couldn't be initialised.", e);
    }
  }

  private int getSizeOfGroup(GroupImpl group) {
    // TODO
    return 0;
  }

}
