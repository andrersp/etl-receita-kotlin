package br.com.rspinfotec.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "qualificacoes_socios")
data class QualificacaoSocio(
    @Id
    @Column(name = "codigo")
    val codigo: Int,
    val descricao: String

)
