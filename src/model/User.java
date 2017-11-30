package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbUtil;
import org.mindrot.jbcrypt.BCrypt;

import db.Operation;
import db.PsUtil;
import db.Query;

public class User {
	private long id;
	private String username;
	private String email;
	private String password;
	private int userGroupId;

	public User() {
	}

	public User(String username, String email, String password, int userGroupId) {
		this.username = username;
		this.email = email;
		setPassword(password);
		this.userGroupId = userGroupId;
	}

	public static List<User> loadAll(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectAllUsers());
		ResultSet rs = ps.executeQuery();
		List<User> users = load(rs);
		DbUtil.closeAll(ps, rs);
		return users;
	}

	public static List<User> loadAllByGroupId(Connection conn, int userGroupId) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectAllUsersByGroupId());
		ps.setInt(1, userGroupId);
		ResultSet rs = ps.executeQuery();
		List<User> users = load(rs);
		DbUtil.closeAll(ps, rs);
		return users;
	}

	public static User loadById(Connection conn, long id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query.selectUserById());
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		List<User> users = load(rs);
		DbUtil.closeAll(ps, rs);
		return users.get(0);
	}

	public void save(Connection conn) throws SQLException {
		if (id == 0) {
			PreparedStatement ps = conn.prepareStatement(Query.insertUser(), new String[] { "id" });
			PsUtil.prepare(ps, Operation.INSERT, this);
			ps.executeUpdate();
			id = PsUtil.synchronizeId(ps);
			ps.close();
		} else {
			PreparedStatement ps = conn.prepareStatement(Query.updateUser());
			PsUtil.prepare(ps, Operation.UPDATE, this);
			ps.executeUpdate();
			ps.close();
		}
	}

	public void delete(Connection conn) throws SQLException {
		if (id != 0) {
			PreparedStatement ps = conn.prepareStatement(Query.deleteUser());
			PsUtil.prepare(ps, Operation.DELETE, this);
			ps.executeUpdate();
			id = PsUtil.synchronizeId(ps);
			ps.close();
		}
	}

	public void copy(User user) {
		this.username = user.username;
		this.email = user.email;
		this.password = user.password;
		this.userGroupId = user.userGroupId;
	}

	private static List<User> load(ResultSet rs) throws SQLException {
		List<User> users = new ArrayList<>();
		while (rs.next()) {
			User u = new User();
			u.id = rs.getLong("id");
			u.username = rs.getString("username");
			u.email = rs.getString("email");
			u.password = rs.getString("password");
			u.userGroupId = rs.getInt("user_group_id");
			users.add(u);
		}
		return users;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean checkPassword(String password) {
		return BCrypt.checkpw(password, this.password);
	}

	public int getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	public long getId() {
		return id;
	}

}
