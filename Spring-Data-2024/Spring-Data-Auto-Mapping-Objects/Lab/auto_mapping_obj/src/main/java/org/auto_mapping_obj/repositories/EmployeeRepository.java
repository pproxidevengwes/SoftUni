package org.auto_mapping_obj.repositories;

import org.auto_mapping_obj.models.Employee;
import org.auto_mapping_obj.models.dto.EmployeeInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT new org.auto_mapping_obj.models.dto.EmployeeInfoDto(e.firstName, e.lastName, e.salary, e.birthday) FROM Employee e WHERE e.birthday<:before")
    List<EmployeeInfoDto> findAllByBirthdayBefore(LocalDate before);
}
