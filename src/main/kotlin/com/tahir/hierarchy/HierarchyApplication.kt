package com.tahir.hierarchy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [ SecurityAutoConfiguration::class ])
class HierarchyApplication
fun main(args: Array<String>) {
	runApplication<HierarchyApplication>(*args)
}
