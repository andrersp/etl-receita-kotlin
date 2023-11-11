package br.com.rspinfotec.extraction

import org.apache.commons.csv.CSVPrinter

interface Extraction {

    suspend fun extractFile(csvInputFileName: String, csvOutPutFile: CSVPrinter)
    suspend fun createCsvOutputFile(prefixFileName: String): CSVPrinter
}