package com.aggroConnect.api.controller;

import com.aggroConnect.api.dto.ApiResponse;
import com.aggroConnect.api.model.Department;
import com.aggroConnect.api.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<Department>>> getAllDepartments() {
        Iterable<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(new ApiResponse<>(200, "Liste des services récupérée avec succès", departments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> getDepartmentById(@PathVariable long id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Service trouvé", department));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody Department department) {
        Department created = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Service créé avec succès", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> updateDepartment(@PathVariable long id, @RequestBody Department department) {
        Department updated = departmentService.updateDepartment(id, department);
        return ResponseEntity.ok(new ApiResponse<>(200, "Service mis à jour avec succès", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Service supprimé avec succès", null));
    }
}
