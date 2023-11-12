package br.com.rspinfotec.exceptions

import io.micronaut.http.HttpStatus

class ApiException(val errorType: Errors, val status: HttpStatus): RuntimeException() {
    constructor(errorType: Errors): this(errorType, HttpStatus.BAD_REQUEST){
    }
}


