package br.com.rspinfotec.dto

import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class SociosDTO(
    val nome: String,
    val cpfCnpj: String,
    val dataEntrada: LocalDate?,
    val identificadorSocio: String,
    val qualificacao: String

)
