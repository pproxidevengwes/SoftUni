package org.auto_mapping_obj.lab.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDto {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthday;

    public EmployeeDto() {
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", firstName, lastName, salary);
    }
}
