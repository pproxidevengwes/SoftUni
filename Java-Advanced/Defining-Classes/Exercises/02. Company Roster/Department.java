import java.util.ArrayList;
import java.util.List;

public class Department {
    List<Employee> employees;
    String name;

    public Department() {
        this.employees = new ArrayList<>();;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public double getAvarageSalary() {
        double avg = 0.0;
        for (Employee employee : this.employees) {
            avg += employee.getSalary();
        }
        return avg / this.employees.size();
    }
}
