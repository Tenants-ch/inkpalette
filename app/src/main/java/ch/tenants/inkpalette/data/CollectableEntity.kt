package ch.tenants.inkpalette.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.tenants.inkpalette.model.Collectable
import ch.tenants.inkpalette.model.Colors
import ch.tenants.inkpalette.model.Upgrade
import ch.tenants.inkpalette.model.Worker

@Entity(tableName = "collectable")
class CollectableEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "unlocked") val unlocked: Boolean,
    @ColumnInfo(name = "total_collected") val totalCollected: Int,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "color") val color: Colors,
    @ColumnInfo(name = "section") val section: Int = 1,
    @ColumnInfo(name = "worker") val worker: Worker?,
    @ColumnInfo(name = "upgrade") val upgrade: Upgrade?,
    @ColumnInfo(name = "storage") val storage: Int = 500,
    @ColumnInfo(name = "ration") val ration: Int = 1000,
    @ColumnInfo(name = "intermediateStorage") val intermediateStorage: Int = 10,
    @ColumnInfo(name = "costToBuy") val costToBuy: Int = 10,
    @ColumnInfo(name = "neededTicksToCollect") val neededTicksToCollect: Int = 100,
    @ColumnInfo(name = "ticks") var ticks: Int = 0,
    @ColumnInfo(name = "notCollectedCount") var notCollectedCount: Int = 0,
)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<CollectableEntity>.asDomainModel(): List<Collectable> {
    return map {
        Collectable(
            id = it.uid,
            count = it.count,
            totalCollected = it.totalCollected,
            unlocked = it.unlocked,
            color = it.color,
            section = it.section,
            worker = it.worker,
            upgrade = it.upgrade,
            storage = it.storage,
            ration = it.ration,
            intermediateStorage = it.intermediateStorage,
            costToBuy = it.costToBuy,
            neededTicksToCollect = it.neededTicksToCollect,
            ticks = it.ticks,
            notCollectedCount = it.notCollectedCount
        )
    }
}