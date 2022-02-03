package com.tahir.hierarchy.error

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiError(
    val errorCode: HttpStatus,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    val timestamp: LocalDateTime? = LocalDateTime.now(),
    val errorMessage: String?,
    val detail: String? = "",
    val stackTrace: String? = ""
)

class EmptyHierarchyError(message: String): RuntimeException(message)
class CycleError(message:String): RuntimeException(message)
class TwoBossError(message:String): RuntimeException(message)
class HasAlreadyASupervisorError(message:String): RuntimeException(message)
class SupervisorNotFoundError(message:String): RuntimeException(message)
class EmployeeNameNotValidError(message:String): RuntimeException(message)
class EmployeesNotFoundError(message:String): RuntimeException(message)