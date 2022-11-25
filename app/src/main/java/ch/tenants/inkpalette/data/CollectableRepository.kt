package ch.tenants.inkpalette.data

import RealCost
import UpgradeCost
import WorkerCost
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Transaction
import ch.tenants.inkpalette.model.Action
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
        Log.i("CollectableRepository", "section -> $section / color -> $color / worker -> $worker")
        return if (section == 3 && worker != null && color != null) {
            getCollectableByAllValues(section, color, worker)
        } else if (section == 2 && color != null) {
            getCollectableBySectionAndColor(section, color)
        } else {
            getCollectableBySection(1)
        }
    }

    fun performActionOnCollectable(collectable: Collectable, action: Action) {
        var realCost = collectable.giveCostForAction(action)
        if (hasQuantityToPerformAction(collectable, action)) {
            collectable.performAction(action)
            payPriceAndUpdateCollectable(collectable, realCost)
        }
    }

    @Transaction
    fun payPriceAndUpdateCollectable(collectable: Collectable, realCost: RealCost) {
        val payCollectable = when (realCost) {
            is UpgradeCost -> {
                database.collectableDao.getCollectable(
                    realCost.colors,
                    realCost.worker,
                    realCost.upgrade
                )
            }
            is WorkerCost -> {
                database.collectableDao.getCollectable(realCost.colors, realCost.worker)

            }
            else -> {
                database.collectableDao.getCollectable(realCost.colors)

            }
        }
        payCollectable.quantity -= realCost.quantity
        if (payCollectable.quantity >= 0) {
            database.collectableDao.updateCollectable(payCollectable)
            database.collectableDao.updateCollectable(collectable.asDatabaseModel())
        }
    }


    fun unlockCollectable(collectable: Collectable) {
        collectable.unlocked = true
        if (collectable.section == 1) {
        }
    }

    fun hasQuantityToPerformAction(collectable: Collectable, action: Action): Boolean {
        return hasEnoughQuantity(collectable.giveCostForAction(action))
    }

    fun hasEnoughQuantity(cost: RealCost): Boolean {
        return when (cost) {
            is UpgradeCost -> {
                database.collectableDao.hasEnoughUpgradeQuantity(
                    cost.quantity,
                    cost.colors,
                    cost.worker,
                    cost.upgrade
                )
            }
            is WorkerCost -> {
                database.collectableDao.hasEnoughWorkerQuantity(
                    cost.quantity,
                    cost.colors,
                    cost.worker,
                )
            }
            else -> {
                database.collectableDao.hasEnoughColorQuantity(
                    cost.quantity,
                    cost.colors
                )
            }
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

    fun getCollectableByAllValues(
        section: Int,
        color: Colors,
        worker: Worker
    ): LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.loadAllByValues(section, color, worker)) {
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