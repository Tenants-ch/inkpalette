package ch.tenants.inkpalette.data

import androidx.lifecycle.Transformations
import androidx.room.Transaction
import ch.tenants.inkpalette.data.entities.asDomainModel
import ch.tenants.inkpalette.model.enums.StatisticEnum

class StatisticRepository(private val database: AppDatabase) {

    private fun getAddAndUpdateStat(enum: StatisticEnum) {
        val stat = database.statisticDao.getByEnum(enum)
        stat.quantity += 1
        database.statisticDao.update(stat)
    }

    @Transaction
    fun addStats(stats: List<StatisticEnum>) {
        stats.forEach { enum ->
            getAddAndUpdateStat(enum)
        }

    }

    fun getAll() =
        Transformations.map(
            database.statisticDao.getAll()

        ) {
            it.asDomainModel()
        }


    fun initStatistics() = database.statisticDao.initStatistics()

}