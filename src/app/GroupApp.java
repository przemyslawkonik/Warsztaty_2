package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import io.Input;
import io.Output;
import model.Group;

public class GroupApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn(); Scanner scan = new Scanner(System.in)) {
			while (true) {
				System.out.println("List of groups (id, name):");
				Output.printAllGroups(conn);
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (scan.next()) {
				case "add": {
					System.out.println("\nAdd group menu");
					Group g = Input.getGroup(scan);
					g.save(conn);
					System.out.println("\nGroup has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit group menu");
					Group g = Input.getGroupById(conn, scan);
					g.copy(Input.getGroup(scan));
					g.save(conn);
					System.out.println("\nGroup has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete group menu");
					Group g = Input.getGroupById(conn, scan);
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

}
