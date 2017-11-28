package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import db.DbUtil;
import model.Excercise;
import model.Solution;
import model.User;
import tool.MyDate;

public class MyApp {
	public static void main(String[] args) {
		try (Connection conn = getConnection(); Scanner scan = new Scanner(System.in)) {
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
					int i = printAllUnresolvedSolutionByUserId(conn, u.getId());
					if (i > 0) {
						Solution s = getSolutionById(conn, scan);
						String desc = getSolutionDescription(scan);
						s.setDescription(desc);
						s.setUpdated(MyDate.get());
						s.save(conn);
					}
					break;
				}
				case "view": {
					System.out.println("\nAll of " + u.getUsername()
							+ " solutions (id, created, updated, description, excercise_id, users_id):");
					printAllSolutionByUserId(conn, u.getId());
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

	private static void printAllSolutionByUserId(Connection conn, long id) throws SQLException {
		for (Solution s : Solution.loadAllByUserId(conn, id)) {
			System.out.println(s.getId() + " | " + s.getCreated() + " | " + s.getUpdated() + " | " + s.getDescription()
					+ " | " + s.getExcerciseId() + " | " + s.getUserId());
		}
	}

	private static int printAllUnresolvedSolutionByUserId(Connection conn, long id) throws SQLException {
		int counter = 0;
		for (Solution s : Solution.loadAllByUserId(conn, id)) {
			if (s.getDescription() == null) {
				System.out.println(s.getId() + " | " + s.getCreated() + " | " + s.getUpdated() + " | "
						+ s.getDescription() + " | " + s.getExcerciseId() + " | " + s.getUserId());
				counter++;
			}
		}
		return counter;
	}

	private static Solution getSolutionById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert solution id: ");
		int id = scan.nextInt();
		return Solution.loadById(conn, id);
	}

	private static String getSolutionDescription(Scanner scan) {
		System.out.print("Insert your solution: ");
		String desc = scan.next();
		return desc;
	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztat2?useSSL=false", "root", "coderslab");
	}
}
