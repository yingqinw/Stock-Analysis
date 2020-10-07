package csci310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropStockTable {
	public DropStockTable() {
		String url = "jdbc:sqlite:project.db";
        System.out.println("Table droped");
        // SQL statement for creating a new table
        String sql = "DROP TABLE IF EXISTS stocks;";
        
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
			
