package TTCParser;

public enum DelayCause {
	PERSONAL_INJURY_AT_TRACK_LEVEL("Personal Injury at Track Level"),
	SMOKE_AT_TRACK_LEVEL("Smoke at Track Level"),
	FIRE_INVESTIGATION("Fire Investigation"),
	MECHANICAL_PROBLEMS("Mechanical Problems"),
	SIGNAL_PROBLEMS("Signal Problems"),
	OTHER("Other");

	private DelayCause(String name) {
		_name = name;
	}
	private final String _name;
	public String getName() { return _name; }
}
