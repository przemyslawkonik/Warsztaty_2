package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import model.Excercise;

public class ExcerciseApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn(); Scanner scan = new Scanner(System.in)) {
			while (true) {
				System.out.println("List of excercises (id, title, description):");
				printAllExcercises(conn);
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (scan.next()) {
				case "add": {
					System.out.println("\nAdd excercise menu");
					Excercise e = getExcerciseData(scan);
					e.save(conn);
					System.out.println("\nExcercise has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit excercise menu");
					Excercise e = getExcerciseById(conn, scan);
					e.copy(getExcerciseData(scan));
					e.save(conn);
					System.out.println("\nExcercise has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete excercise menu");
					Excercise e = getExcerciseById(conn, scan);
					e.delete(conn);
					System.out.println("\nExcercise has been deleted succesfully!");
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

	private static Excercise getExcerciseById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert excercise id: ");
		int id = scan.nextInt();
		return Excercise.loadById(conn, id);
	}

	private static Excercise getExcerciseData(Scanner scan) {
		System.out.print("Insert title: ");
		String title = scan.next();
		System.out.print("Insert description: ");
		String description = scan.next();

		return new Excercise(title, description);
	}

	private static void printAllExcercises(Connection conn) throws SQLException {
		for (Excercise e : Excercise.loadAll(conn)) {
			System.out.println(e.getId() + " | " + e.getTitle() + " | " + e.getDescription());
		}
	}

}
