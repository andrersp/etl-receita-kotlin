package br.com.rspinfotec.dto

import br.com.rspinfotec.entity.Estabelecimento
import br.com.rspinfotec.enums.IdentificadorSocioEnum
import br.com.rspinfotec.enums.PorteEmpresaEnum
import br.com.rspinfotec.enums.SituacaoCadastralEnum
import br.com.rspinfotec.shared.StringUtils
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class CompanyResponseDTO(
    var razaoSocial: String = "",
    val nomeFantasia: String,
    val cnpj: String,
    val inicioAtividade: LocalDate?,
    var capitalSocial: Double = 0.0,
    var naturezaJuridica: String = "",
    var porteEmpresa: String = "",
    val telefones: MutableSet<String> = mutableSetOf(),
    val email: String,
    var cnaePrincipal: String ="",
    val situacaoCadastral: SituacaoCadastralEnum? = SituacaoCadastralEnum.DESCONHECIDA,
    val socios: MutableSet<SociosDTO> = mutableSetOf(),


) {
    companion object {
        fun fromModel(estabelecimento: Estabelecimento): CompanyResponseDTO {

            val response = CompanyResponseDTO(
                nomeFantasia = estabelecimento.nomeFantasia.orEmpty(),
                cnpj = StringUtils.parseCnpjToString(
                    estabelecimento.cnpjBasico,
                    estabelecimento.cnpjOrdem,
                    estabelecimento.cnpjDv
                ),
                email = estabelecimento.correioEletronico ?: "",
                inicioAtividade = estabelecimento.dataInicioAtividade,
                situacaoCadastral = SituacaoCadastralEnum.getByCode(estabelecimento.situacaoCadastral)

            )

            if (estabelecimento.ddd1 != null && estabelecimento.telefone1 != null) {
                response.telefones.add("(${estabelecimento.ddd1}) ${estabelecimento.telefone1}")

            }

            if (estabelecimento.ddd2 != null && estabelecimento.telefone2 != null) {
                response.telefones.add("(${estabelecimento.ddd2}) ${estabelecimento.telefone2}")
            }
            estabelecimento.empresa?.let {
                response.razaoSocial = it.razaoSocial
                response.capitalSocial = it.capitalSocialDaEmpresa ?: 0.0
                it.dataNaturezaJuridica?.let { naturezaJuridica ->
                    response.naturezaJuridica = naturezaJuridica.descricao
                }
                response.porteEmpresa = PorteEmpresaEnum.getByCode(it.porteEmpresa).descricao
            }

            estabelecimento.cnaePrincipal?.let {
                response.cnaePrincipal = "${it.codigo} - ${it.descricao}"
            }

            estabelecimento.socios.forEach {socio ->
                val dtoSocio = SociosDTO(
                    nome = socio.nomeSocio,
                    cpfCnpj = socio.cpfCnpjSocio ?: "",
                    dataEntrada = socio.dataEntradaSociedade,
                    identificadorSocio = IdentificadorSocioEnum.getByIdentificadorID(socio.identificadorSocio)?.descricao ?: "",
                    qualificacao = socio.qualificacao.descricao
                )
                response.socios.add(dtoSocio)
            }

            return response


        }
    }
}
