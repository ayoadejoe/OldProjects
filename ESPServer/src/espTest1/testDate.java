package espTest1;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class testDate {
	private static SimpleDateFormat getTimeOnly = new SimpleDateFormat("HH:mm");
	public testDate() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date today = new Date();
		String time = getTimeOnly.format(today);
		System.out.println(time);
		
		String dateInString = "2014-10-05T15:23:01Z";

        Instant instant = Instant.parse(dateInString);

        System.out.println(instant);
        
        Date tre = Date.from(instant);
        
        System.out.println("TRE:"+tre);

        //get date time only
        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

        System.out.println(result);

        //get date time + timezone
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Africa/Tripoli"));
        System.out.println(zonedDateTime);

        //get date time + timezone
        ZonedDateTime zonedDateTime2 = instant.atZone(ZoneId.of("Europe/Athens"));
        System.out.println(zonedDateTime2);

	}

}
