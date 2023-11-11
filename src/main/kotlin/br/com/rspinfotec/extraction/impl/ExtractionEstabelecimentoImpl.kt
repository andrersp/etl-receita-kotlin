package br.com.rspinfotec.extraction.impl

import br.com.rspinfotec.extraction.Extraction
import br.com.rspinfotec.shared.DateUtil
import br.com.rspinfotec.shared.Fileutils
import br.com.rspinfotec.shared.StringUtils
import jakarta.inject.Singleton
import org.apache.commons.csv.CSVPrinter

@Singleton
class ExtractionEstabelecimentoImpl : Extraction {
    
    override suspend fun extractFile(csvInputFileName: String, csvOutPutFile: CSVPrinter) {
        println("Iniciando extração de $csvInputFileName")
        val fileData = Fileutils.readCsvFile(csvInputFileName)
        for (data in fileData) {

            if (data.get(0).isEmpty()) continue
            if (data.get(1).isEmpty()) continue
            if (data.get(2).isEmpty()) continue

            val estabelecimento = listOf(
                data.get(0),
                data.get(1),
                data.get(2),
                data.get(3).toInt(),
                data.get(4),
                data.get(5).toInt(),
                DateUtil.stringToDate(data.get(6)),
                data.get(7).toIntOrNull(),
                data.get(8),
                data.get(9).toIntOrNull(),
                DateUtil.stringToDate(data.get(10)),
                data.get((11)),
                data.get(13),
                StringUtils.clearString(data.get(14)),
                data.get(15),
                StringUtils.clearString(data.get(16)),
                data.get(17),
                data.get(18),
                data.get(19),
                data.get(20).toInt(),
                data.get(21),
                data.get(22),
                data.get(23),
                data.get(24),
                data.get(25),
                data.get(26),
                data.get(27),
                data.get(28),
                DateUtil.stringToDate(data.get(29)),
            )
            csvOutPutFile.printRecord(estabelecimento)


        }
        println("Extração de $csvInputFileName Finalizado")
    }

    override suspend fun createCsvOutputFile(prefixFileName: String): CSVPrinter {
        val header = arrayOf(
            "cnpj_basico",
            "cnpj_ordem",
            "cnpj_dv",
            "identificador_matriz_filial",
            "nome_fantasia",
            "situacao_cadastral",
            "data_situacao_cadastral",
            "motivo_situacao_cadastral",
            "nome_cidade_exterior ",
            "cod_pais",
            "data_inicio_atividade",
            "cnae_fiscal",
            "tipo_logradouro",
            "logradouro",
            "numero",
            "complemento",
            "bairro",
            "cep",
            "uf",
            "codigo_municipio",
            "ddd1",
            "telefone1",
            "ddd2",
            "telefone2",
            "ddd_fax",
            "fax",
            "correio_eletronico",
            "situacao_especial",
            "data_situacao_especial"
        )
        return Fileutils.createCsvFile(prefixFileName, header)
    }


}