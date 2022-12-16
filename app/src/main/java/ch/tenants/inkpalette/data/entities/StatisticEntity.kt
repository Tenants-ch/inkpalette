package ch.tenants.inkpalette.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.tenants.inkpalette.model.Statistic
import ch.tenants.inkpalette.model.enums.StatisticEnum

@Entity(tableName = "statistic")
class StatisticEntity(
    @PrimaryKey val statisticEnum: StatisticEnum,
    @ColumnInfo(name = "quantity") var quantity: Int = 0,
)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<StatisticEntity>.asDomainModel(): List<Statistic> {
    return map {
        Statistic(
            statisticEnum = it.statisticEnum,
            quantity = it.quantity
        )
    }
}