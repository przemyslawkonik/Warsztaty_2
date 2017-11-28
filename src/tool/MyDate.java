package tool;

import java.util.Date;

import db.DbUtil;

public class MyDate {

	public static String get() {
		return DbUtil.convert(new Date());
	}
}
