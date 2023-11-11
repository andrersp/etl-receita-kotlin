package br.com.rspinfotec.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "motivo_situacao_cadastral")
data class MotivoSituacaoCadastral(
    @Id
    val codigo: Int,
    val descricao: String
)
