package com.tahir.hierarchy

import org.junit.Assert.*
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.*
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HierarchyIntegrationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @LocalServerPort
    var randomServerPort: Int = 0


    @Before
    fun setUp() {
        testRestTemplate = TestRestTemplate()
    }

    @Test
    fun whenNewHierarchy_CreateHierarchySuccessfully() {

        val baseUrl = "http://localhost:" + randomServerPort.toString() + "/employee/hierarchy"
        val uri = URI(baseUrl)
        val hierarchyMap = """
            {
              "Pete": "Nicku",
              "Barbara": "Nicku",
              "Nicku": "Sophie",
              "Sophie": "Jonas"
            }
        """.trimIndent()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity(hierarchyMap, headers)
        val result = testRestTemplate
            .withBasicAuth("theUsername", "thePassword")
            .postForEntity(uri, request, String::class.java)
        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result.statusCode)
    }


    @Test
    fun whenNewHierarchyWithTwoBoss_ReturnBadRequestError() {

        val baseUrl = "http://localhost:" + randomServerPort.toString() + "/employee/hierarchy"
        val uri = URI(baseUrl)
        val hierarchyMap = """
            {
              "Pete": "Nicku",
              "Barbara": "Nicku",
              "Nicku": "Sophie",
              "Sophie": "Jonas",
              "Tahir": "Pellumb"
            }
        """.trimIndent()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity(hierarchyMap, headers)
        val result = testRestTemplate
            .withBasicAuth("theUsername", "thePassword")
            .exchange(uri,
                    HttpMethod.POST,
                    request,
                    Object::class.java
                )
        assertNotNull(result)
        assertTrue(result.body.toString().contains("TwoBossError"))
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
    }

    @Test
    fun whenNewHierarchyCycle_ReturnBadRequestError() {

        val baseUrl = "http://localhost:" + randomServerPort.toString() + "/employee/hierarchy"
        val uri = URI(baseUrl)
        val hierarchyMap = """
            {
              "Pete": "Nicku",
              "Barbara": "Nicku",
              "Nicku": "Sophie",
              "Sophie": "Jonas",
              "Jonas": "Nicku"
            }
        """.trimIndent()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity(hierarchyMap, headers)
        val result = testRestTemplate
            .withBasicAuth("theUsername", "thePassword")
            .exchange(uri,
                HttpMethod.POST,
                request,
                Object::class.java
            )
        assertNotNull(result)
        assertTrue(result.body.toString().contains("CycleError"))
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
    }


    @Test
    fun whenNewHierarchyWithCycle_ReturnBadRequestError() {

        val baseUrl = "http://localhost:" + randomServerPort.toString() + "/employee/hierarchy"
        val uri = URI(baseUrl)
        val hierarchyMap = """
            {
              "Pete": "Nicku",
              "Barbara": "Nicku",
              "Nicku": "Sophie",
              "Sophie": "Jonas",
              "Sophie": "Nicku"
            }
        """.trimIndent()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity(hierarchyMap, headers)
        val result = testRestTemplate
            .withBasicAuth("theUsername", "thePassword")
            .exchange(uri,
                HttpMethod.POST,
                request,
                Object::class.java
            )
        assertNotNull(result)
        println(result.body)
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
    }


    @Test
    fun whenUserWithWrongCredentials_thenUnauthorized() {
        val baseUrl = "http://localhost:" + randomServerPort.toString() + "/employee/structuredHierarchy"
        val uri = URI(baseUrl)
        testRestTemplate = TestRestTemplate("user", "wrongpassword")
        val response: ResponseEntity<String> = testRestTemplate.getForEntity(uri, String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }
}