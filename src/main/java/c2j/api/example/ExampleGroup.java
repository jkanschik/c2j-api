package c2j.api.example;

import c2j.api.dataStructures.AlphaNumericCobolRecord;
import c2j.api.dataStructures.DisplayRecord;
import c2j.api.dataStructures.GroupImpl;
import c2j.api.dataStructures.NumericCobolRecord;
import c2j.api.dataStructures.NumericRecord;

/**
 * Created by kanschje on 13.03.2017.
 */
public class ExampleGroup extends GroupImpl {

    @DisplayRecord(pic = "X", value = "A")
    public AlphaNumericCobolRecord field1;

    @DisplayRecord(pic = "X(5)", value = "BBBBB")
    public AlphaNumericCobolRecord field2;

    @DisplayRecord(pic = "X", value = "4")
    public AlphaNumericCobolRecord field3;

    @NumericRecord(pic = "9", redefines = "field3")
    public NumericCobolRecord field3N;

}
