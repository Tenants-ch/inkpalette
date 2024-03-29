package ch.tenants.inkpalette.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.tenants.inkpalette.model.collectable.Collectable
import ch.tenants.inkpalette.model.enums.AttributeEnum
import ch.tenants.inkpalette.model.enums.ColorEnum

@Entity(tableName = "collectable")
open class CollectableEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "unlocked") val unlocked: Boolean = false,
    @ColumnInfo(name = "total_collected") val totalCollected: Int = 0,
    @ColumnInfo(name = "quantity") var quantity: Int = 0,
    @ColumnInfo(name = "color") open val color: ColorEnum,
    @ColumnInfo(name = "section") val section: Int = 1,
    @ColumnInfo(name = "storage") val storage: Int = 500,
    @ColumnInfo(name = "ration") val ration: Int = 1000,
    @ColumnInfo(name = "level") var level: Int = 1,
    @ColumnInfo(name = "intermediateStorage") val intermediateStorage: Int = 10,
    @ColumnInfo(name = "costToBuy") val costToBuy: Int = 10,
    @ColumnInfo(name = "neededTicksToCollect") val neededTicksToCollect: Int = 10,
    @ColumnInfo(name = "ticks") var ticks: Int = 0,
    @ColumnInfo(name = "notCollectedCount") var notCollectedCount: Int = 0,
    @ColumnInfo(name = "attributeUpgrade") var attributeUpgrade: AttributeEnum
)

fun CollectableEntity.asDomainModel(): Collectable {
    return Collectable(
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
        attributeUpgrade = attributeUpgrade
    )
}

/**
 * Map DatabaseVideos to domain entities
 */
fun List<CollectableEntity>.asDomainModel(): List<Collectable> {
    return map {
        Collectable(
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
            attributeUpgrade = it.attributeUpgrade
        )
    }
}