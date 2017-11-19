package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Group {
	private int id;
	private String name;

	public Group() {
	}

	public Group(String name) {
		this.name = name;
	}

	public static List<Group> loadAll(Connection conn) throws SQLException {
		List<Group> groups = new ArrayList<>();
		String sql = "SELECT * FROM user_group";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = rs.getInt("id");
			loadedGroup.name = rs.getString("name");
			groups.add(loadedGroup);
		}
		ps.close();
		rs.close();
		return groups;
	}

	public static Group loadById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM user_group WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = rs.getInt("id");
			loadedGroup.name = rs.getString("name");
			ps.close();
			rs.close();
			return loadedGroup;
		}
		ps.close();
		rs.close();
		return null;
	}

	public void save(Connection conn) throws SQLException {
		if (id == 0) {
			String sql = "INSERT INTO user_group(name) VALUES(?)";
			String[] generatedColumns = { "id" };
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, name);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} else {
			String sql = "UPDATE user_group SET name=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
		}
	}

	public void delete(Connection conn) throws SQLException {
		if (id != 0) {
			String sql = "DELETE FROM user_group WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			id = 0;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

}
