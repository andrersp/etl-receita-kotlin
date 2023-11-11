package br.com.rspinfotec.extraction.impl

import br.com.rspinfotec.extraction.Extraction
import br.com.rspinfotec.shared.DateUtil
import br.com.rspinfotec.shared.Fileutils
import jakarta.inject.Singleton
import org.apache.commons.csv.CSVPrinter

@Singleton
class ExtractionSimplesImpl : Extraction {

    override suspend fun extractFile(csvInputFileName: String, csvOutPutFile: CSVPrinter) {
        println("Iniciando extração de $csvInputFileName")
        val fileData = Fileutils.readCsvFile(csvInputFileName)
        for (data in fileData) {
            val simples = listOf(
                data.get(0),
                data.get(1) == "S",
                DateUtil.stringToDate(data.get(2)),
                DateUtil.stringToDate(data.get(3)),
                data.get(4) == "S",
                DateUtil.stringToDate(data.get(5)),
                DateUtil.stringToDate(data.get(6))
            )
            csvOutPutFile.printRecord(simples)

        }
        println("Extração de $csvInputFileName Finalizado")
    }

    override suspend fun createCsvOutputFile(prefixFileName: String): CSVPrinter {
        val header = arrayOf(
            "cnpj_basico",
            "opcao_simples",
            "data_opcao_simples",
            "data_exclusao_simples",
            "opcao_mei",
            "data_opcao_mei",
            "data_exclusao_mei"
        )
        return Fileutils.createCsvFile(prefixFileName, header)
    }

}