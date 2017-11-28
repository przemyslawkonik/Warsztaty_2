package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import io.Input;
import io.Output;
import model.User;

public class UserApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn(); Scanner scan = new Scanner(System.in)) {
			while (true) {
				System.out.println("List of users (id, username, email, userGroupId):");
				Output.printAllUsers(conn);
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (scan.next()) {
				case "add": {
					System.out.println("\nAdd user menu");
					User u = Input.getUser(scan);
					u.save(conn);
					System.out.println("\nUser has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit user menu");
					User u = Input.getUserById(conn, scan);
					u.copy(Input.getUser(scan));
					u.save(conn);
					System.out.println("\nUser has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete user menu");
					User u = Input.getUserById(conn, scan);
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

}
