package csci310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropUserTable {
	public DropUserTable() {
		String url = "jdbc:sqlite:project.db";
        System.out.println("Table droped");
        // SQL statement for creating a new table
        String sql = "DROP TABLE users;";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
	}

}
