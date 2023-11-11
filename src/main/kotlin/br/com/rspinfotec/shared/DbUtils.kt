package br.com.rspinfotec.shared


import com.lordcodes.turtle.shellRun
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import kotlin.math.abs


@Singleton
class DbUtils(

) {

    @field:Property(name = "db.uri")
    private var databaseURi: String = ""


    suspend fun importDb(tableName: String, fileNamePrefix: String) {

        val filePath = "$OUTPUT_DIR/${fileNamePrefix}_out.csv"

        val start = System.currentTimeMillis()
        Logger.getAnonymousLogger().info("Iniciando importacao de $filePath")

        val saida = runSqlCommand(tableName, filePath)

        val fim = System.currentTimeMillis()
        val duration = abs(fim - start)
        val time = TimeUnit.MILLISECONDS.toMinutes(duration)
        Fileutils.deleteFile(filePath)
        Logger.getAnonymousLogger().info("Importacao de $filePath finalziada. Resulado -> $saida Duracao -> $time min")
    }

    private suspend fun runSqlCommand(tableName: String, filePath: String, attemps: Int = 0): String {

        var saida: String = ""

        try {
            saida = shellRun(
                "psql",
                listOf(
                    databaseURi,
                    "-c",
                    "\\copy $tableName FROM '$filePath' WITH DELIMITER ';' CSV HEADER;"
                )
            )
        } catch (exc: Exception) {
            Logger.getAnonymousLogger().info("Erro importar $filePath. Err -> ${exc.message}")

            if (attemps < 3) {
                delay(30000)
                return runSqlCommand(tableName, filePath, attemps + 1)
            }


        }

        return saida


    }

    fun createIndexOnTables() {
        var saida: String = ""
        Logger.getAnonymousLogger().info("Iniciando Criação de indexes")
        try {
            saida = shellRun(
                "psql",
                listOf(
                    databaseURi,
                    "-f",
                    "sql/create_index.sql"
                )
            )
        } catch (exc: Exception) {
            Logger.getAnonymousLogger().info("Erro ao criar index -> ${exc.message}")

        }

        Logger.getAnonymousLogger().info("Criação de indexes finalizada.")

        println(saida)
    }


}