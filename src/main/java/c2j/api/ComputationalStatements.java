package c2j.api;

import c2j.api.dataStructures.AlphaNumericCobolRecord;
import c2j.api.dataStructures.CobolRecord;
import c2j.api.dataStructures.NumericCobolRecord;
import c2j.api.dataStructures.NumericValueConverter;

import java.math.BigDecimal;

/**
 * Created by kanschje on 14.03.2017.
 */
public class ComputationalStatements {

  public boolean isNumeric(CobolRecord record) {
    try {
      if (record instanceof AlphaNumericCobolRecord) {
        NumericValueConverter.fromPIC9(record.getValue(), record.getSize());
      } else if (record instanceof NumericCobolRecord) {
        ((NumericCobolRecord) record).get();
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public void add(long value, NumericCobolRecord to) {
    add(new BigDecimal(value), to);
  }

  public void add(NumericCobolRecord value, NumericCobolRecord to) {
    add(value.get(), to);
  }

  public void add(BigDecimal value, NumericCobolRecord to) {
    to.set(to.get().add(value));
  }

}
