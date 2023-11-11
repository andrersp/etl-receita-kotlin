package br.com.rspinfotec.controller

import br.com.rspinfotec.dto.CompanyResponseDTO
import br.com.rspinfotec.dto.StatusResponse
import br.com.rspinfotec.entity.Estabelecimento
import br.com.rspinfotec.service.ServiceHistory
import br.com.rspinfotec.service.ServiceReceitaFederal
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.swagger.v3.oas.annotations.Operation

@Controller("/receita")
class ControllerReceita(
    private val serviceReceitaFederal: ServiceReceitaFederal,
    private val serviceHistory: ServiceHistory,
) {

    @Post
    @Operation(description = "Verificar atualização e iniciar se desatualizado", summary ="Verificar autalição" )
    suspend fun verifyAndUpdate(): StatusResponse {
        val status = serviceReceitaFederal.verifyUpdate()
        return StatusResponse(status = status)

    }

    @Get(uri = "/history")
    fun showHistory() = serviceHistory.showHistory()

    @Get("/{cnpj}")
    suspend fun getEstab(@PathVariable cnpj: String): CompanyResponseDTO {
        val clearCnpj = cnpj.filter { it.isDigit() }
        val cnpjBase = clearCnpj.take(8).toInt()
        val cnpjDv = clearCnpj.takeLast(2).toInt()
        val cnpjOrdem = clearCnpj.substring(8, 12).toInt()

        val estabelecimento = serviceReceitaFederal.findByCnpj(cnpjBasico = cnpjBase, cnpjOrdem = cnpjOrdem, cnpjDv = cnpjDv)
        estabelecimento.empresa?.let {
            println(it.porteEmpresa)
        }
        estabelecimento.simples?.let {
            it.dataExclusaoMei?.let { exc -> println(exc) }
        }

        return CompanyResponseDTO.fromModel(estabelecimento)


    }


}