package app;

import java.sql.Connection;
import java.sql.SQLException;

import db.DbUtil;
import io.Input;
import io.Output;
import model.User;

public class UserApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn()) {
			while (true) {
				System.out.println("List of users (id, username, email, userGroupId):\n");
				Output.printUsers(User.loadAll(conn));
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (Input.get()) {
				case "add": {
					System.out.println("\nAdd user menu");
					User u = getUser();
					u.save(conn);
					System.out.println("\nUser has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit user menu");
					User u = User.loadById(conn, getUserId());
					u.copy(getUser());
					u.save(conn);
					System.out.println("\nUser has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete user menu");
					User u = User.loadById(conn, getUserId());
					u.delete(conn);
					System.out.println("\nUser has been deleted succesfully!");
					break;
				}
				case "quit": {
					return;
				}
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static User getUser() {
		System.out.print("Insert username: ");
		String username = Input.get();
		System.out.print("Insert email: ");
		String email = Input.get();
		System.out.print("Insert password: ");
		String password = Input.get();
		System.out.print("Insert user group id: ");
		int userGroupId = Input.getInt();
		return new User(username, email, password, userGroupId);
	}

	static long getUserId() {
		System.out.print("Insert user id: ");
		return Input.getLong();
	}

}
