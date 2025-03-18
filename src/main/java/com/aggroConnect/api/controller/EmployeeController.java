package com.aggroConnect.api.controller;

import com.aggroConnect.api.dto.ApiResponse;
import com.aggroConnect.api.dto.EmployeeDto;
import com.aggroConnect.api.model.Employee;
import com.aggroConnect.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<Employee>>> getAllEmployees() {
        Iterable<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ApiResponse<>(200,"Liste des employés récupérée avec succès", employees));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id); // Gestion de l'erreur est maintenant dans le service
        return ResponseEntity.ok(new ApiResponse<>(200, "Employé trouvé", employee));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.createEmployee(
                employeeDto.getName(),
                employeeDto.getEmail(),
                employeeDto.getLandline(),
                employeeDto.getCellphone(),
                employeeDto.getSiteId(),
                employeeDto.getDepartmentId()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Employé créé avec succès", employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee updated = employeeService.updateEmployee(id,
                employeeDto.getName(),
                employeeDto.getEmail(),
                employeeDto.getLandline(),
                employeeDto.getCellphone(),
                employeeDto.getSiteId(),
                employeeDto.getDepartmentId()
        );
        return ResponseEntity.ok(new ApiResponse<>(200, "Employé mis à jour avec succès", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Employé supprimé avec succès", null));
    }
}
