package c2j.api;

import c2j.api.dataStructures.AlphaNumericCobolRecordImpl;

/**
 * Created by kanschje on 09.03.2017.
 */
public class Main extends CobolProgram {

	public static void main(String... args) {
		new Main().execute();
	}
	public void execute() {

		AlphaNumericCobolRecordImpl feld1 = new AlphaNumericCobolRecordImpl("X(10)", "INITIAL");
		AlphaNumericCobolRecordImpl feld2 = new AlphaNumericCobolRecordImpl("X(10)");

		display(feld1);

		move("Hallo Welt", feld1);
		display(feld1);

		// MOVE "Hallo" TO FELD1
		move("Test", feld1);
		display(feld1);

		// MOVE "Hallo" TO FELD1(3:)
		move("Hallo", feld1.getRefMod(3));
		display(feld1);

		// MOVE FELD1(5:2) TO FELD2
		move(feld1.getRefMod(5, 2), feld2);
		display(feld1);

		move(feld1, feld2);
		display(feld2);
	}
}
