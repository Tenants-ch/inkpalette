package ch.tenants.inkpalette.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.tenants.inkpalette.ui.model.Collectable

@Entity(tableName = "collectable")
class CollectableEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "order") val order: Int,
    @ColumnInfo(name = "unlocked") val unlocked: Boolean,
    @ColumnInfo(name = "icon") val iconResourceId: Int,
    @ColumnInfo(name = "color") val color: Int,
    @ColumnInfo(name = "info") val info: String,
    @ColumnInfo(name = "total_collected") val totalCollected: Int,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "section") val section: Int,
)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<CollectableEntity>.asDomainModel(): List<Collectable> {
    return map {
        Collectable(
            id = it.uid,
            name = it.name,
            iconResourceId = it.iconResourceId,
            color = it.color,
            count = it.count,
            totalCollected = it.totalCollected,
            info = it.info,
            unlocked = it.unlocked,
            order = it.order,
            section = it.section
        )
    }
}