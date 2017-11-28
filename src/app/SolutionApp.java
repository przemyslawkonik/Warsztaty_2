package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import model.Excercise;
import model.Solution;
import model.User;
import tool.MyDate;

public class SolutionApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn(); Scanner scan = new Scanner(System.in)) {
			while (true) {
				System.out.print("Avaliable options (add, view, quit): ");

				switch (scan.next()) {
				case "add": {
					System.out.println("\nAdd solution menu");
					System.out.println("\nList of users (id, username, email, userGroupId):");
					printAllUsers(conn);
					User u = getUserById(conn, scan);

					System.out.println("\nList of excercises (id, title, description):");
					printAllExcercises(conn);
					Excercise e = getExcerciseById(conn, scan);

					Solution s = new Solution(MyDate.get(), null, null, e.getId(), u.getId());
					s.save(conn);
					System.out.println("\nSolution has been added succesfully!");
					break;
				}
				case "view": {
					System.out.println("\nView solution menu");
					System.out.println("\nList of users (id, username, email, userGroupId):");
					printAllUsers(conn);
					User u = getUserById(conn, scan);
					System.out.println("\nAll of " + u.getUsername()
							+ " solutions (id, created, updated, description, excercise_id, users_id):");
					printAllSolutionByUserId(conn, u.getId());
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

	private static Excercise getExcerciseById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert excercise id: ");
		int id = scan.nextInt();
		return Excercise.loadById(conn, id);
	}

	private static void printAllUsers(Connection conn) throws SQLException {
		for (User u : User.loadAll(conn)) {
			System.out.println(u.getId() + " | " + u.getUsername() + " | " + u.getEmail() + " | " + u.getUserGroupId());
		}
	}

	private static void printAllExcercises(Connection conn) throws SQLException {
		for (Excercise e : Excercise.loadAll(conn)) {
			System.out.println(e.getId() + " | " + e.getTitle() + " | " + e.getDescription());
		}
	}

	private static void printAllSolutionByUserId(Connection conn, long id) throws SQLException {
		for (Solution s : Solution.loadAllByUserId(conn, id)) {
			System.out.println(s.getId() + " | " + s.getCreated() + " | " + s.getUpdated() + " | " + s.getDescription()
					+ " | " + s.getExcerciseId() + " | " + s.getUserId());
		}
	}

}
