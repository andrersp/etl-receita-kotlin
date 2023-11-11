package br.com.rspinfotec.service

import br.com.rspinfotec.entity.History
import br.com.rspinfotec.repository.HistoryRepository
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Singleton
class ServiceHistory(
    private val repositoryHistory: HistoryRepository,

    ) {

    fun showHistory(): Flow<History?> {
        return repositoryHistory.findOrderByFinishedInDesc()
    }

    suspend fun getLastHistory(): History? =
        repositoryHistory.findFirstOrderByIdDesc()

    suspend fun createNewHistory(): History {
        val history = History(status = 2)
        return repositoryHistory.save(history)
    }

    suspend fun updateHistory(historyId: Int, status: Int = 1) {
        val history = repositoryHistory.findById(historyId)
        history?.let {
            history.finishedIn = LocalDateTime.now()
            history.status = status
            repositoryHistory.update(history)
        }

    }


}