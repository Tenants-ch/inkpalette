package ch.tenants.inkpalette.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ch.tenants.inkpalette.model.enums.UpgradeEnum

@ProvidedTypeConverter
class UpgradeEnumConverter {
    @TypeConverter
    fun toUpgrade(value: Int): UpgradeEnum = enumValues<UpgradeEnum>()[value]

    @TypeConverter
    fun fromUpgrade(value: UpgradeEnum): Int = value.ordinal
}