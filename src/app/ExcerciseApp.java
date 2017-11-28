package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import io.Input;
import io.Output;
import model.Excercise;

public class ExcerciseApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn(); Scanner scan = new Scanner(System.in)) {
			while (true) {
				System.out.println("List of excercises (id, title, description):");
				Output.printAllExcercises(conn);
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (scan.next()) {
				case "add": {
					System.out.println("\nAdd excercise menu");
					Excercise e = Input.getExcercise(scan);
					e.save(conn);
					System.out.println("\nExcercise has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit excercise menu");
					Excercise e = Input.getExcerciseById(conn, scan);
					e.copy(Input.getExcercise(scan));
					e.save(conn);
					System.out.println("\nExcercise has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete excercise menu");
					Excercise e = Input.getExcerciseById(conn, scan);
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

}
