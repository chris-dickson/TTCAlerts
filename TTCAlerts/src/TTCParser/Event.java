package TTCParser;

import java.io.Serializable;

public class Event implements Serializable{
	private static final long serialVersionUID = 1L;
	private HalfEvent _start;
	private HalfEvent _end;
	
	public Event(HalfEvent start, HalfEvent end) {
		_start = start;
		_end = end;
	}
	
	public long getStartTime() { return _start.getDate(); }
	public long getEndTime() { return _end.getDate(); }
	
	public HalfEvent getStart() { return _start; }
	public void setStart(HalfEvent start) { _start = start; }
	
	public HalfEvent getEnd() { return _end; }
	public void setEnd(HalfEvent end) { _end = end; }
		
	public long getDuration() { return getEndTime() - getStartTime(); }
	public int getDurationInMins() { return (int)(getDuration()/(60*1000)); }
}
