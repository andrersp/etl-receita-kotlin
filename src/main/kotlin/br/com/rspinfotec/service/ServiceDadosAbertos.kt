package br.com.rspinfotec.service

import br.com.rspinfotec.extraction.Extraction
import br.com.rspinfotec.extraction.impl.*
import br.com.rspinfotec.integration.schemas.ResourceDTO
import br.com.rspinfotec.shared.*
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin

@Singleton
class ServiceDadosAbertos(
    private val downloadService: ServiceDownload,
    private val serviceHistory: ServiceHistory
) {
    suspend fun getFilesToDownload(resources: List<ResourceDTO>) {
        val files = resources.map { it.url.substringAfterLast("/") }

        val supervisor = SupervisorJob()
        val scope = CoroutineScope(Dispatchers.IO + supervisor)

        val history = serviceHistory.createNewHistory()

        val serviceComplementar = ExtractionComplementarImpl()
        val cnaeService = getService(serviceComplementar, files, PREFIX_CNAE, TABLE_CNAE, scope)
        val municipioService = getService(serviceComplementar, files, PREFIX_MUNICIPIOS, TABLE_MUNICIPIOS, scope)
        val naturezaJuridicaService =
            getService(serviceComplementar, files, PREFIX_NATUREZA_JURIDICA, TABLE_NATUREZAS_JURIDICAS, scope)
        val serviceQualificacaoSocio =
            getService(serviceComplementar, files, PREFIX_QUALIFICACOES, TABLE_QUALIFICACAO_SOCIO, scope)
        val serviceMotivos =
            getService(serviceComplementar, files, PREFIX_MOTIVOS, TABLE_MOTIVO_SITUACAO_CADASTRAL, scope)
        val servicePaises = getService(serviceComplementar, files, PREFIX_PAIS, TABLE_PAISES, scope)
        val serviceEmpresa = getService(ExtractionEmpresaImpl(), files, PREFIX_EMPRESA, TABLE_EMPRESAS, scope)
        val serviceSocio = getService(ExtractionSociosImpl(), files, PREFIX_SOCIOS, TABLE_SOCIOS, scope)
        val serviceEstabelecimento =
            getService(ExtractionEstabelecimentoImpl(), files, PREFIX_ESTABELECIMENTO, TABLE_ESTABELECIMENTOS, scope)
        val serviceSimples = getService(ExtractionSimplesImpl(), files, PREFIX_SIMPLES, TABLE_SIMPLES, scope)

        downloadService.startDownload(
            cnaeService,
            municipioService,
            naturezaJuridicaService,
            serviceQualificacaoSocio,
            serviceMotivos,
            servicePaises,
            serviceEmpresa,
            serviceSocio,
            serviceSimples,
            serviceEstabelecimento
        )

        supervisor.cancelAndJoin()
        serviceHistory.updateHistory(history.id)

    }

    private fun getService(
        service: Extraction,
        files: List<String>,
        filePrefix: String,
        tableName: String,
        scope: CoroutineScope
    ): DownloadParams {

        val filesDownload = files.filter { it.startsWith(filePrefix) }

        return DownloadParams(
            service = service,
            files = filesDownload,
            filePrefix = filePrefix,
            tableName = tableName,
            scope = scope
        )

    }


}