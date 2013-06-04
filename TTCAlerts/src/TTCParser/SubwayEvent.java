package TTCParser;

public class SubwayEvent extends Event {
	private static final long serialVersionUID = 1L;
	private Station _station;
	private DelayCause _delayCause;
	private String _delayString;
	
	private DelayCause inferCause(String cause) {
		if (cause.contains("smoke")) {
			return DelayCause.SMOKE_AT_TRACK_LEVEL;
		} else if (cause.contains("personal injury")) {
			return DelayCause.PERSONAL_INJURY_AT_TRACK_LEVEL;
		} else if (cause.contains("fire investigation")) {
			return DelayCause.FIRE_INVESTIGATION;
		} else if (cause.contains("mechanical")) {
			return DelayCause.MECHANICAL_PROBLEMS;
		} else if (cause.contains("signal")){
			return DelayCause.SIGNAL_PROBLEMS;
		} else {
			return DelayCause.OTHER;
		}
	}

	public SubwayEvent(HalfEvent start, HalfEvent end, Station station, DelayCause delayCause) {
		super(start, end);
		_station = station;
		_delayCause = delayCause;
	}
	
	public SubwayEvent(SubwayHalfEvent start, SubwayHalfEvent end) {
		super(start, end);
		_station = start.getStation();
		
		// Try and extract delay
		String originalSourceMsg = start.getOriginalMessage().toLowerCase();
		int delayStartIdx = originalSourceMsg.indexOf("due to ");
		if (delayStartIdx >= 0 ) {
			int delayEndIdx = originalSourceMsg.indexOf(" at ", delayStartIdx);
			if (delayEndIdx >= 0 && delayEndIdx > delayStartIdx) {
				String causeString = originalSourceMsg.substring(delayStartIdx, delayEndIdx);
				_delayCause = inferCause(causeString);
				_delayString = causeString;
			} else {
				_delayCause = DelayCause.OTHER;
				_delayString = "";
			}
		} else {
			_delayCause = DelayCause.OTHER;
			_delayString = "";
		}

	}
	
	public Station getStation() { return _station; }
	public void setStation(Station s) { _station = s; }
	
	public DelayCause getDelayCause() { return _delayCause; }
	public void setDelayCause(DelayCause delayCause) { _delayCause = delayCause; }
	
	public String toString() { 
		return _delayCause.getName() + " at " + _station.getName() + " for " + getDurationInMins() + " minutes";
	}
}
