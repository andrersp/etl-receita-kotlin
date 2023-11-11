package br.com.rspinfotec.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "municipios")
data class Municipio(
    @Id
    val codigo: Int,
    val descricao: String

)
