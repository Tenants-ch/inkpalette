package ch.tenants.inkpalette.model

import ch.tenants.inkpalette.data.CollectableEntity

open class Collectable(
    val id: Int,
    var count: Int,
    var totalCollected: Int,
    val unlocked: Boolean,
    val color: Colors,
    val section: Int = 1,
    val worker: Worker?,
    val upgrade: Upgrade?,
    val storage: Int = 500,
    val ration: Int = 1000,
    val intermediateStorage: Int = 10,
    val costToBuy: Int = 10,
    val neededTicksToCollect: Int = 100,
    var ticks: Int = 0,
    var notCollectedCount: Int = 0,
) {

    fun tick() {
        ticks++
        calculateNewState()
    }

    fun getProgress(): Int {
        return if (ticks > 0) 100 / (neededTicksToCollect / ticks) else 0
    }

    fun getCountDisplay(): String {
        return "$count  / $storage"
    }

    fun getNotCollectCountDisplay(): String {
        return "$notCollectedCount / $intermediateStorage"
    }

    private fun calculateNewState() {
        if (notCollectedCount >= intermediateStorage) {
            ticks = neededTicksToCollect
        } else if (ticks >= neededTicksToCollect) {
            notCollectedCount++
            ticks = 0
        }
    }

    fun collect() {
        count += notCollectedCount
        if (count > storage) {
            count = storage
        }
        notCollectedCount = 0
    }

    fun getOrder(): Int {
        return upgrade?.order ?: (worker?.order ?: color.order)
    }


    fun getIconId(): Int {
        return upgrade?.iconResourceId ?: (worker?.iconResourceId ?: color.iconResourceId)
    }

    fun asDatabaseModel(): CollectableEntity {
        return CollectableEntity(
            uid = id,
            count = count,
            totalCollected = totalCollected,
            unlocked = unlocked,
            color = color,
            section = section,
            worker = worker,
            upgrade = upgrade,
            storage = storage,
            ration = ration,
            intermediateStorage = intermediateStorage,
            costToBuy = costToBuy,
            neededTicksToCollect = neededTicksToCollect,
            ticks = ticks,
            notCollectedCount = notCollectedCount
        )
    }
}