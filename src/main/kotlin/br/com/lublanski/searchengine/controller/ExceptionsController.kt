package br.com.lublanski.searchengine.controller

import br.com.lublanski.searchengine.exception.ExceptionView
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ExceptionsController {

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSuchElementException(exception: NoSuchElementException, httpPath: HttpServletRequest): ExceptionView {

        return ExceptionView(
                error = HttpStatus.NOT_FOUND.name,
                status = HttpStatus.NOT_FOUND.value(),
                message = exception.message.toString(),
                path = httpPath.servletPath
                )
    }

   @ExceptionHandler(MethodArgumentNotValidException::class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException, httpPath: HttpServletRequest) : ExceptionView {
       val errorsList = HashMap<String,String?>()
       exception.bindingResult.fieldErrors.forEach {
           error -> errorsList.put(error.field,error.defaultMessage)
       }
       return ExceptionView(
           error = HttpStatus.BAD_REQUEST.name,
           status = HttpStatus.BAD_REQUEST.value(),
           message = errorsList.toString(),
           path = httpPath.servletPath
       )
   }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception, httpPath: HttpServletRequest): ExceptionView {

        return ExceptionView(
            error = HttpStatus.BAD_REQUEST.name,
            status = HttpStatus.BAD_REQUEST.value(),
            message = exception.message.toString(),
            path = httpPath.servletPath
        )
    }
}