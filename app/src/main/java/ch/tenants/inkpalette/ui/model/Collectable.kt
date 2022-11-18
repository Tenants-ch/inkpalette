package ch.tenants.inkpalette.ui.model

import ch.tenants.inkpalette.data.CollectableEntity

class Collectable(
    val id: Int,
    val name: String,
    val iconResourceId: Int,
    val color: Int,
    var count: Int,
    var totalCollected: Int,
    val info: String,
    val unlocked: Boolean,
    val order: Int,
    val section: Int
) {

    fun tick() {
        count++;
    }

    fun asDatabaseModel(): CollectableEntity {
        return CollectableEntity(
            uid = id,
            name = name,
            iconResourceId = iconResourceId,
            color = color,
            count = count,
            totalCollected = totalCollected,
            info = info,
            unlocked = unlocked,
            order = order,
            section = section
        )
    }
}