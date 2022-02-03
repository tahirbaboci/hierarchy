package com.tahir.hierarchy.error

import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class HierarchyExceptionHandler {

    private val log = LoggerFactory.getLogger(HierarchyExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleValidationError(ex: MethodArgumentNotValidException): ApiError {
        val bindingResult = ex.bindingResult
        val fieldError = bindingResult.fieldError
        val defaultMessage = fieldError!!.defaultMessage
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.BAD_REQUEST,
            errorMessage = defaultMessage,
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleEmployeeNotFoundError(ex: EmptyResultDataAccessException): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.NOT_FOUND,
            errorMessage = ex.message,
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(CycleError::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleCycleError(ex: CycleError): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.BAD_REQUEST,
            errorMessage = ex.message,
            detail = "This is a cycle error, please check you hierarchy structure once again",
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(TwoBossError::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleTwoBossError(ex: TwoBossError): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.BAD_REQUEST,
            errorMessage = ex.message,
            detail = "You have two bosses in your hierarchy, please check you hierarchy structure once again",
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(EmptyHierarchyError::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleEmptyHierarchyError(ex: EmptyHierarchyError): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.BAD_REQUEST,
            errorMessage = ex.message,
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(SupervisorNotFoundError::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleSupervisorNotFoundError(ex: SupervisorNotFoundError): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.NOT_FOUND,
            errorMessage = ex.message,
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(HasAlreadyASupervisorError::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleHasAlreadyASupervisorError(ex: HasAlreadyASupervisorError): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.BAD_REQUEST,
            errorMessage = ex.message,
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(EmployeeNameNotValidError::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleEmployeeNameNotValidError(ex: EmployeeNameNotValidError): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.BAD_REQUEST,
            errorMessage = ex.message,
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(EmployeesNotFoundError::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleEmployeesNotFoundError(ex: EmployeesNotFoundError): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.NOT_FOUND,
            errorMessage = ex.message,
            stackTrace = ex.stackTraceToString()
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleInternalServerError(ex: Exception): ApiError {
        log.error(ex.message)
        return ApiError(
            errorCode = HttpStatus.INTERNAL_SERVER_ERROR,
            errorMessage = ex.message,
            stackTrace = ex.stackTraceToString()
        )
    }

}