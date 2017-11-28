package io;

import java.util.List;

import model.Excercise;
import model.Group;
import model.Solution;
import model.User;

public class Output {
	public static String separator = " | ";

	public static void printUsers(List<User> users) {
		for (User u : users) {
			System.out.println(u.getId() + separator + u.getUsername() + separator + u.getEmail() + separator + u.getUserGroupId());
		}
	}

	public static void printGroups(List<Group> groups) {
		for (Group g : groups) {
			System.out.println(g.getId() + separator + g.getName());
		}
	}

	public static void printExcercises(List<Excercise> excercises) {
		for (Excercise e : excercises) {
			System.out.println(e.getId() + separator + e.getTitle() + separator + e.getDescription());
		}
	}

	public static void printSolutions(List<Solution> solutions) {
		for (Solution s : solutions) {
			System.out.println(s.getId() + separator + s.getCreated() + separator + s.getUpdated() + separator + s.getDescription()
					+ separator + s.getExcerciseId() + separator + s.getUserId());
		}
	}

	public static String getSeparator() {
		return separator;
	}

	public static void setSeparator(String separator) {
		Output.separator = separator;
	}

}
