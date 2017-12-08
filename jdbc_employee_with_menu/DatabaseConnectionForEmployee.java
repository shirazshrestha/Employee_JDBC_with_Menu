package jdbc_employee_with_menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnectionForEmployee {

    /** Database name -> demo
     *  Table name -> employee
     * **/

    final String url = "jdbc:mysql://localhost:3306/demo";
    final String username = "root";
    final String password = "";

    Connection connection = null;

    public DatabaseConnectionForEmployee() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connection Successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String query) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
