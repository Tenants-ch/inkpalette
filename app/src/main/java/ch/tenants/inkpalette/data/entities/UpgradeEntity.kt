package ch.tenants.inkpalette.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import ch.tenants.inkpalette.model.collectable.UpgradeCollectable
import ch.tenants.inkpalette.model.enums.AttributeEnum
import ch.tenants.inkpalette.model.enums.ColorEnum
import ch.tenants.inkpalette.model.enums.UpgradeEnum
import ch.tenants.inkpalette.model.enums.WorkerEnum

@Entity(tableName = "upgrade")
class UpgradeEntity(
    uid: Int = 0,
    @ColumnInfo(name = "upgrade") val upgradeEnum: UpgradeEnum,
    parentId: Int,
    workerEnum: WorkerEnum,
    unlocked: Boolean,
    totalCollected: Int,
    quantity: Int,
    color: ColorEnum,
    section: Int = 1,
    storage: Int = 500,
    ration: Int = 1000,
    level: Int = 1,
    intermediateStorage: Int = 10,
    costToBuy: Int = 10,
    neededTicksToCollect: Int = 10,
    ticks: Int = 0,
    notCollectedCount: Int = 0,
    attributeUpgrade: AttributeEnum
) : WorkerEntity(
    uid = uid,
    parentId = parentId,
    workerEnum = workerEnum,
    quantity = quantity,
    totalCollected = totalCollected,
    unlocked = unlocked,
    color = color,
    section = section,
    storage = storage,
    ration = ration,
    level = level,
    intermediateStorage = intermediateStorage,
    costToBuy = costToBuy,
    neededTicksToCollect = neededTicksToCollect,
    ticks = ticks,
    notCollectedCount = notCollectedCount,
    attributeUpgrade = attributeUpgrade
)

fun UpgradeEntity.asDomainModel(): UpgradeCollectable {
    return UpgradeCollectable(
        id = uid,
        quantity = quantity,
        totalCollected = totalCollected,
        unlocked = unlocked,
        color = color,
        section = section,
        storage = storage,
        ration = ration,
        level = level,
        intermediateStorage = intermediateStorage,
        costToBuy = costToBuy,
        neededTicksToCollect = neededTicksToCollect,
        ticks = ticks,
        notCollectedCount = notCollectedCount,
        workerEnum = workerEnum,
        parentId = parentId,
        upgradeEnum = upgradeEnum,
        attributeUpgrade = attributeUpgrade
    )
}

/**
 * Map DatabaseVideos to domain entities
 */
fun List<UpgradeEntity>.asDomainModel(): List<UpgradeCollectable> {
    return map {
        UpgradeCollectable(
            id = it.uid,
            quantity = it.quantity,
            totalCollected = it.totalCollected,
            unlocked = it.unlocked,
            color = it.color,
            section = it.section,
            storage = it.storage,
            ration = it.ration,
            level = it.level,
            intermediateStorage = it.intermediateStorage,
            costToBuy = it.costToBuy,
            neededTicksToCollect = it.neededTicksToCollect,
            ticks = it.ticks,
            notCollectedCount = it.notCollectedCount,
            workerEnum = it.workerEnum,
            parentId = it.parentId,
            upgradeEnum = it.upgradeEnum,
            attributeUpgrade = it.attributeUpgrade
        )
    }
}