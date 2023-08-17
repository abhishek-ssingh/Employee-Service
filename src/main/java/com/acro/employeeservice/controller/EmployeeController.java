package com.acro.employeeservice.controller;

import com.acro.employeeservice.dto.APIResponseDto;
import com.acro.employeeservice.dto.EmployeeDto;
import com.acro.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<EmployeeDto>(savedEmployeeDto, HttpStatus.CREATED);
    }

//    @GetMapping("{email}")
//    public ResponseEntity<EmployeeDto> getByEmail(@PathVariable String email){
//        EmployeeDto employeeDto = employeeService.getByEmail(email);
//        return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
//
//    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getById(@PathVariable Long id){
        APIResponseDto apiResponseDto = employeeService.getById(id);
        return new ResponseEntity<APIResponseDto>(apiResponseDto, HttpStatus.OK);

    }
}
