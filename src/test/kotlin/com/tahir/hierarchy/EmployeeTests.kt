package com.tahir.hierarchy
import com.tahir.hierarchy.model.Employee
import org.junit.Assert
import org.junit.jupiter.api.Test

class EmployeeTests {

    @Test
    fun `PBT test for manage function`() {
        val employees = TestData.testHierarchyMap.entries.map { employeeName ->
            val supervisor = Employee(employeeName = employeeName.value)
            val employee = Employee(employeeName = employeeName.key)
            supervisor.manage(employee)
        }

        employees.forEach {
            Assert.assertTrue(TestData.testHierarchyMap.containsKey(it.employeeName))
            Assert.assertTrue(TestData.testHierarchyMap.containsValue(it.supervisor?.employeeName))
        }
    }
}