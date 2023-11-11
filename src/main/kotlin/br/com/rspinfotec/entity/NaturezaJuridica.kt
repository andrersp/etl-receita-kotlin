package br.com.rspinfotec.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "naturezas_juridicas")
data class NaturezaJuridica(
    @Id
    val codigo: Int,
    val descricao: String

)
