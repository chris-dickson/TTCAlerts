package TTCParser;

import java.io.Serializable;

public class Event implements Serializable{
	private static final long serialVersionUID = 1L;
	private long _start;
	private long _end;
	private String _originalMessage;
	
	public Event(long start, long end, String originalMessage) {
		_start = start;
		_end = end;
		_originalMessage = originalMessage;
	}
	
	public long getStart() { return _start; }
	public void setStart(long start) { _start = start;}
	
	public long getEnd() { return _end; }
	public void setEnd(long end) { _end = end; }
	
	public String getOriginalMessage() { return _originalMessage;}
	public void setOriginalMessage(String originalMessage) { _originalMessage = originalMessage;}
	
	public long getDuration() { return _end - _start; }
	public int getDurationInMins() { return (int)(getDuration()/(60*1000)); }
}
