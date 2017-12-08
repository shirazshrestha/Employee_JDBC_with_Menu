package jdbc_employee_with_menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeService {

    Scanner input = new Scanner(System.in);

    public void menu(){ /** menu **/
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add a New Employee");
        System.out.println("2. Delete an Existing Employee");
        System.out.println("3. Update an Existing Employee");
        System.out.println("4. Show All Employees");
        System.out.println("5. Exit");
        System.out.println("-------------------------------------------------");
        System.out.println("Enter your choice [1-5] ->");
    }

    public void menuAnswered(){
        menu();
        int choice = input.nextInt();

        switch (choice){
            case 1:
                insert(); /** call insert method **/
                menuAnswered();
                break;
            case 2:
                delete(); /** call delete method **/
                menuAnswered();
                break;
            case 3:
                update(); /** call update method **/
                menuAnswered();
                break;
            case 4:
                select(); /** call select method **/
                menuAnswered();
                break;
            case 5:
                System.out.println("Thank you for using our system.");
                System.exit(0);
                break;
        }
    }

    public Employee getInformation() { /** method to get information from the user **/
        Employee employee = new Employee();
        System.out.println("Enter name ->");
        employee.setName(input.next());
        System.out.println("Enter address ->");
        employee.setAddress(input.next());
        System.out.println("Enter email ->");
        employee.setEmail(input.next());

        return employee;
    }

    public void insert() { /** method to add a employee in database **/
        Employee employee = getInformation();
        String query = "insert into employee (name, address, email) values (?, ?, ?)";
        DatabaseConnectionForEmployee databaseConnectionForEmployee = new DatabaseConnectionForEmployee();
        PreparedStatement preparedStatement = databaseConnectionForEmployee.getPreparedStatement(query);

        try {
            preparedStatement.setString(1, employee.name);
            preparedStatement.setString(2, employee.address);
            preparedStatement.setString(3, employee.email);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Employee Successfully Inserted.");
    }

    public void delete() { /** method to delete an employee from the database**/
        System.out.println("Enter id of the employee -> ");
        int idToDelete = input.nextInt();
        String query = "delete from employee where id = ?";
        DatabaseConnectionForEmployee databaseConnectionForEmployee = new DatabaseConnectionForEmployee();
        PreparedStatement preparedStatement = databaseConnectionForEmployee.getPreparedStatement(query);

        try {
            preparedStatement.setInt(1, idToDelete);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Employee with id " + idToDelete + " Successfully Deleted.");
    }

    public void update() { /** method to update an existing employee in the database **/
        System.out.println("Enter id of the employee ->");
        int idToUpdate = input.nextInt();
        System.out.println("Enter address of the employee ->");
        String addressToUpdate = input.next();
        String query = "update employee set address=? where id=?";

        DatabaseConnectionForEmployee databaseConnectionForEmployee = new DatabaseConnectionForEmployee();
        PreparedStatement preparedStatement = databaseConnectionForEmployee.getPreparedStatement(query);

        try {
            preparedStatement.setString(1, addressToUpdate);
            preparedStatement.setInt(2, idToUpdate);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Employee with id " + idToUpdate + " successfully updated.");
    }

    public void select() { /** method to show all the employees from the database **/
        String query = "select * from employee";
        DatabaseConnectionForEmployee databaseConnectionForEmployee = new DatabaseConnectionForEmployee();
        PreparedStatement preparedStatement = databaseConnectionForEmployee.getPreparedStatement(query);

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Id -> " + resultSet.getInt("id"));
                System.out.println("Name -> " + resultSet.getString("name"));
                System.out.println("Address ->" + resultSet.getString("address"));
                System.out.println("Email -> " + resultSet.getString("email"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
