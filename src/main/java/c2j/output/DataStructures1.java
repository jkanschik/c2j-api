package c2j.output;

import c2j.api.*;
import c2j.api.dataStructures.*;

import c2j.output.dataStructures.*;

public class DataStructures1 extends CobolProgram {

	// Data structures
				private final AlphaNumericCobolRecordImpl FELD_X1 = new AlphaNumericCobolRecordImpl("X");
				private final AlphaNumericCobolRecordImpl FELD_X1_S = new AlphaNumericCobolRecordImpl("X", "");
				private final AlphaNumericCobolRecordImpl FELD_X1_A = new AlphaNumericCobolRecordImpl("X", "A");
				private final AlphaNumericCobolRecordImpl FELD_X2 = new AlphaNumericCobolRecordImpl("X(10)");
				private final AlphaNumericCobolRecordImpl FELD_X2_S = new AlphaNumericCobolRecordImpl("X(10)", "");
				private final AlphaNumericCobolRecordImpl FELD_X2_A = new AlphaNumericCobolRecordImpl("X(10)", "AAAAAAAAAA");
				private final NumericCobolRecord FELD_X2_A_N = new NumericCobolRecord("9(10)", FELD_X2_A);
				private final NumericCobolRecord FELD_N1 = new NumericCobolRecord("9", 0);
				private final NumericCobolRecord FELD_N2 = new NumericCobolRecord("9(10)", 0);
			private final GROUP_1 GROUP_1 = new GROUP_1();		
			private final GROUP_2 GROUP_2 = new GROUP_2();		

	public static void main(String[] args) {
		DataStructures1 program = new DataStructures1();
		program.execute();
	}
	
	public void execute() {
	
	}

}
