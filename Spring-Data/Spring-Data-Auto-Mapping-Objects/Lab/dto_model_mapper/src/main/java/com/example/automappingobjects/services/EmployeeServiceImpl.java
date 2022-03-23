package service;

import entities.Employee;
import entities.dto.EmployeeSpringDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> findOneById(Long id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeSpringDTO> findEmployeeBornBefore(int year) {
        LocalDate beforeYear = LocalDate.of(year, 01, 01);

        return employeeRepository.findByBirthdayIsBeforeOrderBySalaryDesc(beforeYear);
    }
}
