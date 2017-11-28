package app;

import java.sql.Connection;
import java.sql.SQLException;

import db.DbUtil;
import io.Input;
import io.Output;
import model.Group;

public class GroupApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn()) {
			while (true) {
				System.out.println("List of groups (id, name):\n");
				Output.printGroups(Group.loadAll(conn));
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (Input.get()) {
				case "add": {
					System.out.println("\nAdd group menu");
					Group g = getGroup();
					g.save(conn);
					System.out.println("\nGroup has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit group menu");
					Group g = Group.loadById(conn, getGroupId());
					g.copy(getGroup());
					g.save(conn);
					System.out.println("\nGroup has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete group menu");
					Group g = Group.loadById(conn, getGroupId());
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

	static Group getGroup() {
		System.out.print("Insert name: ");
		return new Group(Input.get());
	}

	static int getGroupId() {
		System.out.print("Insert group id: ");
		return Input.getInt();
	}

}
