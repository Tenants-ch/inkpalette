package ch.tenants.inkpalette.data

import androidx.room.Embedded
import androidx.room.Relation
import ch.tenants.inkpalette.data.entities.CollectableEntity
import ch.tenants.inkpalette.data.entities.WorkerEntity

data class ColorWithWorkers(
    @Embedded val collectableEntity: CollectableEntity,
    @Relation(
        parentColumn = "uid",
        entityColumn = "parentId"
    )
    val upgrades: List<WorkerEntity>
)
