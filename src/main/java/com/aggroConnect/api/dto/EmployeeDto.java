package com.aggroConnect.api.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private String name;
    private String email;
    private String landline;
    private String cellphone;
    private Long siteId;
    private Long departmentId;
}