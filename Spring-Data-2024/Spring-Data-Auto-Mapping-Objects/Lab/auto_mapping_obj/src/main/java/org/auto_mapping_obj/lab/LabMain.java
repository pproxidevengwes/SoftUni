package org.auto_mapping_obj.lab;

import org.auto_mapping_obj.lab.models.Employee;
import org.auto_mapping_obj.lab.models.EmployeeDto;
import org.auto_mapping_obj.lab.models.ManagerDto;
import org.auto_mapping_obj.lab.models.WorkStatus;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LabMain {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        Employee manager = new Employee("Tom", "Jason", BigDecimal.TEN, LocalDate.now(), "Costa Rica",
                WorkStatus.PRESENT, null, List.of());
        Employee employee = new Employee("Lexy", "Bravo", BigDecimal.ONE, LocalDate.now(), "Sofia",
                WorkStatus.PAID_TIME_OFF, manager, List.of());
        manager.setEmployees(List.of(employee));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        ManagerDto managrDto=modelMapper.map(manager, ManagerDto.class);
        System.out.println(employeeDto);
        System.out.println(managrDto);
    }
}
