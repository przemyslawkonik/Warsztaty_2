package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DbUtil;
import io.Input;
import io.Output;
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
					Output.printAllUsers(conn);
					User u = Input.getUserById(conn, scan);

					System.out.println("\nList of excercises (id, title, description):");
					Output.printAllExcercises(conn);
					Excercise e = Input.getExcerciseById(conn, scan);

					Solution s = new Solution(MyDate.get(), null, null, e.getId(), u.getId());
					s.save(conn);
					System.out.println("\nSolution has been added succesfully!");
					break;
				}
				case "view": {
					System.out.println("\nView solution menu");
					System.out.println("\nList of users (id, username, email, userGroupId):");
					Output.printAllUsers(conn);
					User u = Input.getUserById(conn, scan);
					System.out.println("\nAll of " + u.getUsername()
							+ " solutions (id, created, updated, description, excercise_id, users_id):");
					Output.printAllSolutionByUserId(conn, u.getId());
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
