package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/otp_auth";
    private static final String USER = "root";
    private static final String PASSWORD = "2782004";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối cơ sở dữ liệu thất bại.");
        }
        return connection;
    }
//    public static void main(String[] args) {
//        try (Connection conn = getConnection()) {
//            if (conn != null) {
//                System.out.println("Kết nối thành công!");
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi kết nối cơ sở dữ liệu.");
//            e.printStackTrace();
//        }
//    }
}
