package ch.ffhs.hdo.persistence.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase extends JdbcHelper {

	public void createDatabase() {

		try {

			/**
			 * Create Table
			 */
			update("CREATE SEQUENCE priority_sequence");
			update("CREATE TABLE config ( id INTEGER IDENTITY, key VARCHAR(256), value VARCHAR(2500), creationDate TIMESTAMP, changedate TIMESTAMP  )");
			update("CREATE TABLE ruleset ( id INTEGER IDENTITY,  targetDirectory VARCHAR(2500), rulesetName VARCHAR(500), newFilename VARCHAR(500), filenameCounter BIGINT, priority INTEGER, active BOOLEAN DEFAULT FALSE NOT NULL, creationDate TIMESTAMP, changedate TIMESTAMP  )");
			update("CREATE TABLE rule ( id INTEGER IDENTITY, rulesetId INTEGER, contextType VARCHAR(250),contextAttribute VARCHAR(250),  compareType VARCHAR(250), compareValue VARCHAR(250), creationDate TIMESTAMP, changedate TIMESTAMP  )");

			update("CREATE TRIGGER trigg BEFORE INSERT ON ruleset REFERENCING NEW ROW AS newrow FOR EACH ROW SET newrow.priority = NEXT VALUE FOR priority_sequence");

			/**
			 * Grant Permission
			 */
			update("grant all on rule to PUBLIC");
			update("grant all on ruleset to PUBLIC");
			update("grant all on config to PUBLIC");
			/**
			 * Insert Data
			 */

			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('auto_modus','true',CURTIME (),CURTIME ())");
			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('intervall','3600',CURTIME (),CURTIME ())");

		} catch (SQLException ex2) {

			// ignore should throw execption since table
			// already there this will have no effect on the db
		}

	}

	public synchronized void update(String expression) throws SQLException {
		System.out.println(expression);
		Statement st = null;

		st = conn.createStatement(); // statements

		int i = st.executeUpdate(expression); // run the query

		if (i == -1) {
			System.out.println("db error : " + expression);
		}

		st.close();
	}

}
