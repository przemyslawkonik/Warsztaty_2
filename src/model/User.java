package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

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
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User loadedUser = new User();
			loadedUser.id = rs.getLong("id");
			loadedUser.username = rs.getString("username");
			loadedUser.email = rs.getString("email");
			loadedUser.password = rs.getString("password");
			loadedUser.userGroupId = rs.getInt("user_group_id");
			users.add(loadedUser);
		}
		ps.close();
		rs.close();
		return users;
	}

	public static List<User> loadAllByGroupId(Connection conn, int userGroupId) throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE user_group_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userGroupId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User loadedUser = new User();
			loadedUser.id = rs.getLong("id");
			loadedUser.username = rs.getString("username");
			loadedUser.email = rs.getString("email");
			loadedUser.password = rs.getString("password");
			loadedUser.userGroupId = rs.getInt("user_group_id");
			users.add(loadedUser);
		}
		ps.close();
		rs.close();
		return users;
	}

	public static User loadById(Connection conn, long id) throws SQLException {
		String sql = "SELECT * FROM users WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			User loadedUser = new User();
			loadedUser.id = rs.getLong("id");
			loadedUser.username = rs.getString("username");
			loadedUser.email = rs.getString("email");
			loadedUser.password = rs.getString("password");
			loadedUser.userGroupId = rs.getInt("user_group_id");
			ps.close();
			rs.close();
			return loadedUser;
		}
		ps.close();
		rs.close();
		return null;
	}

	public void save(Connection conn) throws SQLException {
		if (id == 0) {
			String sql = "INSERT INTO users(username, email, password, user_group_id) VALUES(?, ?, ?, ?)";
			String[] generatedColumns = { "id" };
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setInt(4, userGroupId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong(1);
			}
			rs.close();
			ps.close();
		} else {
			String sql = "UPDATE users SET username=?, email=?, password=?, user_group_id=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setInt(4, userGroupId);
			ps.setLong(5, id);
			ps.executeUpdate();
			ps.close();
		}
	}

	public void delete(Connection conn) throws SQLException {
		if (id != 0) {
			String sql = "DELETE FROM users WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
			id = 0;
		}
	}

	public void copy(User user) {
		this.username = user.username;
		this.email = user.email;
		this.password = user.password;
		this.userGroupId = user.userGroupId;
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
