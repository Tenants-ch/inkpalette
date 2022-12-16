package ch.tenants.inkpalette.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ch.tenants.inkpalette.model.enums.StatisticEnum

@ProvidedTypeConverter
class StatisticEnumConverter {
    @TypeConverter
    fun toStatistic(value: Int): StatisticEnum = enumValues<StatisticEnum>()[value]
    @TypeConverter
    fun fromStatistic(value: StatisticEnum): Int = value.ordinal
}