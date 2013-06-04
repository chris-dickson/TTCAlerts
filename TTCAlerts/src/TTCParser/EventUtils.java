package TTCParser;

import java.util.ArrayList;

public class EventUtils {
	public static ArrayList<HalfEvent> getHalfEventsWithin(ArrayList<HalfEvent> events, long start, long end) {
		ArrayList<HalfEvent> ret = new ArrayList<HalfEvent>();
		for (HalfEvent e : events) {
			if (start <= e.getDate() && e.getDate() <= end) {
				ret.add(e);
			}
		}
		return ret;
	}

}
