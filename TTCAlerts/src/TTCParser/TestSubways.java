package TTCParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import GmailPull.GmailMessage;
import GmailPull.MessageArchiver;
import TTCParser.EventUtils;
import TTCParser.HalfEvent;
import TTCParser.Station;
import TTCParser.SubwayEvent;
import TTCParser.SubwayHalfEvent;

public class TestSubways {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<GmailMessage> messages = MessageArchiver.restore("..\\SampleData\\smallTTC.gma");
		ArrayList<SubwayHalfEvent> halfEvents = GmailMessageParser.getSubwayHalfEvents(messages);
		
		// Split into possible sources and dests
		ArrayList<HalfEvent> possibleEnds = new ArrayList<HalfEvent>();
		ArrayList<HalfEvent> possibleStarts = new ArrayList<HalfEvent>();
		for (SubwayHalfEvent he : halfEvents) {
			if (he.getOriginalMessage().contains("resume")) {
				possibleEnds.add(he);
			} else {
				possibleStarts.add(he);
			}
		}
		
		ArrayList<SubwayEvent> matchedEvents = new ArrayList<SubwayEvent>();
		for (HalfEvent startEvent : possibleStarts) {
			
			// get all events within a day of the start
			ArrayList<HalfEvent> nearEnds = EventUtils.getHalfEventsWithin(possibleEnds, startEvent.getDate(), startEvent.getDate() + 86400000);
			
			// Get earliest event in near end that contains the station name from the start
			Collections.sort(nearEnds, new Comparator<HalfEvent>() {
				public int compare(HalfEvent e1, HalfEvent e2) {
					return (int)(e1.getDate() - e2.getDate());
				}
			});
			
			HalfEvent matchedEvent = null;
			boolean bMatched = false;
			for (HalfEvent possibleEnd : nearEnds) {
				Station sourceStation = ((SubwayHalfEvent)startEvent).getStation();
				if (possibleEnd.getOriginalMessage().contains(sourceStation.getName())) {
					matchedEvents.add(new SubwayEvent((SubwayHalfEvent)startEvent, (SubwayHalfEvent)possibleEnd));
					matchedEvent = possibleEnd;
					bMatched = true;
					break;
				}
			}
			if (bMatched) {
				possibleEnds.remove(matchedEvent);
			}
		}
	}

}
