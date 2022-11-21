package ch.tenants.inkpalette.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ch.tenants.inkpalette.model.Upgrade

@ProvidedTypeConverter
class UpgradeConverter {
    @TypeConverter
    fun toUpgrade(value: Int): Upgrade = enumValues<Upgrade>()[value]

    @TypeConverter
    fun fromUpgrade(value: Upgrade): Int = value.ordinal
}