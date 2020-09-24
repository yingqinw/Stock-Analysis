package csci310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitializeUserTable {
	public InitializeUserTable() {
		CreateUserTable c = new CreateUserTable();
		String url = "jdbc:sqlite:project.db";
        System.out.println("Table initialized");
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";

        try {
        	Connection conn = DriverManager.getConnection(url);
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "trojan");
            pstmt.setString(2, PasswordHash.getHash("12345Qa"));
            System.out.println("Reached without sqle");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
        }
	}

}
