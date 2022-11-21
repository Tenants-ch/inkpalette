package ch.tenants.inkpalette.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ch.tenants.inkpalette.model.Colors

@ProvidedTypeConverter
class ColorsConverter {
    @TypeConverter
    fun toColors(value: Int): Colors = enumValues<Colors>()[value]
    @TypeConverter
    fun fromColors(value: Colors): Int = value.ordinal
}