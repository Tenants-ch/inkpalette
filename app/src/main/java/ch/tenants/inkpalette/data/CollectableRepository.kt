package ch.tenants.inkpalette.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ch.tenants.inkpalette.model.Collectable
import ch.tenants.inkpalette.model.Colors
import ch.tenants.inkpalette.model.Worker

class CollectableRepository(private val database: AppDatabase) {
    fun updateCollectable(collectable: Collectable) =
        database.collectableDao.updateCollectable(collectable.asDatabaseModel())

    suspend fun insertCollectable(collectableEntity: CollectableEntity) =
        database.collectableDao.insertCollectable(collectableEntity)

    fun insertAll(vararg collectableEntities: CollectableEntity) =
        database.collectableDao.insertAll(*collectableEntities)

    fun getAllSynced() = database.collectableDao.getAllSync().asDomainModel()

    fun getCollectablesByValues(
        section: Int,
        color: Colors?,
        worker: Worker?
    ): LiveData<List<Collectable>> {
        return if (section == 3 && worker != null && color != null) {
            getCollectableByAllValues(section, color, worker)
        } else if (section == 2 && color != null) {
            getCollectableBySectionAndColor(section, color)
        } else {
            getCollectableBySection(1)
        }
    }

    fun getAllUnlocked(): List<Collectable> =
        database.collectableDao.getAllUnlocked().asDomainModel()

    fun getCollectableBySection(section: Int) =
        Transformations.map(database.collectableDao.loadAllBySection(section)) {
            it.asDomainModel()
        }

    fun getCollectableBySectionAndColor(section: Int, color: Colors): LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.loadAllBySectionAndColor(section, color)) {
            it.asDomainModel()
        }

    fun getCollectableByAllValues(section: Int, color: Colors, worker: Worker): LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.loadAllByValues(section, color,worker)) {
            it.asDomainModel()
        }

    fun getCollectableByColor(color: Colors): LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.loadAllByColor(color)) {
            it.asDomainModel()
        }

    fun getCollectableByColorAndWorker(color: Colors, worker: Worker): LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.loadAllByColorAndWorker(color, worker)) {
            it.asDomainModel()
        }

    suspend fun deleteAll() {
        database.collectableDao.getAllSync().forEach {
            database.collectableDao.delete(it)
        }
    }

    val collectables: LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.getAll()) {
            it.asDomainModel()
        }
}