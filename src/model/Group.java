package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbUtil;
import db.Operation;
import db.PsUtil;
import db.Query;

public class Group {
	private int id;
	private String name;

	public Group() {
	}

	public Group(String name) {
		this.name = name;
	}

	public static List<Group> loadAll(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectAllGroups());
		ResultSet rs = ps.executeQuery();
		List<Group> groups = load(rs);
		DbUtil.closeAll(ps, rs);
		return groups;
	}

	public static Group loadById(Connection conn, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectGroupById());
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<Group> groups = load(rs);
		DbUtil.closeAll(ps, rs);
		return groups.get(0);
	}

	public void save(Connection conn) throws SQLException {
		if (id == 0) {
			PreparedStatement ps = conn.prepareStatement(Query.insertGroup(), new String[] { "id" });
			PsUtil.prepare(ps, Operation.INSERT, this);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			synchronizeId(rs);
			DbUtil.closeAll(ps, rs);
		} else {
			PreparedStatement ps = conn.prepareStatement(Query.updateGroup());
			PsUtil.prepare(ps, Operation.UPDATE, this);
			ps.executeUpdate();
			ps.close();
		}
	}

	public void delete(Connection conn) throws SQLException {
		if (id != 0) {
			PreparedStatement ps = conn.prepareStatement(Query.deleteGroup());
			PsUtil.prepare(ps, Operation.DELETE, this);
			ps.executeUpdate();
			ps.close();
			id = 0;
		}
	}

	private static List<Group> load(ResultSet rs) throws SQLException {
		List<Group> groups = new ArrayList<>();
		while (rs.next()) {
			Group g = new Group();
			g.id = rs.getInt("id");
			g.name = rs.getString("name");
			groups.add(g);
		}
		return groups;
	}
	
	public void copy(Group g) {
		this.name = g.name;
	}

	private void synchronizeId(ResultSet rs) throws SQLException {
		if (rs.next()) {
			id = rs.getInt(1);
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
