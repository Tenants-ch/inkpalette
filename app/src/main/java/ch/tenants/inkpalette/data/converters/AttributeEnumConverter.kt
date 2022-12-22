package ch.tenants.inkpalette.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ch.tenants.inkpalette.model.enums.AttributeEnum

@ProvidedTypeConverter
class AttributeEnumConverter {
    @TypeConverter
    fun toAttribute(value: Int): AttributeEnum = enumValues<AttributeEnum>()[value]

    @TypeConverter
    fun fromAttribute(value: AttributeEnum): Int = value.ordinal
}