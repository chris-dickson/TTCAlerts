package TTCParser;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import GmailPull.GmailMessage;
import TTCParser.Event;
import TTCParser.Station;
import TTCParser.SubwayHalfEvent;

public class GmailMessageParser {

	public static ArrayList<Event> messageListToEventList(ArrayList<GmailMessage> messages) {
		ArrayList<Event> events = new ArrayList<Event>();
		
		return events;
	}
	
	public static long ParseDateFromMessage(GmailMessage message) throws ParseException {
		//3/18/10 4:14 PM
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm aa");
		int sentIdx = message.getBody().indexOf("Sent: ");
		int nextLineIdx = message.getBody().indexOf('\n', sentIdx);
		String dateSubstring = message.getBody().substring(sentIdx + 6, nextLineIdx);
		dateSubstring = dateSubstring.replace("\r", "");
		Date sentDate = (Date) sdf.parse(dateSubstring);
		return sentDate.getTime();
	}
	
	public static ArrayList<SubwayHalfEvent> getSubwayHalfEvents(ArrayList<GmailMessage> messages) {
		ArrayList<SubwayHalfEvent> she = new ArrayList<SubwayHalfEvent>();
		
		for (GmailMessage msg : messages) {
			String msgBody = msg.getBody();
			for (Station s : Station.values()) {
				if (msgBody.contains(s.getName())) {
					
					long sentDate;
					try {
						sentDate = ParseDateFromMessage(msg);
					} catch (ParseException e) {
						sentDate = 0;
					}
					
					SubwayHalfEvent he = new SubwayHalfEvent(sentDate, msg.getBody(), s);
					she.add(he);
				}
			}
		}
		
		return she;
	}
	
}
