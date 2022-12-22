package ch.tenants.inkpalette.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ch.tenants.inkpalette.data.entities.CollectableEntity
import ch.tenants.inkpalette.data.entities.StatisticEntity
import ch.tenants.inkpalette.data.entities.UpgradeEntity
import ch.tenants.inkpalette.data.entities.WorkerEntity
import java.util.concurrent.Executors

@Database(
    entities = [CollectableEntity::class, WorkerEntity::class, UpgradeEntity::class, StatisticEntity::class],
    version = 7, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val collectableDao: CollectableDao
    abstract val statisticDao: StatisticDao

    companion object {
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"
                    ).addCallback(object : Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            // moving to a new thread
                            Executors.newSingleThreadExecutor().execute {
                                if (!INSTANCE.collectableDao.hasCollectables() || !INSTANCE.collectableDao.hasWorkers() || !INSTANCE.collectableDao.hasUpgrades()) {
                                    INSTANCE.collectableDao.initCollectables()
                                }
                                if (!INSTANCE.statisticDao.hasStatistics()) {
                                    INSTANCE.statisticDao.initStatistics()
                                }
                            }
                        }
                    })
                        .fallbackToDestructiveMigration() //TODO: This has to be removed before you go to Production!!!!!
                        .build()
                }
            }
            return INSTANCE
        }
    }
}