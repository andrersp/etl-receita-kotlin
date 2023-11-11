package br.com.rspinfotec.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "simples")
data class Simples(
    @Id
    val cnpjBasico: Int,

    val opcaoSimples: Boolean,

    val dataOpcaoSimples: LocalDate?,

    val dataExclusaoSimples: LocalDate?,

    val opcaoMei: Boolean,

    val dataOpcaoMei: LocalDate?,

    val dataExclusaoMei: LocalDate?
)
