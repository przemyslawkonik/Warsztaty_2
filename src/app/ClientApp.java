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

public class ClientApp {
	public static void main(String[] args) {
		try (Connection conn = DbUtil.getConn()) {
			User u = checkUser(args, conn);
			if (u != null) {
				System.out.println("Witaj " + u.getUsername());
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
						Solution s = Solution.loadById(conn, SolutionApp.getSolutionId());
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

	private static User checkUser(String[] args, Connection conn) throws SQLException {
		if (args.length > 0) {
			return User.loadById(conn, Long.parseLong(args[0]));
		}
		return null;
	}

}
