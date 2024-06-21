package org.auto_mapping_obj.lab.models;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private List<EmployeeDto> employees;

    public ManagerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        String employeesStr = employees.stream().map(e -> " - " + e.toString()).collect(Collectors.joining("\n"));
        return String.format("%s %s | Employees: %d%n%s", firstName, lastName, employees.size(), employeesStr);
    }
}
