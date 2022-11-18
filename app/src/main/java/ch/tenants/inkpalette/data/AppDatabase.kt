package ch.tenants.inkpalette.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CollectableEntity::class),
    version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val collectableDao: CollectableDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "app-database").build()
        }
    }
    return INSTANCE
}