package jdbc_employee_with_menu;

public class Employee {
    int id;
    String name, address, email;

    public Employee() { /** default constructor **/
    }

    public Employee(int id, String name, String address, String email) { /** parameterized constructor **/
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public int getId() { //getter
        return id;
    }

    public void setId(int id) { //setter
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
