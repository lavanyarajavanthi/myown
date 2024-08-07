package com.oneux.iam.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oneux.iam.department.entity.Department;
import com.oneux.iam.department.repository.DepartmentRepository;
import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.organization.entity.Organization;
import com.oneux.iam.success.ApiSuccess;
import com.oneux.iam.util.MessageConstants;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class DepartmentService {
private final DepartmentRepository departmentRepository;

public DepartmentService(DepartmentRepository departmentRepository) {
	super();
	this.departmentRepository = departmentRepository;
}
public List<Department> getAllDepartments() throws IAMException {
	log.info("******DepartmentService.getAllDepartments**************");
	
	List<Department>department=new ArrayList<>();
	try {
		department=departmentRepository.findByStatus("Active");
		
		
if(department.isEmpty()) {
    	 throw new IAMException("D001","MessageConstants.BAD_REQUEST1");
     }
	}catch(Exception e) {
		log.error("******DepartmentService.getAllDepartments,server read db exception****" + e); 
		List<String> errors = new ArrayList<>();
		throw new IAMException("D002", 
			 MessageConstants.SERVER_ERROR);
	
	}
	
	return department;
}

public Optional<Department> getDepartmentById(Long deptId) throws IAMException {
	log.info("************DepartmentService.getDepartmentById************");
	try {
	Optional<Department> department = departmentRepository.findById(deptId);
	if (department.isEmpty()) {
		throw new IAMException("D003","MessageConstants.NOT_FOUND");
	}
	return department;
	}catch(IllegalArgumentException e) {
		throw new IAMException("D004","MessageConstants.EMPTY");
	
	}catch(Exception e) {
		log.info("*********DepartmentService.getDepartmentById,no such id exists");
		List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
		throw new IAMException("D005",MessageConstants.SERVER_ERROR);
	}
	
}

public Department createDepartment(Department department) throws IAMException {
	log.info("*************DepartmentService.createDepartment***********");
	try {
		if(department.getDeptName().isEmpty()||department.getDeptName().length()==0) {
			throw new IAMException("D006",MessageConstants.BAD_REQUEST);
		}
		Department savedDepartment=departmentRepository.save(department);
		return savedDepartment;
	}catch(IllegalArgumentException e) {
		throw new IAMException("D007","MessageConstants.ERROR"+e.getMessage());
	}catch(Exception e) {
		throw new IAMException("D008","MessageConstants.INTERNAL_ERROR"+e.getMessage());
	}
}

public Department updateDepartment( Long deptId,Department department) throws IAMException {
	log.info("**************DepartmentService.updateDepartment*******************");
	 try {
           if (deptId == null || deptId <= 0) {
               throw new IAMException("D009","MessageConstants.BAD_REQUEST");
           }
           if (department == null) {
               throw new IAMException("D0010","MessageConstants.NO_CONTENT");
           }
           if (department.getDeptName() == null || department.getDeptName().isEmpty()) {
               throw new IAMException("D0011","MessageConstants.ERROR");
           }

          Department existingDepartment = departmentRepository.findById(deptId)
                  .orElseThrow(() -> new IAMException("D0012","MessageConstants.ERROR1"));
          existingDepartment.setDeptId(department.getDeptId());
      	existingDepartment.setDeptName(department.getDeptName());
      	existingDepartment.setStatus(department.getStatus());
      	return departmentRepository.save(department);       
       } catch (IAMException e) {
           log.info("****************DepartmentService.updateDepartment,server read db exception*************");
           List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
           throw new IAMException("D0013","MessageConstants.SERVER_ERROR"+ e.getMessage());
       }

   }




public Department deleteDepartment(Long deptId) throws IAMException {
	log.info("**********DepartmentService.deleteByDepartmentId**************");
	try {
		Optional<Department> optionalDepartment = departmentRepository.findById(deptId);
	
		
		if(optionalDepartment==null) {
			throw new IAMException("D0014","MessageConstants.NOT_FOUND");
		}
		if (optionalDepartment.isPresent()) {
			Department department = optionalDepartment.get();
			department.setStatus("InActive");
			return departmentRepository.save(department);
			  
		}
	}catch(Exception e){
		List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
		throw new IAMException("D0015","MessageConstants.SERVER_ERROR"+e.getMessage());
		}
	return departmentRepository.save(deleteDepartment(deptId)); 
	}
}


	



