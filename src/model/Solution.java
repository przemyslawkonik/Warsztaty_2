package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Operation;
import db.PsUtil;
import db.Query;

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
		PreparedStatement ps = conn.prepareStatement(Query.selectAllSolutionsByUserId());
		ps.setLong(1, userId);
		ResultSet rs = ps.executeQuery();
		List<Solution> solutions = load(rs);
		ps.close();
		rs.close();
		return solutions;
	}

	public static List<Solution> loadAllByExcerciseId(Connection conn, int excerciseId) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectAllSolutionsByExcerciseId());
		ps.setInt(1, excerciseId);
		ResultSet rs = ps.executeQuery();
		List<Solution> solutions = load(rs);
		ps.close();
		rs.close();
		return solutions;
	}

	public static List<Solution> loadAll(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectAllSolutions());
		ResultSet rs = ps.executeQuery();
		List<Solution> solutions = load(rs);
		ps.close();
		rs.close();
		return solutions;
	}

	public static Solution loadById(Connection conn, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectSolutionById());
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<Solution> solutions = load(rs);
		ps.close();
		rs.close();
		return solutions.get(0);
	}

	public void save(Connection conn) throws SQLException {
		if (id == 0) {
			PreparedStatement ps = conn.prepareStatement(Query.insertSolution(), new String[] { "id" });
			PsUtil.prepare(ps, Operation.INSERT, this);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} else {
			PreparedStatement ps = conn.prepareStatement(Query.updateSolution());
			PsUtil.prepare(ps, Operation.UPDATE, this);
			ps.executeUpdate();
			ps.close();
		}
	}

	public void delete(Connection conn) throws SQLException {
		if (id != 0) {
			PreparedStatement ps = conn.prepareStatement(Query.deleteSolution());
			PsUtil.prepare(ps, Operation.DELETE, this);
			ps.executeUpdate();
			ps.close();
			id = 0;
		}
	}

	private static List<Solution> load(ResultSet rs) throws SQLException {
		List<Solution> solutions = new ArrayList<>();
		while (rs.next()) {
			Solution s = new Solution();
			s.id = rs.getInt("id");
			s.created = rs.getString("created");
			s.updated = rs.getString("updated");
			s.description = rs.getString("description");
			s.excerciseId = rs.getInt("excercise_id");
			s.userId = rs.getLong("users_id");
			solutions.add(s);
		}
		return solutions;
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
