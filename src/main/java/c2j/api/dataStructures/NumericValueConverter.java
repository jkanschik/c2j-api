package c2j.api.dataStructures;

import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;

/**
 * Created by kanschje on 14.03.2017.
 */
public class NumericValueConverter {

  public static BigDecimal fromPIC9(byte[] bytes, int scale) {
    StringBuffer sb = new StringBuffer(bytes.length);
    for (byte b : bytes) {
      if ((b & 0xF0) != 0xF0) {
        throw new RuntimeException("Invalid number " + DatatypeConverter.printHexBinary(bytes));
      }
      sb.append(b & 0x0F);
    }
    BigDecimal result = new BigDecimal(sb.toString());
    result.setScale(scale);
    return result;
  }

}
