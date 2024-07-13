package org.auto_mapping_obj.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeInfoDto {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthday;

    public EmployeeInfoDto(){}

    public EmployeeInfoDto(String firstName, String lastName, BigDecimal salary, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInfoDto that = (EmployeeInfoDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(salary, that.salary) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary, birthday);
    }
}
