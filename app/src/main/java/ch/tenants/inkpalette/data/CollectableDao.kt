package ch.tenants.inkpalette.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.tenants.inkpalette.data.entities.CollectableEntity
import ch.tenants.inkpalette.data.entities.UpgradeEntity
import ch.tenants.inkpalette.data.entities.WorkerEntity
import ch.tenants.inkpalette.model.enums.ColorEnum
import ch.tenants.inkpalette.model.enums.UpgradeEnum
import ch.tenants.inkpalette.model.enums.WorkerEnum

@Dao
interface CollectableDao {

    @Transaction
    @Query("SELECT * FROM collectable")
    fun getColorWithWorker(): LiveData<List<ColorWithWorkers>>

    @Transaction
    @Query("SELECT * FROM worker WHERE worker.color LIKE :color")
    fun getWorkerWithUpgrades(color: ColorEnum): LiveData<List<WorkerWithUpgrades>>

    @Query("SELECT * FROM collectable")
    fun getAllCollectables(): List<CollectableEntity>

    @Query("SELECT * FROM worker WHERE section LIKE :section AND color LIKE :color")
    fun loadAllBySectionAndColor(section: Int, color: ColorEnum): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable WHERE section = 1 AND color LIKE :color")
    fun getCollectable(color: ColorEnum): CollectableEntity

    @Query("SELECT * FROM worker WHERE section = 2 AND color LIKE :color AND worker LIKE :workerEnum")
    fun getWorker(color: ColorEnum, workerEnum: WorkerEnum): WorkerEntity

    @Query("SELECT * FROM upgrade WHERE section = 3 AND color LIKE :color AND worker LIKE :workerEnum AND upgrade LIKE :upgradeEnum")
    fun getUpgrade(
        color: ColorEnum,
        workerEnum: WorkerEnum,
        upgradeEnum: UpgradeEnum
    ): UpgradeEntity

    @Query(
        "SELECT EXISTS(SELECT * FROM upgrade WHERE " +
                "quantity >= :quantity AND " +
                "color LIKE :color AND " +
                "worker LIKE :workerEnum AND " +
                "upgrade LIKE :upgradeEnum AND " +
                "section = 3 LIMIT 1)"
    )
    fun hasEnoughUpgradeQuantity(
        quantity: Int,
        color: ColorEnum,
        workerEnum: WorkerEnum,
        upgradeEnum: UpgradeEnum
    ): Boolean

    @Query("SELECT EXISTS(SELECT * FROM collectable LIMIT 1)")
    fun hasCollectables(): Boolean

    @Query("SELECT EXISTS(SELECT * FROM worker LIMIT 1)")
    fun hasWorkers(): Boolean

    @Query("SELECT EXISTS(SELECT * FROM upgrade LIMIT 1)")
    fun hasUpgrades(): Boolean

    @Query(
        "SELECT EXISTS(SELECT * FROM worker WHERE " +
                "quantity >= :quantity AND " +
                "color LIKE :color AND " +
                "worker LIKE :workerEnum AND " +
                "section = 2 LIMIT 1)"
    )
    fun hasEnoughWorkerQuantity(
        quantity: Int,
        color: ColorEnum,
        workerEnum: WorkerEnum
    ): Boolean

    @Query("SELECT EXISTS(SELECT * FROM collectable WHERE quantity >= :quantity AND color = :color AND section = 1 LIMIT 1)")
    fun hasEnoughColorQuantity(quantity: Int, color: ColorEnum): Boolean


    @Query(
        "SELECT * FROM upgrade " +
                "WHERE section LIKE :section AND worker LIKE :workerEnum AND color LIKE :color"
    )
    fun loadUpgradesByValues(
        section: Int,
        color: ColorEnum,
        workerEnum: WorkerEnum
    ): LiveData<List<UpgradeEntity>>

    @Query("SELECT * FROM worker WHERE worker LIKE :workerEnum AND color LIKE :color")
    fun loadAllByColorAndWorker(
        color: ColorEnum,
        workerEnum: WorkerEnum
    ): LiveData<List<WorkerEntity>>

    @Query("SELECT * FROM collectable WHERE color LIKE :color")
    fun loadAllByColor(color: ColorEnum): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable WHERE unlocked = 1")
    fun getAllUnlockedCollectables(): List<CollectableEntity>

    @Query("SELECT * FROM worker WHERE unlocked = 1")
    fun getAllUnlockedWorkers(): List<WorkerEntity>

    @Query("SELECT * FROM upgrade WHERE unlocked = 1")
    fun getAllUnlockedUpgrades(): List<UpgradeEntity>

    @Update
    fun updateCollectable(collectableEntity: CollectableEntity)

    @Update
    fun updateCollectable(workerEntity: WorkerEntity)

    @Update
    fun updateCollectable(upgradeEntity: UpgradeEntity)

    @Delete
    fun delete(collectableEntity: CollectableEntity)

    @Insert
    fun insertCollectable(collectableEntity: CollectableEntity): Long

    @Insert
    fun insertCollectable(workerEntity: WorkerEntity): Long

    @Insert
    fun insertCollectable(upgradeEntity: UpgradeEntity): Long

    @Query("DELETE FROM collectable")
    fun deleteAllCollectables()

    @Query("DELETE FROM worker")
    fun deleteAllWorkers()

    @Query("DELETE FROM upgrade")
    fun deleteAllUpgrades()

    @Transaction
    fun initCollectables() {
        deleteAllCollectables()
        deleteAllWorkers()
        deleteAllUpgrades()
        ColorEnum.values().forEach { color ->
            val colorId = insertCollectable(
                CollectableEntity(
                    unlocked = color == ColorEnum.YELLOW,
                    totalCollected = 0,
                    quantity = 0,
                    color = color,
                    section = 1,
                    attributeUpgrade= color.attributeUpgrade
                )
            )
            WorkerEnum.values().forEach { worker ->
                val workerEnumId = insertCollectable(
                    WorkerEntity(
                        unlocked = color == ColorEnum.YELLOW && worker == WorkerEnum.PERSON,
                        totalCollected = 0,
                        quantity = 0,
                        color = color,
                        section = 2,
                        workerEnum = worker,
                        parentId = colorId.toInt(),
                        attributeUpgrade= worker.attributeUpgrade
                    )
                )
                UpgradeEnum.values().filter { it.workerEnum == worker }.forEach { upgrade ->
                    insertCollectable(
                        UpgradeEntity(
                            unlocked = color == ColorEnum.YELLOW && worker == WorkerEnum.PERSON && upgrade == UpgradeEnum.HAMMER,
                            totalCollected = 0,
                            quantity = 0,
                            color = color,
                            section = 3,
                            workerEnum = worker,
                            upgradeEnum = upgrade,
                            parentId = workerEnumId.toInt(),
                            attributeUpgrade= worker.attributeUpgrade
                        )
                    )
                }
            }
        }
    }

}