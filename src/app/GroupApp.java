package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import model.Group;

public class GroupApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn(); Scanner scan = new Scanner(System.in)) {
			while (true) {
				System.out.println("List of groups (id, name):");
				printAllGroups(conn);
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (scan.next()) {
				case "add": {
					System.out.println("\nAdd group menu");
					Group g = getGroupData(scan);
					g.save(conn);
					System.out.println("\nGroup has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit group menu");
					Group g = getGroupById(conn, scan);
					g.copy(getGroupData(scan));
					g.save(conn);
					System.out.println("\nGroup has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete group menu");
					Group g = getGroupById(conn, scan);
					g.delete(conn);
					System.out.println("\nGroup has been deleted succesfully!");
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

	private static Group getGroupById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert group id: ");
		int id = scan.nextInt();
		return Group.loadById(conn, id);
	}

	private static Group getGroupData(Scanner scan) {
		System.out.print("Insert name: ");
		String name = scan.next();

		return new Group(name);
	}

	private static void printAllGroups(Connection conn) throws SQLException {
		for (Group g : Group.loadAll(conn)) {
			System.out.println(g.getId() + " | " + g.getName());
		}
	}

}
