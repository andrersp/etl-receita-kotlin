package br.com.rspinfotec.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cnaes")
data class Cnae(
    @Id
    val codigo: String,
    val descricao: String
)
