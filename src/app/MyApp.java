package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.DbUtil;
import io.Input;
import io.Output;
import model.Solution;
import model.User;
import tool.MyDate;

public class MyApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn()) {
			User u = null;
			if (args.length > 0) {
				u = User.loadById(conn, Long.parseLong(args[0]));
				System.out.println("Hello " + u.getUsername());
			} else {
				System.out.println("User doesn't exists");
				return;
			}
			while (true) {
				System.out.print("\nAvaliable options (add, view, quit):");

				switch (Input.get()) {
				case "add": {
					System.out.println("\nAll of " + u.getUsername()
							+ " unresolved solutions (id, created, updated, description, excercise_id, users_id):");
					List<Solution> unresolved = Solution.loadAllUnresolvedByUserId(conn, u.getId());
					if (unresolved.size() > 0) {
						Output.printSolutions(unresolved);
						Solution s = Solution.loadById(conn, Input.getSolutionId());
						String desc = Input.getLine();
						s.setDescription(desc);
						s.setUpdated(MyDate.get());
						s.save(conn);
					}
					break;
				}
				case "view": {
					System.out.println("\nAll of " + u.getUsername()
							+ " solutions (id, created, updated, description, excercise_id, users_id):");
					Output.printSolutions(Solution.loadAllByUserId(conn, u.getId()));
					break;
				}
				case "quit": {
					return;
				}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
