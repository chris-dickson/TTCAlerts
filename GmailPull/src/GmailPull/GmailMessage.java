package GmailPull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GmailMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _fromAddress;
	private String _subject;
	private String _body;
	private long _dateSent;
	
	public GmailMessage() {}
	public GmailMessage(String title, String fromAddress, String subject, String body, long dateSent) {
		_fromAddress = fromAddress;
		_subject = subject;
		_body = body;
		_dateSent = dateSent;
	}
		
	public String getFromAddress() { return _fromAddress; }
	public void setFromAddress(String s) { _fromAddress = s;}
	
	public String getSubject() { return _subject; }
	public void setSubject(String s) { _subject = s;}
	
	public String getBody() { return _body; }
	public void setBody(String s) { _body = s;}
	
	public long getDateSent() { return _dateSent; }
	public void setDateSent(long l) { _dateSent = l; }
	
	public String toString() {
		Date d = new Date(_dateSent);
		SimpleDateFormat df2 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
		
		String formattedMsg = "";
		formattedMsg += "<" + _fromAddress + "> ";
		formattedMsg += _subject + "( " + df2.format(d) + " )" + '\n';
		formattedMsg += _body + '\n' + '\n';
		return formattedMsg;
	}
}
