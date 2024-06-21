package org.auto_mapping_obj.services;

import org.auto_mapping_obj.models.dto.EmployeeInfoDto;
import org.auto_mapping_obj.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeInfoDto> findInfoForBornBefore(LocalDate before) {
        return employeeRepository.findAllByBirthdayBefore(before);
    }

}
