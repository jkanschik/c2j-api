package c2j.api.dataStructures;

public interface AlphaNumericCobolRecord extends CobolRecord {

  public ReferenceModification getRefMod(int from);
  public ReferenceModification getRefMod(int from, int size);

}
