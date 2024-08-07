package com.oneux.iam.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneux.iam.constants.IAMConstants;
import com.oneux.iam.department.entity.Department;
import com.oneux.iam.department.service.DepartmentService;
import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.success.ApiSuccess;

@RestController
@RequestMapping(IAMConstants.DEPARTMENT_CONTEXT)
public class DepartmentController {

	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@GetMapping(value = IAMConstants.GET_ALL_DEPARTMENTS, produces = "application/json")
	public List<Department> getAllDepartments() throws IAMException {
		return departmentService.getAllDepartments();
	}

	@GetMapping(value = IAMConstants.GET_DEPARTMENT_BY_ID, produces = "application/json")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long deptId) throws  IAMException {
		departmentService.getDepartmentById(deptId);
		return new ResponseEntity<Department>(HttpStatus.OK);
	}

	@PostMapping(value = IAMConstants.CREATE, produces = "application/json")
	public Department createDepartment(@RequestBody Department department) throws IAMException {
		return departmentService.createDepartment(department);
	}

	@PostMapping(value = IAMConstants.UPDATE_BY_ID, produces = "application/json")
	public Department updateDepartment(@RequestBody Long deptId, Department department) throws IAMException {
		return departmentService.updateDepartment(deptId, department);
	}

	@DeleteMapping(value = IAMConstants.DELETE_BY_ID, produces = "application/json")
	public ApiSuccess deleteDepartment(@PathVariable Long deptId) throws IAMException {
		departmentService.deleteDepartment(deptId);
		return new ApiSuccess(HttpStatus.OK, "successfully deleted");
	}
}
