package ch.tenants.inkpalette.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ch.tenants.inkpalette.ui.model.Collectable

class CollectableRepository(private val database: AppDatabase) {
    fun updateCollectable(collectableEntity: CollectableEntity) =
        database.collectableDao.updateCollectable(collectableEntity)

    suspend fun insertCollectable(collectableEntity: CollectableEntity) =
        database.collectableDao.insertCollectable(collectableEntity)

    fun insertAll(vararg collectableEntities: CollectableEntity) =
        database.collectableDao.insertAll(*collectableEntities)

    fun getAllSynced() = database.collectableDao.getAllSync().asDomainModel()

    fun getCollectableBySection(section: Int) =
        Transformations.map(database.collectableDao.loadAllBySection(section)) {
            it.asDomainModel()
        }

    suspend fun deleteAll() {
        Log.i("repository", "deleteall")
        Log.i("repository", database.collectableDao.getAllSync().toString())
        database.collectableDao.getAllSync().forEach {
            Log.i("repository", it.name)
            database.collectableDao.delete(it)
        }
    }

    val collectables: LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.getAll()) {
            it.asDomainModel()
        }
}