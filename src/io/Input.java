package io;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import model.Excercise;
import model.Group;
import model.Solution;
import model.User;

public class Input {

	public static User getUserById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert user id: ");
		long id = scan.nextLong();
		return User.loadById(conn, id);
	}

	public static User getUser(Scanner scan) {
		System.out.print("Insert username: ");
		String username = scan.next();
		System.out.print("Insert email: ");
		String email = scan.next();
		System.out.print("Insert password: ");
		String password = scan.next();
		System.out.print("Insert user group id: ");
		int userGroupId = scan.nextInt();

		return new User(username, email, password, userGroupId);
	}

	public static Group getGroupById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert group id: ");
		int id = scan.nextInt();
		return Group.loadById(conn, id);
	}

	public static Group getGroup(Scanner scan) {
		System.out.print("Insert name: ");
		String name = scan.next();

		return new Group(name);
	}

	public static Excercise getExcerciseById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert excercise id: ");
		int id = scan.nextInt();
		return Excercise.loadById(conn, id);
	}

	public static Excercise getExcercise(Scanner scan) {
		System.out.print("Insert title: ");
		String title = scan.next();
		System.out.print("Insert description: ");
		String description = scan.next();

		return new Excercise(title, description);
	}

	public static Solution getSolutionById(Connection conn, Scanner scan) throws SQLException {
		System.out.print("Insert solution id: ");
		int id = scan.nextInt();
		return Solution.loadById(conn, id);
	}

	public static String getSolutionDescription(Scanner scan) {
		System.out.print("Insert your solution: ");
		String desc = scan.next();
		return desc;
	}
}
