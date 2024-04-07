package org.auto_mapping_obj.services;

import org.auto_mapping_obj.models.dto.EmployeeInfoDto;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<EmployeeInfoDto> findInfoForBornBefore(LocalDate before);
}
