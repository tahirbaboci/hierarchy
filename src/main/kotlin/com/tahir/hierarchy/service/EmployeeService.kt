package com.tahir.hierarchy.service

interface EmployeeService {
    fun save(employeesHierarchy: Map<String, String>): String
    fun findSupervisor(employeeName: String): String
    fun structuredHierarchy(): String
}