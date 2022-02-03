package com.tahir.hierarchy.controller

import com.tahir.hierarchy.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/employee")
class HierarchyController(@Autowired val service: EmployeeService){

    @PostMapping("/hierarchy")
    fun hierarchy(@RequestBody employeesHierarchy: Map<String, String>): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(employeesHierarchy))
    }

    @PostMapping("/supervisor")
    fun supervisor(@RequestBody employeeName: String): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body(service.findSupervisor(employeeName))
    }

    @GetMapping("/structuredHierarchy")
    fun structuredHierarchy(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body(service.structuredHierarchy())
    }
}