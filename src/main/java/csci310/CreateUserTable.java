package csci310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sqlitetutorial.net
 */

public class CreateUserTable {
	public CreateUserTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:project.db";
        //System.out.println("Table created");
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	userID integer PRIMARY KEY,\n"
                + "	username text NOT NULL,\n"
                + "	password text NOT NULL\n"
                + ");";
        
        try{
            // create a new table
        	Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    

}
