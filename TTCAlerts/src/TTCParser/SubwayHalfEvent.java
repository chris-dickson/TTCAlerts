package TTCParser;

public class SubwayHalfEvent extends HalfEvent {
	private Station _station;
	
	public SubwayHalfEvent(long date, String originalMessage, Station station) {
		super(date,originalMessage);
		_station = station;
	}
	
	public Station getStation() { return _station; }
	public void setStation(Station station) { _station = station; }
}
