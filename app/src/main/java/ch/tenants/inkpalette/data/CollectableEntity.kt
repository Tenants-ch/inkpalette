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
    @ColumnInfo(name = "upgrade") val upgrade: Upgrade?
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
        )
    }
}