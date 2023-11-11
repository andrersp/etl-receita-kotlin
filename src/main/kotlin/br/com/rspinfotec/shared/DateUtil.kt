package br.com.rspinfotec.shared

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    private const val DEFAULT_DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss"
    private const val DEFAULT_DATE_PATTERN = "yyyyMMdd"

    fun stringToDateTime(stringDate: String, pattern: String? = null): LocalDateTime {
        val format = DateTimeFormatter.ofPattern(pattern ?: DEFAULT_DATETIME_PATTERN)
        return LocalDateTime.parse(stringDate, format)
    }

    fun stringToDate(stringDate: String, pattern: String? = null): LocalDate? {
        if (stringDate.length != 8 || stringDate == "00000000") return null
        val format = DateTimeFormatter.ofPattern(pattern ?: DEFAULT_DATE_PATTERN)
        return LocalDate.parse(stringDate, format)
    }


}