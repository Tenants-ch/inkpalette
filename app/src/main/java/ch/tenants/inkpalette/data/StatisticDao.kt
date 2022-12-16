package ch.tenants.inkpalette.data

import androidx.room.*
import ch.tenants.inkpalette.data.entities.StatisticEntity
import ch.tenants.inkpalette.model.enums.StatisticEnum

@Dao
interface StatisticDao {

    @Query("SELECT * FROM statistic")
    fun getAll(): List<StatisticEntity>

    @Query("SELECT * FROM statistic WHERE statisticEnum LIKE :statisticEnum")
    fun getByEnum(statisticEnum: StatisticEnum): StatisticEntity

    @Update
    fun update(statisticEntity: StatisticEntity)

    @Delete
    fun delete(statisticEntity: StatisticEntity)

    @Insert
    fun insert(statisticEntity: StatisticEntity): Long

    @Query("SELECT EXISTS(SELECT * FROM statistic LIMIT 1)")
    fun hasStatistics(): Boolean

    @Query("DELETE FROM statistic")
    fun deleteAllStatistics()

    @Transaction
    fun initStatistics() {
        deleteAllStatistics()
        StatisticEnum.values().forEach { statisticEnum ->
            insert(StatisticEntity(statisticEnum))
        }
    }
}