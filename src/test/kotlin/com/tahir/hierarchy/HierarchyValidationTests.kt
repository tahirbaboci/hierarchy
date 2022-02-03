package com.tahir.hierarchy

import com.tahir.hierarchy.HierarchyValidation.isStringOnlyAlphabet
import com.tahir.hierarchy.HierarchyValidation.validate
import com.tahir.hierarchy.error.CycleError
import com.tahir.hierarchy.error.EmptyHierarchyError
import com.tahir.hierarchy.error.TwoBossError
import com.tahir.hierarchy.service.Helper
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class HierarchyValidationTests {

    @Test
    fun `test success and failure case of IsStringOnlyAlphabet`() {
        Assert.assertTrue(isStringOnlyAlphabet("Tahir"))
        Assert.assertTrue(!isStringOnlyAlphabet("1234"))
    }

    @Test
    fun `test validate for CycleError`() {
        Assertions.assertThrows(CycleError::class.java) { validate(Helper.supervisorToEmployees(TestData.testCycleErrorHierarchyMap)) }
    }

    @Test
    fun `test validate for TwoBossError`() {
        Assertions.assertThrows(TwoBossError::class.java) { validate(Helper.supervisorToEmployees(TestData.testTwoBossErrorHierarchyMap)) }
    }

    @Test
    fun `test validate for EmptyHierarchyError`() {
        Assertions.assertThrows(EmptyHierarchyError::class.java) { validate(Helper.supervisorToEmployees(emptyMap())) }
    }

}