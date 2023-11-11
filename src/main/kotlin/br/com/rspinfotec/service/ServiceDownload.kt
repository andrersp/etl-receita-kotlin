package br.com.rspinfotec.service

import br.com.rspinfotec.integration.IntegracaoDadosAbertos
import br.com.rspinfotec.shared.DbUtils
import br.com.rspinfotec.shared.Fileutils
import jakarta.inject.Singleton
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Singleton
class ServiceDownload(
    private val integracaoDadosAbertos: IntegracaoDadosAbertos,
    private val dbUtils: DbUtils
) {

    suspend fun startDownload(vararg services: DownloadParams) {

        services.asFlow()
            .onEach { service -> download(service) }
            .onCompletion { dbUtils.createIndexOnTables() }
            .collect()
    }

    private suspend fun download(service: DownloadParams) {

        println("Iniciado download de ${service.filePrefix}")
        val jobs: MutableList<Job> = mutableListOf()
        val csvFiles: MutableList<String> = mutableListOf()
        val csvOutputFile = service.service.createCsvOutputFile(service.filePrefix)

        for (fileName in service.files) {
            val job = service.scope.launch {
                val csvFile = downloadUnzipFile(fileName)
                csvFiles.add(csvFile)
            }
            jobs.add(job)
        }
        jobs.joinAll()

        for (csvFile in csvFiles) {
            service.service.extractFile(csvFile, csvOutputFile)
            Fileutils.deleteFile(csvFile)
        }

        csvOutputFile.flush()
        csvOutputFile.close()

        service.scope.launch {
            dbUtils.importDb(service.tableName, service.filePrefix)
        }.join()
    }

    private suspend fun downloadUnzipFile(fileName: String, attempts: Int = 0): String {
        val file = Fileutils.createInputFile(fileName = fileName)
        val csvFile: String

        try {
            integracaoDadosAbertos.downloadFile(fileName).collect { br ->
                file.appendBytes(br)
            }
            val zipFilename = fileName.replace("zip", "csv")
            csvFile = Fileutils.unzipFile(zipFilename, file)

        } catch (exc: Exception) {

            val attempsNumber = attempts + 1
            println("Erro ao baixar arquivo $fileName")
            println("Tentativa $attempsNumber")
            println("Erro -> ${exc.message}")
            if (attempts <= 3) {
                delay(1000)
                return downloadUnzipFile(fileName, attempsNumber)
            }
            throw Exception()

        }
        return csvFile
    }
}