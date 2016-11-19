package ch.ffhs.hdo.persistence.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase extends JdbcHelper {

	public InitDatabase() {

		try {

			update("CREATE TABLE config ( id INTEGER IDENTITY, key VARCHAR(256), value VARCHAR(2500), creationDate TIMESTAMP, changedate TIMESTAMP  )");

			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('auto_modus','true',CURTIME (),CURTIME ())");
			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('intervall','3600',CURTIME (),CURTIME ())");
			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('inbox_path','',CURTIME (),CURTIME ())");

		} catch (SQLException ex2) {

			// ignore
			// should throw execption since table
			// already there
			// this will have no effect on the db
		}

	}

	public synchronized void update(String expression) throws SQLException {

		Statement st = null;

		st = conn.createStatement(); // statements

		int i = st.executeUpdate(expression); // run the query

		if (i == -1) {
			System.out.println("db error : " + expression);
		}

		st.close();
	}

}
