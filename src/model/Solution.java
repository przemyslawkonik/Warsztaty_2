package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	private int id;
	private String created;
	private String updated;
	private String description;
	private int excerciseId;
	private long userId;

	public Solution() {
	}

	public Solution(String created, String updated, String description, int excerciseId, long userId) {
		this.created = created;
		this.updated = updated;
		this.description = description;
		this.excerciseId = excerciseId;
		this.userId = userId;
	}

	public static List<Solution> loadAllByUserId(Connection conn, long userId) throws SQLException {
		List<Solution> solutions = new ArrayList<>();
		String sql = "SELECT * FROM solution WHERE users_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, userId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = rs.getInt("id");
			loadedSolution.created = rs.getString("created");
			loadedSolution.updated = rs.getString("updated");
			loadedSolution.description = rs.getString("description");
			loadedSolution.excerciseId = rs.getInt("excercise_id");
			loadedSolution.userId = rs.getLong("users_id");
			solutions.add(loadedSolution);
		}
		ps.close();
		rs.close();
		return solutions;
	}

	public static List<Solution> loadAllByExcerciseId(Connection conn, int excerciseId) throws SQLException {
		List<Solution> solutions = new ArrayList<>();
		String sql = "SELECT * FROM solution WHERE excercise_id=? ORDER BY created DESC";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, excerciseId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = rs.getInt("id");
			loadedSolution.created = rs.getString("created");
			loadedSolution.updated = rs.getString("updated");
			loadedSolution.description = rs.getString("description");
			loadedSolution.excerciseId = rs.getInt("excercise_id");
			loadedSolution.userId = rs.getLong("users_id");
			solutions.add(loadedSolution);
		}
		ps.close();
		rs.close();
		return solutions;
	}

	public static List<Solution> loadAll(Connection conn) throws SQLException {
		List<Solution> solutions = new ArrayList<>();
		String sql = "SELECT * FROM solution";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = rs.getInt("id");
			loadedSolution.created = rs.getString("created");
			loadedSolution.updated = rs.getString("updated");
			loadedSolution.description = rs.getString("description");
			loadedSolution.excerciseId = rs.getInt("excercise_id");
			loadedSolution.userId = rs.getLong("users_id");
			solutions.add(loadedSolution);
		}
		ps.close();
		rs.close();
		return solutions;
	}

	public static Solution loadById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solution WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = rs.getInt("id");
			loadedSolution.created = rs.getString("created");
			loadedSolution.updated = rs.getString("updated");
			loadedSolution.description = rs.getString("description");
			loadedSolution.excerciseId = rs.getInt("excercise_id");
			loadedSolution.userId = rs.getLong("users_id");
			ps.close();
			rs.close();
			return loadedSolution;
		}
		ps.close();
		rs.close();
		return null;
	}

	public void save(Connection conn) throws SQLException {
		if (id == 0) {
			String sql = "INSERT INTO solution(created, updated, description, excercise_id, users_id) VALUES(?, ?, ?, ?, ?)";
			String[] generatedColumns = { "id" };
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, created);
			ps.setString(2, updated);
			ps.setString(3, description);
			ps.setInt(4, excerciseId);
			ps.setLong(5, userId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} else {
			String sql = "UPDATE solution SET created=?, updated=?, description=? excercise_id=?, user_id=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, created);
			ps.setString(2, updated);
			ps.setString(3, description);
			ps.setInt(4, excerciseId);
			ps.setLong(5, userId);
			ps.setInt(6, id);
			ps.executeUpdate();
			ps.close();
		}
	}

	public void delete(Connection conn) throws SQLException {
		if (id != 0) {
			String sql = "DELETE FROM solution WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			id = 0;
		}
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExcerciseId() {
		return excerciseId;
	}

	public void setExcerciseId(int excerciseId) {
		this.excerciseId = excerciseId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

}
