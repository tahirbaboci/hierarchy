package com.tahir.hierarchy.service

import com.tahir.hierarchy.HierarchyValidation.isStringOnlyAlphabet
import com.tahir.hierarchy.HierarchyValidation.validate
import com.tahir.hierarchy.HierarchyValidation.validateIfDBIsEmpty
import com.tahir.hierarchy.error.CycleError
import com.tahir.hierarchy.error.EmployeeNameNotValidError
import com.tahir.hierarchy.error.EmployeesNotFoundError
import com.tahir.hierarchy.error.SupervisorNotFoundError
import com.tahir.hierarchy.model.Employee
import com.tahir.hierarchy.repository.EmployeeRepository
import com.tahir.hierarchy.service.Helper.supervisorToEmployees
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl(private val repository: EmployeeRepository): EmployeeService {

    override fun save(employeesHierarchy: Map<String, String>): String {
        validate(supervisorToEmployees(employeesHierarchy))
        Helper.buildListOfEmployee(employeesHierarchy)
            .map { employeeEntity ->
//                 upsert(employeeEntity)
                val existsSupervisor = repository.findByEmployeeName(employeeEntity.supervisor!!.employeeName)
                if(existsSupervisor == null)
                    repository.save(employeeEntity)
                else
                    repository.save(employeeEntity.copy(supervisor = existsSupervisor))
            }
        val listOfSavedEmployees = repository.findAll()
        validateIfDBIsEmpty(listOfSavedEmployees)
        return Helper
            .findBoss(listOfSavedEmployees)
            .buildTree(listOfSavedEmployees)
            .toJson()
    }

//    fun upsert(employee: Employee) {
//        val existsSupervisor = repository.findByEmployeeName(employee.supervisor!!.employeeName)
//        if(existsSupervisor == null)
//            repository.save(employee)
//        else
//            repository.save(employee.copy(supervisor = existsSupervisor))
//    }

    override fun findSupervisor(employeeName: String): String {
        if(!isStringOnlyAlphabet(employeeName)) throw EmployeeNameNotValidError("this $employeeName, employee name you are searching for is not valid")
        val supervisor = repository.findSupervisorByEmployeeName(employeeName)
        if(supervisor == null)
            throw SupervisorNotFoundError("No supervisor is found for $employeeName")
        else {
            val supervisorOfSupervisor = supervisor.supervisor
            val listEmployees = listOfNotNull(Employee(employeeName = employeeName), supervisor, supervisorOfSupervisor)
            return supervisorOfSupervisor?.buildTree(listEmployees)?.toJson() ?: supervisor.buildTree(listEmployees).toJson()
        }
    }

    override fun structuredHierarchy(): String {
        val listEmployees = repository.findAll()
        validateIfDBIsEmpty(listEmployees)
        val boss = Helper.findBoss(listEmployees)
        return boss.buildTree(listEmployees).toJson()
    }
}

object Helper{
    fun buildListOfEmployee(hierarchyMap: Map<String, String>): List<Employee> {
        return hierarchyMap.entries
            .associate { Employee(it.key) to Employee(it.value) }
            .map { it.value.manage(it.key) }
    }

    fun supervisorToEmployees(employeeToSupervisor: Map<String, String>): Map<String, List<String>> {
        val allEmployees = employeeToSupervisor.entries.flatMap { e -> listOf(e.key, e.value) }.toSet()
        //keys are elements from the given collection and values are produced by the function applied to each element
        return allEmployees.associateWith { supervisor -> findEmployeesOfSupervisor(supervisor, employeeToSupervisor) }
    }
    private fun findEmployeesOfSupervisor(supervisor: String, employeesHierarchy: Map<String, String>): List<String> {
        // finds all employees for the `supervisor`
        return employeesHierarchy.entries
            .filter { it.value == supervisor }
            .map { it.key }
    }

    fun findBoss(listOfEmployees: List<Employee>): Employee {
        return listOfEmployees.findLast { e -> e.supervisor == null } ?: throw CycleError("A cycle has been detected")
    }
}