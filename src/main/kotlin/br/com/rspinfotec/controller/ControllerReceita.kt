package br.com.rspinfotec.controller

import br.com.rspinfotec.dto.CompanyResponseDTO
import br.com.rspinfotec.dto.StatusResponse
import br.com.rspinfotec.entity.Estabelecimento
import br.com.rspinfotec.exceptions.ApiException
import br.com.rspinfotec.exceptions.Errors
import br.com.rspinfotec.exceptions.MessageException
import br.com.rspinfotec.service.ServiceHistory
import br.com.rspinfotec.service.ServiceReceitaFederal
import br.com.rspinfotec.shared.Maintenance
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Controller("/receita")
@ApiResponses(
    ApiResponse(responseCode = "400", content = [Content(schema = Schema(implementation = MessageException::class))]),
    ApiResponse(responseCode = "503", content = [Content(schema = Schema(implementation = MessageException::class))])
)
class ControllerReceita(
    private val serviceReceitaFederal: ServiceReceitaFederal,
    private val serviceHistory: ServiceHistory,
) {

    @Post
    @Operation(description = "Verificar atualização e iniciar se desatualizado", summary ="Verificar atualização" )
    suspend fun verifyAndUpdate(): StatusResponse {
        val status = serviceReceitaFederal.verifyUpdate()
        return StatusResponse(status = status)

    }

    @Get(uri = "/history")
    fun showHistory() = serviceHistory.showHistory()

    @Get("/{cnpj}")
    suspend fun searchByCnpj(@PathVariable cnpj: String): CompanyResponseDTO {

        if (Maintenance.checkMaintenance()) throw ApiException(Errors.MAINTENANCE, HttpStatus.SERVICE_UNAVAILABLE)

        val clearCnpj = cnpj.filter { it.isDigit() }
        val cnpjBase = clearCnpj.take(8).toInt()
        val cnpjDv = clearCnpj.takeLast(2).toInt()
        val cnpjOrdem = clearCnpj.substring(8, 12).toInt()

        val estabelecimento = serviceReceitaFederal.findByCnpj(cnpjBasico = cnpjBase, cnpjOrdem = cnpjOrdem, cnpjDv = cnpjDv)

        return CompanyResponseDTO.fromModel(estabelecimento)

    }


}