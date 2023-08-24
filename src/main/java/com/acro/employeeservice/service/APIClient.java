package com.acro.employeeservice.service;

import com.acro.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("departments/{departmentcode}")
    DepartmentDto getDepartmentByCode(@PathVariable String departmentcode);
}
