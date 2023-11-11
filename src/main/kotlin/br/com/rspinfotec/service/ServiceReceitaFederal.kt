package br.com.rspinfotec.service

import br.com.rspinfotec.entity.Estabelecimento
import br.com.rspinfotec.integration.IntegracaoRF
import br.com.rspinfotec.repository.EstabelecimentoRepository
import br.com.rspinfotec.shared.DateUtil
import br.com.rspinfotec.shared.StatusEnum
import br.com.rspinfotec.shared.TIPO_ARQUIVO_ACEITO
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

@Singleton
class ServiceReceitaFederal(
    private val integrationReceitaFederal: IntegracaoRF,
    private val serviceHistory: ServiceHistory,
    private val serviceExtraction: ServiceDadosAbertos,
    private val estabelecimentoRepository: EstabelecimentoRepository

) {

    suspend fun verifyUpdate(): StatusEnum {
        val lastUpdateLocal = serviceHistory.getLastHistory()
        val dados = integrationReceitaFederal.obterConjuntoDeDados()

        if (lastUpdateLocal != null) {

            if (lastUpdateLocal.status == StatusEnum.UPDATING.value) {
                return StatusEnum.UPDATING
            }
            val lastUpdateReceita = DateUtil.stringToDateTime(dados.metadataModified)

            if (lastUpdateLocal.finishedIn != null && !lastUpdateReceita.isAfter(lastUpdateLocal.finishedIn)) {
                return StatusEnum.UPDATED
            }
        }
        val validResources = dados.resources.filter { it.format == TIPO_ARQUIVO_ACEITO }
        CoroutineScope(Dispatchers.IO).launch {
            serviceExtraction.getFilesToDownload(validResources)
        }

        return StatusEnum.OUTDATED
    }

    suspend fun findByCnpj(cnpjBasico: Int, cnpjOrdem: Int, cnpjDv: Int): Estabelecimento {

        return estabelecimentoRepository.findByCnpjBasicoAndCnpjOrdemAndCnpjDv(cnpjBasico, cnpjOrdem, cnpjDv) ?: throw Exception("error")
    }


}