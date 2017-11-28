package db;

public class Query {

	public static String insertUser() {
		return "INSERT INTO users(username, email, password, user_group_id) VALUES(?, ?, ?, ?)";
	}

	public static String updateUser() {
		return "UPDATE users SET username=?, email=?, password=?, user_group_id=? WHERE id=?";
	}

	public static String deleteUser() {
		return "DELETE FROM users WHERE id=?";
	}

	public static String selectAllUsers() {
		return "SELECT * FROM users";
	}

	public static String selectAllUsersByGroupId() {
		return "SELECT * FROM users WHERE user_group_id=?";
	}

	public static String selectAllUsersById() {
		return "SELECT * FROM users WHERE id=?";
	}

	public static String insertGroup() {
		return "INSERT INTO user_group(name) VALUES(?)";
	}

	public static String updateGroup() {
		return "UPDATE user_group SET name=? WHERE id=?";
	}

	public static String deleteGroup() {
		return "DELETE FROM user_group WHERE id=?";
	}

	public static String selectAllGroups() {
		return "SELECT * FROM user_group";
	}

	public static String selectAllGroupsById() {
		return "SELECT * FROM user_group WHERE id=?";
	}
}
