package ch.tenants.inkpalette.data

import androidx.room.Transaction
import ch.tenants.inkpalette.data.entities.StatisticEntity
import ch.tenants.inkpalette.data.entities.asDomainModel
import ch.tenants.inkpalette.model.enums.StatisticEnum

class StatisticRepository(private val database: AppDatabase) {

    fun getAddAndUpdateStat(enum: StatisticEnum) {
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

    fun getAll() = database.statisticDao.getAll().asDomainModel()

    fun initStatistics() = database.statisticDao.initStatistics()

}