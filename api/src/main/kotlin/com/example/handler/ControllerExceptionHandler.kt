package com.example.handler

import com.example.EntityNotFoundException
import org.hibernate.validator.internal.engine.path.PathImpl
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException

@ControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(e: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(e, null, HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(e: ConstraintViolationException, request: WebRequest): ResponseEntity<Any> {
        val errors = e.constraintViolations.associate { (it.propertyPath as PathImpl).leafNode.name to it.message }
        return handleExceptionInternal(e, ExceptionResponse(errors), HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}