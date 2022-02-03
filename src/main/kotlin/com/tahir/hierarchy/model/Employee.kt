package com.tahir.hierarchy.model

import com.tahir.hierarchy.error.HasAlreadyASupervisorError
import javax.persistence.*

@Entity
@Table(name = "employee")
data class Employee(
    @Id
    val employeeName: String ="",

    @ManyToOne(cascade = [CascadeType.ALL])
    val supervisor: Employee? = null,
) {
    fun buildTree(employeesList: List<Employee>): TreeNode<String> {
        return TreeNode(
            this.employeeName,
            employeesList
                .filter { it.supervisor?.employeeName == this.employeeName }
                .map { it.buildTree(employeesList) }
        )
    }
    fun manage(employee: Employee): Employee {
        if (employee.supervisor != null) {
            throw HasAlreadyASupervisorError("${employee.employeeName} is already managed by ${employee.supervisor.employeeName}")
        }else {
            return employee.copy(supervisor = this)
        }
    }
}