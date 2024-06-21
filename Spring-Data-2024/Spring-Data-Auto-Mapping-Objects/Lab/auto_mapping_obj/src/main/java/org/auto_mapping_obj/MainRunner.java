package org.auto_mapping_obj;

import org.auto_mapping_obj.models.dto.EmployeeInfoDto;
import org.auto_mapping_obj.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MainRunner implements CommandLineRunner {
    private EmployeeService employeeService;

    public MainRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
//        Address_ address = new Address_("UK", "London", "199");
//        Employee employee = new Employee("Jack", "Jackson", BigDecimal.TEN, LocalDate.now()address);
//        BasicEmployeeDto employeeDto = modelMapper.map(employee, BasicEmployeeDto.class);

        List<EmployeeInfoDto> infoForBornBefore = employeeService.findInfoForBornBefore(LocalDate.of(1990, 01, 01));
        System.out.println();

    }
}
