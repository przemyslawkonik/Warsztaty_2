package db;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;

public class DbUtil {

	public static String convert(Date d) {
		return new Timestamp(d.getTime()).toString();
	}

	public static String convert(DateTime dt) {
		return new Timestamp(dt.getMillis()).toString();
	}
}
