package br.com.rspinfotec.exceptions

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error


@Controller
class ExceptionHandlers {

    @Error(global = true)
    fun apiExceptionHandler(request: HttpRequest<*>, exception: ApiException): HttpResponse<MessageException> {
        val message = MessageException(
            statusCode = exception.status.code,
            message = exception.errorType.message,
            detail = exception.errorType.name
        )

        return HttpResponse.status<MessageException?>(exception.status).body(message)
    }
}