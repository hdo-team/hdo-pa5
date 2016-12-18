package ch.ffhs.hdo.persistence.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitDatabase extends JdbcHelper {
	private static Logger LOGGER = LogManager.getLogger(InitDatabase.class);

	public InitDatabase() {

		try {

			/**
			 * Create Table
			 */
			update("CREATE SEQUENCE PUBLIC.priority_sequence");
			update("CREATE MEMORY TABLE PUBLIC.KONFIG ( id INTEGER IDENTITY, key VARCHAR(256), value VARCHAR(2500), creationDate TIMESTAMP, changedate TIMESTAMP  )");
			update("CREATE MEMORY TABLE PUBLIC.ruleset ( id INTEGER IDENTITY,  targetDirectory VARCHAR(2500), rulesetName VARCHAR(500), newFilename VARCHAR(500), filenameCounter BIGINT, priority INTEGER, active BOOLEAN DEFAULT FALSE NOT NULL, creationDate TIMESTAMP, changedate TIMESTAMP  )");
			update("CREATE MEMORY TABLE PUBLIC.rule ( id INTEGER IDENTITY, rulesetId INTEGER, contextType VARCHAR(250),contextAttribute VARCHAR(250),  compareType VARCHAR(250), compareValue VARCHAR(250), creationDate TIMESTAMP, changedate TIMESTAMP  )");

			update("CREATE TRIGGER trigg BEFORE INSERT ON ruleset REFERENCING NEW ROW AS newrow FOR EACH ROW SET newrow.priority = NEXT VALUE FOR priority_sequence");

			/**
			 * Grant Permission
			 */
			update("GRANT ALL ON TABLE PUBLIC.KONFIG TO SA");
			update("GRANT ALL ON TABLE PUBLIC.RULE TO SA");
			update("GRANT ALL ON TABLE PUBLIC.RULESET TO SA");

			/**
			 * Insert Data
			 */
			update("INSERT INTO KONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('auto_modus','true',CURTIME (),CURTIME ())");
			update("INSERT INTO KONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('intervall','3600',CURTIME (),CURTIME ())");
			update("INSERT INTO KONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('inbox_path','',CURTIME (),CURTIME ())");

		} catch (SQLException ex2) {
			LOGGER.error(ex2);
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
			LOGGER.error("db error : " + expression);

		}
		st.close();

	}

}
