package br.com.rspinfotec.exceptions

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class MessageException(
    val statusCode: Int,
    val message: String,
    val detail: String
)
