package br.com.rspinfotec.repository

import br.com.rspinfotec.entity.Estabelecimento
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import kotlinx.coroutines.flow.Flow

@Repository
interface EstabelecimentoRepository : CoroutineCrudRepository<Estabelecimento, Int> {

    suspend fun findByCnpjBasico(cnpjBase: Int): Estabelecimento?

    suspend fun findByCnpjBasicoAndCnpjOrdemAndCnpjDv(cnpjBase: Int, cnpjOrdem: Int, cnpjDv: Int): Estabelecimento?

//    @Query("select c from Estabelecimento c join c.empresa WHERE c.cnpjBasico = :cnpjBase")
//    suspend fun findCompany(cnpjBase: Int): Estabelecimento?
}