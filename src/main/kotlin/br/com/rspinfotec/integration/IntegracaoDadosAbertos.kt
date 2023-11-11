package br.com.rspinfotec.integration

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import kotlinx.coroutines.flow.Flow

@Client("https://dadosabertos.rfb.gov.br", path = "CNPJ")
interface IntegracaoDadosAbertos {
    @Get("/{fileName}")
    fun downloadFile(fileName: String): Flow<ByteArray>
}