package com.boot.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.ms.entity.Game;

@Repository
//@FeignClient(value = "EMPLOYEE-MS")
public interface GameRepository extends JpaRepository<Game, Integer> {
	
	/*
	 * @GetMapping("/Employees/getEmployees") public List<Employee> getEmployees();
	 * 
	 * @GetMapping("/Employees/getEmployee/{employeeId}") public Employee
	 * getemployeesbyid(@PathVariable int id);
	 */
}
