package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Excercise {
	private int id;
	private String title;
	private String description;

	public Excercise() {
	}

	public Excercise(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public static List<Excercise> loadAll(Connection conn) throws SQLException {
		List<Excercise> excercises = new ArrayList<>();
		String sql = "SELECT * FROM excercise";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Excercise loadedExcercise = new Excercise();
			loadedExcercise.id = rs.getInt("id");
			loadedExcercise.title = rs.getString("title");
			loadedExcercise.description = rs.getString("description");
			excercises.add(loadedExcercise);
		}
		ps.close();
		rs.close();
		return excercises;
	}

	public static Excercise loadById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM excercise WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Excercise loadedExcercise = new Excercise();
			loadedExcercise.id = rs.getInt("id");
			loadedExcercise.title = rs.getString("title");
			loadedExcercise.description = rs.getString("description");
			ps.close();
			rs.close();
			return loadedExcercise;
		}
		ps.close();
		rs.close();
		return null;
	}

	public void save(Connection conn) throws SQLException {
		if (id == 0) {
			String sql = "INSERT INTO excercise(title, description) VALUES(?, ?)";
			String[] generatedColumns = { "id" };
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, title);
			ps.setString(2, description);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} else {
			String sql = "UPDATE excercise SET title=?, description=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setInt(3, id);
			ps.executeUpdate();
			ps.close();
		}
	}

	public void delete(Connection conn) throws SQLException {
		if (id != 0) {
			String sql = "DELETE FROM excercise WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			id = 0;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

}
