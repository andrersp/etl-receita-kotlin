package br.com.rspinfotec.service

import br.com.rspinfotec.extraction.Extraction
import kotlinx.coroutines.CoroutineScope

data class DownloadParams(
    val service: Extraction,
    val files: List<String>,
    val filePrefix: String,
    val tableName: String,
    val scope: CoroutineScope
)
