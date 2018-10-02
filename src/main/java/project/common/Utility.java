package project.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

	public static Timestamp getCurrentDateTimeForQuery() {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		return now;
	}

	public static String getFormatedDateTime(LocalDateTime dateTime) {
		String now = null;
		DateTimeFormatter formate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		now = dateTime.format(formate);
		return now;
	}

}
