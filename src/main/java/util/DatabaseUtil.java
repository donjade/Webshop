package util;

import java.sql.*;

public class DatabaseUtil {
    private static String ip = "@localhost";
    private static int port = 1521;
    private static String loginname = "webshop_admin";
    private static String password = "12345";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:oracle:thin:%s:%d:XE", ip, port);
        return DriverManager.getConnection(url, loginname, password);
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
//        Connection connection = getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from category");
//        while (resultSet.next()) {
//            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
//        }
//        connection.close();
    }
}
