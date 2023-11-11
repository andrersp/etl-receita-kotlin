package br.com.rspinfotec.extraction.impl

import br.com.rspinfotec.extraction.Extraction
import br.com.rspinfotec.shared.Fileutils
import jakarta.inject.Singleton
import org.apache.commons.csv.CSVPrinter

@Singleton
class ExtractionEmpresaImpl : Extraction {

    override suspend fun extractFile(csvInputFileName: String, csvOutPutFile: CSVPrinter) {
        println("Iniciando extração de $csvInputFileName")
        val fileData = Fileutils.readCsvFile(csvInputFileName)
        for (data in fileData) {
            if (data.get(1).isEmpty()) continue
            if (data.get(2).isEmpty()) continue
            val empresa = listOf(
                data.get(0),
                data.get(1),
                data.get(2).let { if (it == "") 0 else it.toInt() },
                data.get(3).let { if (it == "") 0 else it.toInt() },
                data.get(4).replace(",", ".").toDouble(),
                data.get(5).let { if (it == "") 0 else it.toInt() },
                data.get(6)
            )
            csvOutPutFile.printRecord(empresa)
        }
        println("Extração de $csvInputFileName Finalizado")
    }

    override suspend fun createCsvOutputFile(prefixFileName: String): CSVPrinter {
        val header = arrayOf(
            "cnpj_basico",
            "razao_social",
            "natureza_juridica",
            "qualificacao_responsavel",
            "capital_social_da_empresa",
            "porte_empresa",
            "ente_federativo_responsavel"
        )
        return Fileutils.createCsvFile(prefixFileName, header)
    }


}