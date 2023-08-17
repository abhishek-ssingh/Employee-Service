package com.acro.employeeservice.service;

import com.acro.employeeservice.dto.APIResponseDto;
import com.acro.employeeservice.dto.EmployeeDto;
import com.acro.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    EmployeeDto getByEmail(String email);
    APIResponseDto getById(Long id);

}
