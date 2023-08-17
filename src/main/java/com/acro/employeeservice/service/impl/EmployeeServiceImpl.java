package com.acro.employeeservice.service.impl;

import com.acro.employeeservice.dto.APIResponseDto;
import com.acro.employeeservice.dto.DepartmentDto;
import com.acro.employeeservice.dto.EmployeeDto;
import com.acro.employeeservice.entity.Employee;
import com.acro.employeeservice.exception.ResourceNotFoundException;
import com.acro.employeeservice.repository.EmployeeRepository;
import com.acro.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee savedEmployee = employeeRepository.save(employee);
        //maps the employee to dto
        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getByEmail(String email) {
        Employee getEmployee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "email", email)
        );

        EmployeeDto employeeDto = modelMapper.map(getEmployee, EmployeeDto.class);

        return employeeDto;
    }

    @Override
    public APIResponseDto getById(Long id) {

//        Employee getEmployee = employeeRepository.findById(id).get().orElseThrow(
//                () -> new ResourceNotFoundException("Employee", "id", Long.toString(id))
//        );

        Employee getEmployee = employeeRepository.findById(id).get();

//        restTemplate.getForEntity("http://localhost:8080/departments/" + getEmployee.getDepartmentCode(),
//                DepartmentDto.class);

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/departments/" + getEmployee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        EmployeeDto employeeDto = modelMapper.map(getEmployee, EmployeeDto.class);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
