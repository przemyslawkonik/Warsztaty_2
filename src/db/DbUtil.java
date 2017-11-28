package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;

public class DbUtil {

	public static Connection getConn() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztat2?useSSL=false", "root", "coderslab");
	}

	public static String convert(Date d) {
		return new Timestamp(d.getTime()).toString();
	}

	public static String convert(DateTime dt) {
		return new Timestamp(dt.getMillis()).toString();
	}
}
