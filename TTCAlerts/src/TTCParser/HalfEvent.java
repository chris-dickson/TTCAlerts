package TTCParser;

public class HalfEvent {
	private long _date;
	private String _originalMessage;
	
	public HalfEvent(long date, String originalMessage) {
		_date = date;
		_originalMessage = originalMessage;
	}
	
	public long getDate() { return _date;}
	public void setDate(long date) { _date = date; }
	
	public String getOriginalMessage() { return _originalMessage; }
	public void setOriginalMessage(String originalMessage) { _originalMessage = originalMessage; }
}
