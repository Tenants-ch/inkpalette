package ch.tenants.inkpalette.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ch.tenants.inkpalette.model.enums.WorkerEnum

@ProvidedTypeConverter
class WorkerEnumConverter {
    @TypeConverter
    fun toWorker(value: Int): WorkerEnum = enumValues<WorkerEnum>()[value]
    @TypeConverter
    fun fromWorker(value: WorkerEnum): Int = value.ordinal
}