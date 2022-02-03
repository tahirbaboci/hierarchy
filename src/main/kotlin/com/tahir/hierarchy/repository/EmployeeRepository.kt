package com.tahir.hierarchy.repository

import com.tahir.hierarchy.model.Employee
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface EmployeeRepository: CrudRepository<Employee, Long>{

    override fun findAll(): List<Employee>

    @Query("select e from Employee e where e.employeeName = :employeeName")
    fun findByEmployeeName(@Param("employeeName") employeeName: String):Employee?

    @Query("select e.supervisor from Employee e where e.employeeName = :employeeName")
    fun findSupervisorByEmployeeName(@Param("employeeName") employeeName: String): Employee?
}