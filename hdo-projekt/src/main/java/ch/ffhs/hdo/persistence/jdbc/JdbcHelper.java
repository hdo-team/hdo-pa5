package ch.ffhs.hdo.persistence.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.ffhs.hdo.infrastructure.ApplicationSettings;

public class JdbcHelper {

	protected Connection conn; // our connnection to the db - presist for life
								// of program

	public JdbcHelper() {

		String inbox_path = ApplicationSettings.getInstance().getInbox_path();

		try {
			Class.forName("org.hsqldb.jdbcDriver");

			conn = DriverManager.getConnection(
					"jdbc:hsqldb:file:" + inbox_path + File.separator + ".db" + File.separator + "hdo_db", // filenames
					"sa", // username
					""); // password
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void terminate() throws SQLException {

		Statement st = conn.createStatement();

		// db writes out to files and performs clean shuts down
		// otherwise there will be an unclean shutdown
		// when program ends
		st.execute("SHUTDOWN");
		conn.close(); // if there are no other open connection
	}

}
