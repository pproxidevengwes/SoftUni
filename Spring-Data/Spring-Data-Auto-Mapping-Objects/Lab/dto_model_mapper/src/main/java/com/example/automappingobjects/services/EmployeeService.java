package service;

import entities.Employee;
import entities.dto.EmployeeSpringDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findOneById(Long id);
    void save (Employee employee);

    List<EmployeeSpringDTO> findEmployeeBornBefore(int year);
}
