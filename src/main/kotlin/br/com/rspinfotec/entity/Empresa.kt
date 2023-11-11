package br.com.rspinfotec.entity

import jakarta.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction


@Entity
@Table(name = "empresas")
data class Empresa(
    @Id
    @Column(name = "cnpj_basico")
    val cnpjBasico: Int,

    val razaoSocial: String,

    @Column(name = "natureza_juridica")
    val naturezaJuridica: Int,

    val qualificacaoResponsavel: Int,

    val capitalSocialDaEmpresa: Double?,

    val porteEmpresa: Int,

    val enteFederativoResponsavel: String,

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(
        name = "natureza_juridica",
        referencedColumnName = "codigo",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(
            value = ConstraintMode.NO_CONSTRAINT
        ),
    )
    val dataNaturezaJuridica: NaturezaJuridica?
)