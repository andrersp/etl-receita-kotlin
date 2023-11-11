package br.com.rspinfotec.extraction.impl

import br.com.rspinfotec.extraction.Extraction
import br.com.rspinfotec.shared.Fileutils
import org.apache.commons.csv.CSVPrinter

class ExtractionComplementarImpl : Extraction {
    
    override suspend fun extractFile(csvInputFileName: String, csvOutPutFile: CSVPrinter) {
        println("Iniciando extração de $csvInputFileName")
        val fileData = Fileutils.readCsvFile(csvInputFileName)
        for (data in fileData) {
            if (data.get(0).isEmpty() || data.get(1).isEmpty()) continue
            val empresa = listOf(
                data.get(0).toInt(),
                data.get(1),
            )
            csvOutPutFile.printRecord(empresa)
        }
        println("Extração de $csvInputFileName Finalizado")
    }

    override suspend fun createCsvOutputFile(prefixFileName: String): CSVPrinter {
        val header = arrayOf(
            "codigo",
            "descricao",
        )
        return Fileutils.createCsvFile(prefixFileName, header)
    }


}