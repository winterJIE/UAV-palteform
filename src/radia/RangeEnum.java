package radia;

public enum RangeEnum {
	FIRST(100), SECOND(200), THIRD(300), FOURTH(400), FIFTH(500), SIXTH(600);
	
	private int value;
	
	private RangeEnum(){
		this(0);
	}
	private RangeEnum(int value){
		this.setValue(value);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
