package ch.tenants.inkpalette.data

import androidx.room.Embedded
import androidx.room.Relation
import ch.tenants.inkpalette.data.entities.UpgradeEntity
import ch.tenants.inkpalette.data.entities.WorkerEntity

data class WorkerWithUpgrades(
    @Embedded val collectableEntity: WorkerEntity,
    @Relation(
        parentColumn = "uid",
        entityColumn = "parentId"
    )
    val upgrades: List<UpgradeEntity>
)