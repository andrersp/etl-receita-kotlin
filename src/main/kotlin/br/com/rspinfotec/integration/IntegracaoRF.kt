package br.com.rspinfotec.integration

import br.com.rspinfotec.integration.schemas.ResponseReceitaDTO
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client

@Client("https://dados.gov.br", path = "api")
@Header(
    name = USER_AGENT, value = "Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/118.0"
)
interface IntegracaoRF {

    @Get("/publico/conjuntos-dados/cadastro-nacional-da-pessoa-juridica---cnpj")
    suspend fun obterConjuntoDeDados(): ResponseReceitaDTO
}