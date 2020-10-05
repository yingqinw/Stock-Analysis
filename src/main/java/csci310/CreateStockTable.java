package csci310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStockTable {
	public CreateStockTable() {
		String url = "jdbc:sqlite:project.db";
		
		String sql = "CREATE TABLE IF NOT EXISTS stocks (\n"
                + "	stockID integer PRIMARY KEY,\n"
                + "	ticker text NOT NULL,\n"
                + "	dayPurchase text NOT NULL,\n"
                + "	daySold text NOT NULL,\n"
                + "	userID integer text NOT NULL,\n"
                + "	quantity integer text NOT NULL,\n"
                + " FOREIGN KEY(userID) REFERENCES users(userID)"
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

}
