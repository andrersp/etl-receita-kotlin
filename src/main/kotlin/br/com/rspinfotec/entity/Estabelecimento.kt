package br.com.rspinfotec.entity

import jakarta.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.time.LocalDate

@Entity
@Table(name = "estabelecimentos")
data class Estabelecimento(
    @Id
    @Column(name = "cnpj_basico")
    val cnpjBasico: Int,


    val cnpjOrdem: Int,


    val cnpjDv: Int,

    val identificadorMatrizFilial: Int,


    val nomeFantasia: String?,

    val situacaoCadastral: Int,

    val dataSituacaoCadastral: LocalDate?,

    val motivoSituacaoCadastral: Int?,


    val nomeCidadeExterior: String,


    val codPais: Int?,

    val dataInicioAtividade: LocalDate?,

    @Column(name = "cnae_fiscal")
    val cnaeFiscal: String,


    val tipoLogradouro: String,


    val logradouro: String,


    val numero: String,


    val complemento: String,


    val bairro: String,


    val cep: String,


    val uf: String,

    val codigoMunicipio: Int,


    val ddd1: String?,


    val telefone1: String?,


    val ddd2: String?,


    val telefone2: String?,


    val dddFax: String,


    val fax: String,


    val correioEletronico: String?,


    val situacaoEspecial: String,

    val dataSituacaoEspecial: LocalDate?,

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(
        name = "cnpj_basico",
        referencedColumnName = "cnpj_basico",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(
            value = ConstraintMode.NO_CONSTRAINT
        ),
    )
    val empresa: Empresa? = null,

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(
        name = "cnpj_basico",
        referencedColumnName = "cnpj_basico",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(
            value = ConstraintMode.NO_CONSTRAINT
        ),
    )
    val simples: Simples? = null,

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(
        name = "cnae_fiscal",
        referencedColumnName = "codigo",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(
            value = ConstraintMode.NO_CONSTRAINT
        ),
    )
    val cnaePrincipal: Cnae? = null,
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "cnpj_basico",
        referencedColumnName = "cnpj_basico",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    val socios: List<Socio> = listOf()


)
