package com.aggroConnect.api.service;

import com.aggroConnect.api.exception.EntityDeletionException;
import com.aggroConnect.api.model.Department;
import com.aggroConnect.api.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
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
        }).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    public void deleteDepartmentById(Long id) {
        try {
            if (departmentRepository.existsById(id)) {
                departmentRepository.deleteById(id);
            } else {
                throw new RuntimeException("Le service n'a pas été retrouvé avec cet id: " + id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new EntityDeletionException("Ce service est encore affecté à des employés.");
        }
    }
}
