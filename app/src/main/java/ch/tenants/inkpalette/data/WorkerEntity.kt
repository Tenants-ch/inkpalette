package ch.tenants.inkpalette.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import ch.tenants.inkpalette.model.ColorEnum
import ch.tenants.inkpalette.model.WorkerCollectable
import ch.tenants.inkpalette.model.WorkerEnum

@Entity(tableName = "worker")
open class WorkerEntity(
    uid: Int = 0,
    @ColumnInfo(name = "parentId") var parentId: Int,
    @ColumnInfo(name = "worker") val workerEnum: WorkerEnum,
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
) : CollectableEntity(
    uid = uid,
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
) {

}

fun WorkerEntity.asDomainModel(): WorkerCollectable {
    return WorkerCollectable(
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
        parentId = parentId
    )
}

/**
 * Map DatabaseVideos to domain entities
 */
fun List<WorkerEntity>.asDomainModel(): List<WorkerCollectable> {
    return map {
        WorkerCollectable(
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
            parentId = it.parentId
        )
    }
}