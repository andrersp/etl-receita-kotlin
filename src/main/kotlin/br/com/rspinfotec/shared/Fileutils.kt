package br.com.rspinfotec.shared


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVPrinter
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.nio.file.Files.newBufferedReader
import java.nio.file.Files.newBufferedWriter
import java.nio.file.Paths
import java.util.zip.ZipFile

object Fileutils {

    fun createInputFile(fileName: String): File {
        createInputDir()
        val inputFileName = "$INPUT_DIR/$fileName"
        deleteFile(inputFileName)
        return File(inputFileName)
    }


    fun deleteFile(filePath: String) {
        File(filePath).delete()
    }

    fun unzipFile(outputFileName: String, zipFile: File): String {
        createOutputDir()
        val filePath = OUTPUT_DIR + File.separator + outputFileName
        deleteFile(filePath)

        ZipFile(zipFile).use { zip ->
            zip.entries().asSequence().forEach { entry ->
                zip.getInputStream(entry).use { input ->
                    if (!entry.isDirectory) {
                        extractFile(input, filePath)
                    }
                }
            }
        }
        zipFile.delete()
        return filePath

    }

    suspend fun readCsvFile(zipFilePath: String): CSVParser {
        val br =
            withContext(Dispatchers.IO) { newBufferedReader(Paths.get(zipFilePath), StandardCharsets.ISO_8859_1) }


        return CSVParser(
            br,
            CSVFormat.DEFAULT
                .withDelimiter(';')
                .withQuote('"')
                .withRecordSeparator("\r\n")


        )

    }

    suspend  fun createCsvFile(fileNamePrefix: String, headers: Array<String>): CSVPrinter {
        createOutputDir()
        val filePath = "$OUTPUT_DIR/${fileNamePrefix}_out.csv"
        deleteFile(filePath)
        val writer = withContext(Dispatchers.IO) {
            newBufferedWriter(Paths.get(filePath), StandardCharsets.UTF_8)
        }

        return CSVPrinter(
            writer,
            CSVFormat.DEFAULT.withHeader(*headers)
                .withDelimiter(';')
                .withQuote('"')
                .withRecordSeparator("\r\n")
        )

    }

    private fun extractFile(inputStream: InputStream, destFilePath: String) {
        val bos = BufferedOutputStream(FileOutputStream(destFilePath))
        val bytesIn = ByteArray(DEFAULT_BUFFER_SIZE)
        var read: Int
        while (inputStream.read(bytesIn).also { read = it } != -1) {
            bos.write(bytesIn, 0, read)
        }
        bos.close()

    }

    private fun createInputDir() {
        File(INPUT_DIR).run {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    private fun createOutputDir() {
        File(OUTPUT_DIR).run {
            if (!exists()) {
                mkdirs()
            }
        }
    }
}