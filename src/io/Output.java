package io;

import java.sql.Connection;
import java.sql.SQLException;

import model.Excercise;
import model.Group;
import model.Solution;
import model.User;

public class Output {

	public static void printAllUsers(Connection conn) throws SQLException {
		for (User u : User.loadAll(conn)) {
			System.out.println(u.getId() + " | " + u.getUsername() + " | " + u.getEmail() + " | " + u.getUserGroupId());
		}
	}

	public static void printAllGroups(Connection conn) throws SQLException {
		for (Group g : Group.loadAll(conn)) {
			System.out.println(g.getId() + " | " + g.getName());
		}
	}

	public static void printAllExcercises(Connection conn) throws SQLException {
		for (Excercise e : Excercise.loadAll(conn)) {
			System.out.println(e.getId() + " | " + e.getTitle() + " | " + e.getDescription());
		}
	}

	public static void printAllSolutionByUserId(Connection conn, long id) throws SQLException {
		for (Solution s : Solution.loadAllByUserId(conn, id)) {
			System.out.println(s.getId() + " | " + s.getCreated() + " | " + s.getUpdated() + " | " + s.getDescription()
					+ " | " + s.getExcerciseId() + " | " + s.getUserId());
		}
	}

	public static int printAllUnresolvedSolutionByUserId(Connection conn, long id) throws SQLException {
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
}
