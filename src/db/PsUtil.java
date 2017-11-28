package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

public class PsUtil {

	public static void prepare(PreparedStatement ps, Operation o, User u) throws SQLException {
		switch (o) {
		case INSERT: {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getUserGroupId());
			break;
		}
		case UPDATE: {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getUserGroupId());
			ps.setLong(5, u.getId());
			break;
		}
		case DELETE: {
			ps.setLong(1, u.getId());
			break;
		}
		}
	}

}
