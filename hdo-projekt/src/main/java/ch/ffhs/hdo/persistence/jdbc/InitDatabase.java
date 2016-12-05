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

			update("CREATE TABLE config ( id INTEGER IDENTITY, key VARCHAR(256), value VARCHAR(2500), creationDate TIMESTAMP, changedate TIMESTAMP  )");

			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('auto_modus','true',CURTIME (),CURTIME ())");
			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('intervall','3600',CURTIME (),CURTIME ())");
			update("INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES ('inbox_path','',CURTIME (),CURTIME ())");
			
			
			update("CREATE TABLE ruleset ( id INTEGER IDENTITY, targetDirectory VARCHAR(2500), rulesetName VARCHAR(500), newFilename VARCHAR(500), filenameCounter BIGINT, priority INTEGER, active BOOLEAN DEFAULT FALSE NOT NULL, creationDate TIMESTAMP, changedate TIMESTAMP  )");
			update("CREATE TABLE rule ( id INTEGER IDENTITY, rulesetId INTEGER, contextType VARCHAR(250),contextAttribute VARCHAR(250),  compareType VARCHAR(250), compareValue VARCHAR(250), creationDate TIMESTAMP, changedate TIMESTAMP  )");
			//update("INSERT INTO RULESET (targetDirectory, rulesetName,newFilename,filenameCounter, priority, active, creationDate, changedate) VALUES ('test', 'test', 'test', 1, 1, true, CURTIME (),CURTIME ())");
			
			
			
			//****** TEMP			
			//					TODO: in definitiver Version entfernen!

			for (int i = 0; i < 10; i++) {
				String newFilename;
				if (i % 2 == 0)
					newFilename = null;
				else
					newFilename ="[Foto|Bild]-" + i + ".jpg";
	
				System.out.println("ruleset: " + "INSERT INTO RULESET (targetDirectory, rulesetName,newFilename,filenameCounter, priority, active, creationDate, changedate)" +
						" VALUES ('" + "Regelset " + i +"', '" +  "Rechnungen_\\dddd_-" + i + ".pdf" + "', '" + newFilename 
						+ "', " + 990 + i + ", " + i + ", " + (i%2==0) + ", CURTIME (),CURTIME ())"); 
				update("INSERT INTO RULESET (targetDirectory, rulesetName,newFilename,filenameCounter, priority, active, creationDate, changedate)" +
						" VALUES ('" + "Regelset " + i +"', '" +  "Rechnungen_\\dddd_-" + i + ".pdf" + "', '" + newFilename 
						+ "', " + 990 + i + ", " + i + ", " + (i%2==0) + ", CURTIME (),CURTIME ())"     );
System.out.println("INSERT INTO RULE (rulesetId, contextType,contextAttribute,compareType, compareValue, creationDate, changedate)" +
					" VALUES (" + i + ", 'CONTEXT_PDF' ,'PDF_SIZE', 'COMPARISON_GREATER_EQUAL', '20161018', CURTIME (),CURTIME ())"  );
				update("INSERT INTO RULE (rulesetId, contextType,contextAttribute,compareType, compareValue, creationDate, changedate)" +
					" VALUES (" + i + ", 'CONTEXT_PDF' ,'PDF_SIZE', 'COMPARISON_GREATER_EQUAL', '20161018', CURTIME (),CURTIME ())"   );
System.out.println("INSERT INTO RULE (rulesetId, contextType,contextAttribute,compareType, compareValue, creationDate, changedate)" +
		" VALUES (" + i + ", 'CONTEXT_PDF' ,'PDF_AUTHOR', 'COMPARISON_EQUAL', 'Schreiberling', CURTIME (),CURTIME ())");
				update("INSERT INTO RULE (rulesetId, contextType,contextAttribute,compareType, compareValue, creationDate, changedate)" +
						" VALUES (" + i + ", 'CONTEXT_PDF' ,'PDF_AUTHOR', 'COMPARISON_EQUAL', 'Schreiberling', CURTIME (),CURTIME ())"   );
			}			
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
