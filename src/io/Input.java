package io;

import java.util.Scanner;

import model.Excercise;
import model.Group;
import model.User;

public class Input {

	public static Scanner getScannerInstance() {
		return new Scanner(System.in);
	}

	public static String get() {
		return getScannerInstance().next();
	}

	public static String getLine() {
		return getScannerInstance().nextLine();
	}

	public static long getLong() {
		return getScannerInstance().nextLong();
	}

	public static int getInt() {
		return getScannerInstance().nextInt();
	}

	public static long getUserId() {
		System.out.print("Insert user id: ");
		return Input.getLong();
	}

	public static User getUser() {
		System.out.print("Insert username: ");
		String username = Input.get();
		System.out.print("Insert email: ");
		String email = Input.get();
		System.out.print("Insert password: ");
		String password = Input.get();
		System.out.print("Insert user group id: ");
		int userGroupId = Input.getInt();
		return new User(username, email, password, userGroupId);
	}

	public static int getGroupId() {
		System.out.print("Insert group id: ");
		return Input.getInt();
	}

	public static Group getGroup() {
		System.out.print("Insert name: ");
		return new Group(Input.get());
	}

	public static int getExcerciseId() {
		System.out.print("Insert excercise id: ");
		return Input.getInt();
	}

	public static Excercise getExcercise() {
		System.out.print("Insert title: ");
		String title = Input.get();
		System.out.print("Insert description: ");
		String description = Input.getLine();
		return new Excercise(title, description);
	}

	public static int getSolutionId() {
		System.out.print("Insert solution id: ");
		return Input.getInt();
	}

}
