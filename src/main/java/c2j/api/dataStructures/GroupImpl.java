package c2j.api.dataStructures;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class GroupImpl extends AbstractCobolRecord implements CobolRecord {

  protected void init() {
    try {
      instantiateFields();
      this.value = new byte[this.size];
      initChildValues();
    } catch (IllegalArgumentException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
      throw new RuntimeException("Group couldn't be initialised.", e);
    }
  }

  private void instantiateFields() throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchFieldException {
    Class klazz = this.getClass();

    for (Field field : klazz.getDeclaredFields()) {
      field.setAccessible(true);
      for (Annotation annotation : field.getAnnotations()) {
        if (annotation.annotationType() == NumericRecord.class) {
          NumericRecord nr = (NumericRecord) annotation;
          NumericCobolRecord child = new NumericCobolRecord(nr.pic());
          if (!nr.redefines().isEmpty()) {
            Field redefinitionReferenceField = klazz.getField(nr.redefines());
            CobolRecord redefinitionReference = (CobolRecord) redefinitionReferenceField.get(this);
            child.setRedefinitionReference(redefinitionReference);
            field.set(this, child);
          } else {
            child.setParentReference(this, size);
            field.set(this, child);
            size += child.getSize();
          }
        }
        if (annotation.annotationType() == DisplayRecord.class) {
          DisplayRecord dr = (DisplayRecord) annotation;
          AbstractCobolRecord child = new AlphaNumericCobolRecordImpl(dr.pic());
          child.setParentReference(this, size);
          field.set(this, child);
          size += child.getSize();
        }
        if (annotation.annotationType() == Group.class) {
          GroupImpl child = (GroupImpl) field.getType().newInstance();
          child.init();
          child.setParentReference(this, size);
          field.set(this, child);
          size += child.getSize();
        }
      }
    }
  }

  protected void initChildValues() throws IllegalArgumentException, IllegalAccessException {
    Class klazz = this.getClass();

    for (Field field : klazz.getDeclaredFields()) {
      for (Annotation annotation : field.getAnnotations()) {
        if (annotation.annotationType() == NumericRecord.class) {
          NumericRecord nr = (NumericRecord) annotation;
          if (nr.redefines().isEmpty()) {
            ((NumericCobolRecord) field.get(this)).set(nr.value());
          }
        }
        if (annotation.annotationType() == DisplayRecord.class) {
          DisplayRecord dr = (DisplayRecord) annotation;
          ((AlphaNumericCobolRecordImpl) field.get(this)).set(dr.value());
        }
        if (annotation.annotationType() == Group.class) {
          ((GroupImpl) field.get(this)).initChildValues();
        }
      }
    }
  }

}
