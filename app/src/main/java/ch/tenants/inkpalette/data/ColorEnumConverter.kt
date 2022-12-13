package ch.tenants.inkpalette.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ch.tenants.inkpalette.model.ColorEnum

@ProvidedTypeConverter
class ColorEnumConverter {
    @TypeConverter
    fun toColors(value: Int): ColorEnum = enumValues<ColorEnum>()[value]
    @TypeConverter
    fun fromColors(value: ColorEnum): Int = value.ordinal
}