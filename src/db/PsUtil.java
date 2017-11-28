package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Excercise;
import model.Group;
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

	public static void prepare(PreparedStatement ps, Operation o, Group g) throws SQLException {
		switch (o) {
		case INSERT: {
			ps.setString(1, g.getName());
			break;
		}
		case UPDATE: {
			ps.setString(1, g.getName());
			ps.setInt(2, g.getId());
			break;
		}
		case DELETE: {
			ps.setInt(1, g.getId());
			break;
		}
		}
	}

	public static void prepare(PreparedStatement ps, Operation o, Excercise e) throws SQLException {
		switch (o) {
		case INSERT: {
			ps.setString(1, e.getTitle());
			ps.setString(2, e.getDescription());
			break;
		}
		case UPDATE: {
			ps.setString(1, e.getTitle());
			ps.setString(2, e.getDescription());
			ps.setInt(3, e.getId());
			break;
		}
		case DELETE: {
			ps.setInt(1, e.getId());
			break;
		}
		}
	}

}
