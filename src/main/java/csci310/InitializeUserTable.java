package csci310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitializeUserTable {
	public InitializeUserTable() {
		String url = "jdbc:sqlite:project.db";
        System.out.println("Table initialized");
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(url);
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "trojan");
            pstmt.setString(2, "12345Qa");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

}
