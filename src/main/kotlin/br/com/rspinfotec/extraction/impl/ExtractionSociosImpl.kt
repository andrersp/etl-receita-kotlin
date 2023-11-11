package br.com.rspinfotec.extraction.impl

import br.com.rspinfotec.extraction.Extraction
import br.com.rspinfotec.shared.DateUtil
import br.com.rspinfotec.shared.Fileutils
import br.com.rspinfotec.shared.StringUtils
import jakarta.inject.Singleton
import org.apache.commons.csv.CSVPrinter

@Singleton
class ExtractionSociosImpl : Extraction {
    override suspend fun extractFile(csvInputFileName: String, csvOutPutFile: CSVPrinter) {
        println("Iniciando extração de $csvInputFileName")
        val fileData = Fileutils.readCsvFile(csvInputFileName)
        for (data in fileData) {
            val socio = listOf(
                data.get(0),
                data.get(1).toInt(),
                data.get(2),
                data.get(3).filter { it.isDigit() },
                data.get(4).toInt(),
                DateUtil.stringToDate(data.get(5)),
                data.get(6).toIntOrNull(),
                StringUtils.clearCpf(data.get(7)),
                data.get(8).orEmpty(),
                data.get(9).toIntOrNull(),
                data.get(10).toIntOrNull()
            )
            csvOutPutFile.printRecord(socio)


        }

        println("Extração de $csvInputFileName Finalizado")
    }

    override suspend fun createCsvOutputFile(prefixFileName: String): CSVPrinter {
        val header = arrayOf(
            "cnpj_basico",
            "identificador_socio",
            "nome_socio",
            "cpf_cnpj_socio",
            "qualificacao_socio",
            "data_entrada_sociedade",
            "codigo_pais",
            "representante_legal",
            "nome_representante_legal",
            "qualificacao_representante_legal",
            "faixa_etaria"
        )
        return Fileutils.createCsvFile(prefixFileName, header)
    }

}