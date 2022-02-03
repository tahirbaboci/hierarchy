package com.tahir.hierarchy

import com.tahir.hierarchy.service.EmployeeService
import com.tahir.hierarchy.service.Helper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class EmployeeServiceTests {

    private val employeeService: EmployeeService = mockk(relaxed = true)

    @Test
    fun whenSavingHierarchy_thenReturnListOfSavedEmployees() {
        //given
        every { employeeService.save(TestData.testHierarchyMap) } returns TestData.expectedTestData
        //when
        val result = employeeService.save(TestData.testHierarchyMap)
        //then
        verify(exactly = 1) { employeeService.save(TestData.testHierarchyMap) }
        Assert.assertEquals(TestData.expectedTestData, result)
    }

    @Test
    fun whenGettingCurrentStructuredHierarchy_thenReturnStructuredHierarchyOfCompany() {
        //given
        every { employeeService.structuredHierarchy() } returns TestData.expectedTestData
        //when
        val result = employeeService.structuredHierarchy()
        //then
        verify(exactly = 1) { employeeService.structuredHierarchy() }
        Assert.assertEquals(TestData.expectedTestData, result)
    }

    @Test
    fun whenSearchingToFindSupervisor_thenReturnSupervisor() {
        //given
        every { employeeService.findSupervisor("Tahir") } returns "{\"Pellumb\": { }}"
        //when
        val result = employeeService.findSupervisor("Tahir")
        //then
        verify(exactly = 1) { employeeService.findSupervisor("Tahir")  }
        Assert.assertEquals("{\"Pellumb\": { }}", result)
    }

    @Test
    fun test_buildListOfEmployee() {
        val result = Helper.buildListOfEmployee(TestData.testHierarchyMap).map { it.employeeName }
        Assert.assertEquals(TestData.testHierarchyMap.keys.toList(), result)
    }

}