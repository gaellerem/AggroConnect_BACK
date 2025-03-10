package com.aggroConnect.api.service;

import com.aggroConnect.api.exception.ResourceNotFoundException;
import com.aggroConnect.api.model.Department;
import com.aggroConnect.api.model.Employee;
import com.aggroConnect.api.model.Site;
import com.aggroConnect.api.repository.DepartmentRepository;
import com.aggroConnect.api.repository.EmployeeRepository;
import com.aggroConnect.api.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    SiteRepository siteRepository;
    DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, SiteRepository siteRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.siteRepository = siteRepository;
        this.departmentRepository = departmentRepository;
    }

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(String name, String email, String landline, String cellphone, Long siteId, Long departmentId) {
        Site site = siteRepository.findById(siteId)
                .orElseThrow(() -> new ResourceNotFoundException("Site", siteId));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", departmentId));

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setLandline(landline);
        employee.setCellphone(cellphone);
        employee.setSite(site);
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public void updateEmployee(Long id, String name, String email, String landline, String cellphone, Long siteId, Long departmentId) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
        Site site = siteRepository.findById(siteId)
                .orElseThrow(() -> new ResourceNotFoundException("Site", siteId));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", departmentId));
        employee.setName(name);
        employee.setEmail(email);
        employee.setLandline(landline);
        employee.setCellphone(cellphone);
        employee.setSite(site);
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }
    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Employee", id);
        }
    }
}