package ch.ffhs.hdo.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ch.ffhs.hdo.infrastructure.ApplicationSettings;

public class JdbcHelper {

	protected Connection conn; // our connnection to the db - presist for life of program

	public JdbcHelper() {
		
		String inbox_path = ApplicationSettings.getInstance().getInbox_path();

		try {
			Class.forName("org.hsqldb.jdbcDriver");

			conn = DriverManager.getConnection("jdbc:hsqldb:file:" + inbox_path, // filenames
					"sa", // username
					""); // password
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	protected void terminate() {

	}

}
