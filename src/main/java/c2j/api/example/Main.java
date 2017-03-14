package c2j.api.example;

import c2j.api.CobolProgram;
import c2j.api.dataStructures.AlphaNumericCobolRecord;
import c2j.api.dataStructures.DisplayRecord;
import c2j.api.dataStructures.Group;

/**
 * Created by kanschje on 09.03.2017.
 */
public class Main extends CobolProgram {

	@DisplayRecord(pic = "X(10)", value = "INITIAL")
	private AlphaNumericCobolRecord feld1;

	@DisplayRecord(pic = "X(10)")
	private AlphaNumericCobolRecord feld2;

	@Group
	private ExampleGroup someGroup;

	public static void main(String... args) {
		new Main().execute();
	}

	public void execute() {

		display(feld1);
		display(someGroup.field1);
		display(someGroup.field2);
		display(someGroup.field3);
		display(someGroup.field3N);

		move("1", someGroup.field3);
		display(someGroup.field3N);

		move("1234567", someGroup);
		display(someGroup.field1);
		display(someGroup.field2);
		display(someGroup.field3);
		display(someGroup.field3N);

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
