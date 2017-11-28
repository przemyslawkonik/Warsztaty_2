package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import io.Input;
import io.Output;
import model.Solution;
import model.User;
import tool.MyDate;

public class MyApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn(); Scanner scan = new Scanner(System.in)) {
			User u = null;
			if (args.length > 0) {
				u = User.loadById(conn, Long.parseLong(args[0]));
				System.out.println("Hello " + u.getUsername());
			} else {
				System.out.println("User doesn't exists");
				return;
			}
			while (true) {
				System.out.print("\nAvaliable options (add, view, quit):");

				switch (scan.next()) {
				case "add": {
					System.out.println("\nAll of " + u.getUsername()
							+ " unresolved solutions (id, created, updated, description, excercise_id, users_id):");
					int i = Output.printAllUnresolvedSolutionByUserId(conn, u.getId());
					if (i > 0) {
						Solution s = Input.getSolutionById(conn, scan);
						String desc = Input.getSolutionDescription(scan);
						s.setDescription(desc);
						s.setUpdated(MyDate.get());
						s.save(conn);
					}
					break;
				}
				case "view": {
					System.out.println("\nAll of " + u.getUsername()
							+ " solutions (id, created, updated, description, excercise_id, users_id):");
					Output.printAllSolutionByUserId(conn, u.getId());
					break;
				}
				case "quit": {
					return;
				}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
