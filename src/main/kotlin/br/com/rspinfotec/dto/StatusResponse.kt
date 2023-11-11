package br.com.rspinfotec.dto

import br.com.rspinfotec.shared.StatusEnum
import io.micronaut.serde.annotation.Serdeable

@Serdeable.Serializable
data class StatusResponse(
    val status: StatusEnum
)
