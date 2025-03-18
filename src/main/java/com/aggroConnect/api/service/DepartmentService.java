package com.aggroConnect.api.service;

import com.aggroConnect.api.exception.EntityDeletionException;
import com.aggroConnect.api.exception.ResourceNotFoundException;
import com.aggroConnect.api.model.Department;
import com.aggroConnect.api.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ce service", id));
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        return departmentRepository.findById(id).map(department -> {
            if(updatedDepartment.getName() != null) {
                department.setName(updatedDepartment.getName());
            }
            return departmentRepository.save(department);
        }).orElseThrow(() -> new ResourceNotFoundException("Ce service", id));
    }

    public void deleteDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ce service", id));
        try {
            departmentRepository.delete(department);
        } catch (DataIntegrityViolationException e) {
            throw new EntityDeletionException("Ce service est encore affecté à des employés.");
        }
    }
}
