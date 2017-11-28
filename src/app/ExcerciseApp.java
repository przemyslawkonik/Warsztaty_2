package app;

import java.sql.Connection;
import java.sql.SQLException;

import db.DbUtil;
import io.Input;
import io.Output;
import model.Excercise;

public class ExcerciseApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn()) {
			while (true) {
				System.out.println("List of excercises (id, title, description):\n");
				Output.printExcercises(Excercise.loadAll(conn));
				System.out.print("\nAvaliable options (add, edit, delete, quit): ");

				switch (Input.get()) {
				case "add": {
					System.out.println("\nAdd excercise menu");
					Excercise e = Input.getExcercise();
					e.save(conn);
					System.out.println("\nExcercise has been added succesfully!");
					break;
				}
				case "edit": {
					System.out.println("\nEdit excercise menu");
					Excercise e = Excercise.loadById(conn, Input.getExcerciseId());
					e.copy(Input.getExcercise());
					e.save(conn);
					System.out.println("\nExcercise has been edited succesfully!");
					break;
				}
				case "delete": {
					System.out.println("\nDelete excercise menu");
					Excercise e = Excercise.loadById(conn, Input.getExcerciseId());
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
