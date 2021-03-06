package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Excercise;
import model.Group;
import model.Solution;
import model.User;

public class PsUtil {

	public static int synchronizeId(PreparedStatement ps) {
		try (ResultSet rs = ps.getGeneratedKeys()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			return 0;
		}
	}

	public static void prepare(PreparedStatement ps, Operation o, User u) throws SQLException {
		switch (o) {
		case UPDATE: {
			ps.setLong(5, u.getId());
			// no break
		}
		case INSERT: {
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getUserGroupId());
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
		case UPDATE: {
			ps.setInt(2, g.getId());
			// no break
		}
		case INSERT: {
			ps.setString(1, g.getName());
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
		case UPDATE: {
			ps.setInt(3, e.getId());
			// no break
		}
		case INSERT: {
			ps.setString(1, e.getTitle());
			ps.setString(2, e.getDescription());
			break;
		}
		case DELETE: {
			ps.setInt(1, e.getId());
			break;
		}
		}
	}

	public static void prepare(PreparedStatement ps, Operation o, Solution s) throws SQLException {
		switch (o) {
		case UPDATE: {
			ps.setInt(6, s.getId());
			// no break
		}
		case INSERT: {
			ps.setString(1, s.getCreated());
			ps.setString(2, s.getUpdated());
			ps.setString(3, s.getDescription());
			ps.setInt(4, s.getExcerciseId());
			ps.setLong(5, s.getUserId());
			break;
		}
		case DELETE: {
			ps.setInt(1, s.getId());
			break;
		}
		}
	}

}
