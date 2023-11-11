package br.com.rspinfotec.repository

import br.com.rspinfotec.entity.History
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import kotlinx.coroutines.flow.Flow

@Repository
interface HistoryRepository : CoroutineCrudRepository<History, Int>{
    fun findOrderByFinishedInDesc(): Flow<History>
    suspend fun findFirstOrderByIdDesc(): History?
}