package ch.ffhs.hdo.persistence.jdbc;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.client.ui.regelset.RegelModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class InitDatabase extends JdbcHelper {

	public InitDatabase() {

		try {

			/**
			 * Create Table
			 */
			update("CREATE TABLE config ( id INTEGER IDENTITY, key VARCHAR(256), value VARCHAR(2500), creationDate TIMESTAMP, changedate TIMESTAMP  )");
			update("CREATE TABLE ruleset ( id INTEGER IDENTITY, targetDirectory VARCHAR(2500), rulesetName VARCHAR(500), newFilename VARCHAR(500), filenameCounter BIGINT, priority INTEGER, active BOOLEAN DEFAULT FALSE NOT NULL, creationDate TIMESTAMP, changedate TIMESTAMP  )");
			update("CREATE TABLE rule ( id INTEGER IDENTITY, rulesetId INTEGER, contextType VARCHAR(250),contextAttribute VARCHAR(250),  compareType VARCHAR(250), compareValue VARCHAR(250), creationDate TIMESTAMP, changedate TIMESTAMP  )");

			/**
			 * Grant Permission
			 */
			update("grant all on config to PUBLIC");
			update("grant all on rule to PUBLIC");
			update("grant all on ruleset to PUBLIC");

			/**
			 * Insert Data
			 */

			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('auto_modus','true',CURTIME (),CURTIME ())");
			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('intervall','3600',CURTIME (),CURTIME ())");
			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('inbox_path','',CURTIME (),CURTIME ())");

			// TODO: Testdaten - in definitiver Version entfernen
			update("INSERT INTO RULESET (targetDirectory, rulesetName, newFilename, filenameCounter, priority, active, creationDate, changedate) VALUES ('test-dir1', 'test1', 'test-file1', 91, 1, true, CURTIME (),CURTIME ())");
			update("INSERT INTO RULESET (targetDirectory, rulesetName, newFilename, filenameCounter, priority, active, creationDate, changedate) VALUES ('test-dir2', 'test2', 'test-file2', 92, 2, true, CURTIME (),CURTIME ())");
			update("INSERT INTO RULESET (targetDirectory, rulesetName, newFilename, filenameCounter, priority, active, creationDate, changedate) VALUES ('test-dir3', 'test3', 'test-file3', 93, 3, false, CURTIME (),CURTIME ())");
			update("INSERT INTO RULESET (targetDirectory, rulesetName, newFilename, filenameCounter, priority, active, creationDate, changedate) VALUES ('test-dir4', 'test4', 'test-file4', 94, 4, true, CURTIME (),CURTIME ())");

			// TODO: Testdaten - in definitiver Version entfernen
			update("INSERT INTO RULE (rulesetId, contextType, contextAttribute,  compareType, compareValue, creationDate, changedate) VALUES (0, 'CONTEXT_PDF', 'PDF_CREATION_DATE',  'COMPARISON_UNEQUAL', '2016-12-08', CURTIME (), CURTIME ())");
			update("INSERT INTO RULE (rulesetId, contextType, contextAttribute,  compareType, compareValue, creationDate, changedate) VALUES (1, 'CONTEXT_FILE', 'FILE_SIZE',  'COMPARISON_GREATER_EQUAL', 500, CURTIME (), CURTIME ())");
			update("INSERT INTO RULE (rulesetId, contextType, contextAttribute,  compareType, compareValue, creationDate, changedate) VALUES (1, 'CONTEXT_FILE', 'FILE_SIZE',  'COMPARISON_LESS_EQUAL', 876, CURTIME (), CURTIME ())");
			update("INSERT INTO RULE (rulesetId, contextType, contextAttribute,  compareType, compareValue, creationDate, changedate) VALUES (2, 'CONTEXT_CONTENT', 'EMPTY',  'COMPARISON_EQUAL', 'Rechnung', CURTIME (), CURTIME ())");
			update("INSERT INTO RULE (rulesetId, contextType, contextAttribute,  compareType, compareValue, creationDate, changedate) VALUES (3, 'CONTEXT_PDF', 'PDF_TITLE', 'COMPARISON_EQUAL', 'irgendein PDF-Titel', CURTIME (), CURTIME ())");
			
		} catch (SQLException ex2) {

			// ignore should throw execption since table
			// already there this will have no effect on the db
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
