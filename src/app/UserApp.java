package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import model.User;

public class UserApp {
	public static void main(String[] args) {
		try (Connection conn = getConnection(); Scanner scan = new Scanner(System.in)) {
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
					System.out.print("Insert id of user that you want to edit: ");
					long id = scan.nextLong();
					User u = User.loadById(conn, id);
					u.copy(getUserData(scan));
					u.save(conn);
					System.out.println("\nUser has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete user menu");
					System.out.print("Insert id of user that you want to delete: ");
					long id = scan.nextLong();
					User u = User.loadById(conn, id);
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

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztat2?useSSL=false", "root", "coderslab");
	}
}
