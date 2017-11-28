package io;

import java.util.List;

import model.Excercise;
import model.Group;
import model.Solution;
import model.User;

public class Output {

	public static void printUsers(List<User> users) {
		for (User u : users) {
			System.out.println(u.getId() + " | " + u.getUsername() + " | " + u.getEmail() + " | " + u.getUserGroupId());
		}
	}

	public static void printGroups(List<Group> groups) {
		for (Group g : groups) {
			System.out.println(g.getId() + " | " + g.getName());
		}
	}

	public static void printExcercises(List<Excercise> excercises) {
		for (Excercise e : excercises) {
			System.out.println(e.getId() + " | " + e.getTitle() + " | " + e.getDescription());
		}
	}

	public static void printSolutions(List<Solution> solutions) {
		for (Solution s : solutions) {
			System.out.println(s.getId() + " | " + s.getCreated() + " | " + s.getUpdated() + " | " + s.getDescription()
					+ " | " + s.getExcerciseId() + " | " + s.getUserId());
		}
	}

}
