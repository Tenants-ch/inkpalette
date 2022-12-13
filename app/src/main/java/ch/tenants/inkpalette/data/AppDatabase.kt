package ch.tenants.inkpalette.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CollectableEntity::class, WorkerEntity::class, UpgradeEntity::class],
    version = 5, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val collectableDao: CollectableDao

    companion object {
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"
                    )
                        .fallbackToDestructiveMigration() //TODO: This has to be removed before you go to Production!!!!!
                        .build()
                }
            }
            return INSTANCE
        }
    }
}