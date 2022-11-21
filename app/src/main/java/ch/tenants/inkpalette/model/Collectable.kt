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
    val upgrade: Upgrade?
) {

    fun tick() {
        count++
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
        )
    }
}