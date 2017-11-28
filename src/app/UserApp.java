package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import model.User;

public class UserApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn(); Scanner scan = new Scanner(System.in)) {
			while (true) {
				System.out.println("List of users (id, username, email, userGroupId):");
				printAllUsers(conn);
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (scan.next()) {
				case "add": {
					System.out.println("\nAdd user menu");
					User u = getUserData(scan);
					u.save(conn);
					System.out.println("\nUser has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit user menu");
					User u = getUserById(conn, scan);
					u.copy(getUserData(scan));
					u.save(conn);
					System.out.println("\nUser has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete user menu");
					User u = getUserById(conn, scan);
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

	private static User getUserById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert user id: ");
		long id = scan.nextLong();
		return User.loadById(conn, id);
	}

	private static User getUserData(Scanner scan) {
		System.out.print("Insert username: ");
		String username = scan.next();
		System.out.print("Insert email: ");
		String email = scan.next();
		System.out.print("Insert password: ");
		String password = scan.next();
		System.out.print("Insert user group id: ");
		int userGroupId = scan.nextInt();

		return new User(username, email, password, userGroupId);
	}

	private static void printAllUsers(Connection conn) throws SQLException {
		for (User u : User.loadAll(conn)) {
			System.out.println(u.getId() + " | " + u.getUsername() + " | " + u.getEmail() + " | " + u.getUserGroupId());
		}
	}

}
