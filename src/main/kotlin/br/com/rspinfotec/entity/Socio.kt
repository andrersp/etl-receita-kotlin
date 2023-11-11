package br.com.rspinfotec.entity

import jakarta.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.time.LocalDate

@Entity
@Table(name = "socios")
data class Socio(

    @Column(name = "cnpj_basico")
    val cnpjBasico: Int,

    val identificadorSocio: Int,

    @Id
    val nomeSocio: String,

    val cpfCnpjSocio: String?,

    @Column(name = "qualificacao_socio")
    val qualificacaoSocio: Int,

    val dataEntradaSociedade: LocalDate?,

    val codigoPais: Int?,

    val representanteLegal: String?,

    val nomeRepresentanteLegal: String,

    val qualificacaoRepresentanteLegal: Int?,

    val faixaEtaria: Int?,

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(
        name = "qualificacao_socio",
        referencedColumnName = "codigo",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(
            value = ConstraintMode.NO_CONSTRAINT
        ),
    )
    val qualificacao: QualificacaoSocio


    )
