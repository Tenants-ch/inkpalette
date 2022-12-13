package ch.tenants.inkpalette.data

import RealCost
import UpgradeCost
import WorkerCost
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Transaction
import ch.tenants.inkpalette.model.*

class CollectableRepository(private val database: AppDatabase) {

    fun getColorWithWorker(): LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.getColorWithWorker()) {
            it.map { colorWithWorker ->
                mapColorWithWorker(colorWithWorker)
            }
        }

    private fun mapColorWithWorker(colorWithWorker: ColorWithWorker): Collectable {
        val col = colorWithWorker.collectableEntity.asDomainModel()
        col.upgrades = colorWithWorker.upgrades.asDomainModel()
        return col
    }

    fun getWorkerWithUpgrades(color: ColorEnum): LiveData<List<Collectable>> =
        Transformations.map(database.collectableDao.getWorkerWithUpgrades(color)) {
            it.map { workerWithUpgrades ->
                mapWorkerWithUpgrades(workerWithUpgrades)
            }
        }

    private fun mapWorkerWithUpgrades(workerWithUpgrades: WorkerWithUpgrades): Collectable {
        val col = workerWithUpgrades.collectableEntity.asDomainModel()
        col.upgrades = workerWithUpgrades.upgrades.asDomainModel()
        return col
    }

    fun updateCollectable(collectable: Collectable) {
        val ip = collectable.asDatabaseModel()
        Log.i("CollectableRepository", "collectable ${ip}")
        when (ip) {
            is UpgradeEntity -> database.collectableDao.updateCollectable(ip)
            is WorkerEntity -> database.collectableDao.updateCollectable(ip)
            else -> database.collectableDao.updateCollectable(ip)
        }
    }

    fun insertCollectable(collectableEntity: CollectableEntity): Long =
        database.collectableDao.insertCollectable(collectableEntity)

    fun insertCollectable(workerEntity: WorkerEntity): Long =
        database.collectableDao.insertCollectable(workerEntity)

    fun insertCollectable(upgradeEntity: UpgradeEntity): Long =
        database.collectableDao.insertCollectable(upgradeEntity)

    fun getCollectablesByValues(
        section: Int,
        color: ColorEnum?,
        workerEnum: WorkerEnum?
    ): LiveData<List<Collectable>> {
        return if (section == 3 && workerEnum != null && color != null) {
            getCollectableByAllValues(section, color, workerEnum)
        } else if (section == 2 && color != null) {
            getWorkerWithUpgrades(color)
        } else {
            getColorWithWorker()
        }
    }

    fun performActionOnCollectable(collectable: Collectable, action: Action) {
        val realCost = collectable.giveCostForAction(action)
        if (hasQuantityToPerformAction(collectable, action)) {
            collectable.performAction(action)
            payPriceAndUpdateCollectable(collectable, realCost)
        }
    }

    @Transaction
    fun payPriceAndUpdateCollectable(collectable: Collectable, realCost: RealCost) {
        val payCollectable: Collectable = when (realCost) {
            is UpgradeCost -> {
                database.collectableDao.getUpgrade(
                    realCost.colorEnum,
                    realCost.workerEnum,
                    realCost.upgradeEnum
                ).asDomainModel()
            }
            is WorkerCost -> {
                database.collectableDao.getWorker(realCost.colorEnum, realCost.workerEnum)
                    .asDomainModel()

            }
            else -> {
                database.collectableDao.getCollectable(realCost.colorEnum).asDomainModel()

            }
        }
        if (payCollectable.id == collectable.id) {
            collectable.quantity -= realCost.quantity
            if (collectable.quantity >= 0) {
                updateCollectable(collectable)
            }
        } else {
            payCollectable.quantity -= realCost.quantity
            if (payCollectable.quantity >= 0) {
                updateCollectable(payCollectable)
                updateCollectable(collectable)
            }
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
                    cost.colorEnum,
                    cost.workerEnum,
                    cost.upgradeEnum
                )
            }
            is WorkerCost -> {
                database.collectableDao.hasEnoughWorkerQuantity(
                    cost.quantity,
                    cost.colorEnum,
                    cost.workerEnum,
                )
            }
            else -> {
                database.collectableDao.hasEnoughColorQuantity(
                    cost.quantity,
                    cost.colorEnum
                )
            }
        }
    }

    fun getAllUnlockedCollectables(): List<Collectable> =
        database.collectableDao.getAllUnlockedCollectables().asDomainModel()

    fun getAllUnlockedWorkers(): List<WorkerCollectable> =
        database.collectableDao.getAllUnlockedWorkers().asDomainModel()

    fun getAllUnlockedUpgrades(): List<UpgradeCollectable> =
        database.collectableDao.getAllUnlockedUpgrades().asDomainModel()


    fun getCollectableByAllValues(
        section: Int,
        color: ColorEnum,
        workerEnum: WorkerEnum
    ): LiveData<List<Collectable>> =
        Transformations.map(
            database.collectableDao.loadUpgradesByValues(
                section,
                color,
                workerEnum
            )
        ) {
            it.asDomainModel()
        }

    fun deleteAll() {
        database.collectableDao.getAllCollectables().forEach {
            database.collectableDao.delete(it)
        }
    }

}