package ch.tenants.inkpalette.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import ch.tenants.inkpalette.model.Worker

@ProvidedTypeConverter
class WorkerConverter {
    @TypeConverter
    fun toWorker(value: Int): Worker = enumValues<Worker>()[value]
    @TypeConverter
    fun fromWorker(value: Worker): Int = value.ordinal
}