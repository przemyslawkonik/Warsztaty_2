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
		PreparedStatement ps = conn.prepareStatement(Query.selectAllExcercises());
		ResultSet rs = ps.executeQuery();
		List<Excercise> excercises = load(rs);
		ps.close();
		rs.close();
		return excercises;
	}

	public static Excercise loadById(Connection conn, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectExcerciseById());
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<Excercise> excercises = load(rs);
		ps.close();
		rs.close();
		return excercises.get(0);
	}

	public void save(Connection conn) throws SQLException {
		if (id == 0) {
			PreparedStatement ps = conn.prepareStatement(Query.insertExcercise(), new String[] { "id" });
			PsUtil.prepare(ps, Operation.INSERT, this);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} else {
			PreparedStatement ps = conn.prepareStatement(Query.updateExcercise());
			PsUtil.prepare(ps, Operation.UPDATE, this);
			ps.executeUpdate();
			ps.close();
		}
	}

	public void delete(Connection conn) throws SQLException {
		if (id != 0) {
			PreparedStatement ps = conn.prepareStatement(Query.deleteExcercise());
			PsUtil.prepare(ps, Operation.DELETE, this);
			ps.executeUpdate();
			ps.close();
			id = 0;
		}
	}

	public void copy(Excercise e) {
		this.title = e.title;
		this.description = e.description;
	}

	private static List<Excercise> load(ResultSet rs) throws SQLException {
		List<Excercise> excercises = new ArrayList<>();
		while (rs.next()) {
			Excercise e = new Excercise();
			e.id = rs.getInt("id");
			e.title = rs.getString("title");
			e.description = rs.getString("description");
			excercises.add(e);
		}
		return excercises;
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
