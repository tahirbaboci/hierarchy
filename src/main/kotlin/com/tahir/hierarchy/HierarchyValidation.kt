package com.tahir.hierarchy

import com.tahir.hierarchy.error.CycleError
import com.tahir.hierarchy.error.EmployeesNotFoundError
import com.tahir.hierarchy.error.EmptyHierarchyError
import com.tahir.hierarchy.error.TwoBossError
import com.tahir.hierarchy.model.Employee

object HierarchyValidation {


    fun isStringOnlyAlphabet(str: String?): Boolean {
        return (str != ""
                && str != null
                && str.matches(Regex("^[a-zA-Z]*$")))
    }
    fun validate(supervisorToEmployees: Map<String, List<String>>) {
        checkIfEmptyBody(supervisorToEmployees)
        // Check list of all the employees which have managers (except one, the boss)
        val checkList = supervisorToEmployees.keys.map{ supervisor ->
            supervisorToEmployees.values.filter { employees -> employees.contains(supervisor) }.size
        }
        checkIfTwoBossesDetected(checkList)
        checkIfCycleDetected(checkList)
    }

    fun validateIfDBIsEmpty(listEmployees: List<Employee>) {
        if(listEmployees.isEmpty()) throw EmployeesNotFoundError("Employees not found in database")
    }

    private fun checkIfEmptyBody(supervisorToEmployees: Map<String, List<String>>) {
        if(supervisorToEmployees.isEmpty())
            throw EmptyHierarchyError("[Validation Error] No Employees found in the hierarchy request")
    }
    private fun checkIfTwoBossesDetected(check: List<Int>) {
        if (check.filter { it == 0 }.size > 1)
            throw TwoBossError("[Validation Error] Two bosses has been detected in the hierarchy")
    }
    private fun checkIfCycleDetected(check: List<Int>) {
        if(check.none { it == 0 })
            throw CycleError("[Validation Error] Boss is managed by someone else")
    }

}