package ch.tenants.inkpalette.model.collectable

import CostModel
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.data.entities.WorkerEntity
import ch.tenants.inkpalette.model.enums.ColorEnum
import ch.tenants.inkpalette.model.enums.WorkerEnum

open class WorkerCollectable(
    id: Int,
    unlocked: Boolean,
    color: ColorEnum,
    val workerEnum: WorkerEnum,
    val parentId: Int,
    quantity: Int = 0,
    totalCollected: Int = 0,
    section: Int = 1,
    storage: Int = 500,
    ration: Int = 1000,
    level: Int = 1,
    intermediateStorage: Int = 10,
    costToBuy: Int = 10,
    neededTicksToCollect: Int = 100,
    ticks: Int = 0,
    notCollectedCount: Int = 0,
    upgrades: List<Collectable> = emptyList()
) : Collectable(
    id,
    unlocked,
    color,
    quantity,
    totalCollected,
    section,
    storage,
    ration,
    level,
    intermediateStorage,
    costToBuy,
    neededTicksToCollect,
    ticks,
    notCollectedCount,
    upgrades
) {
    override fun getBuyCost(): CostModel {
        return workerEnum.buyCost!!
    }

    override fun getUpgradeCost(): CostModel {
        return workerEnum.upgradeCost
    }

    override fun getIconId(): Int {
        return workerEnum.iconResourceId
    }

    override fun getOrder(): Int {
        return workerEnum.order
    }

    override fun navigate(navController: NavController) {
        val bundle = bundleOf(
            "section" to section + 1,
            "color" to color.ordinal,
            "worker" to workerEnum.ordinal
        )
        navController.navigate(R.id.navigation_section, bundle)
    }

    override fun asDatabaseModel(): WorkerEntity {
        //Log.i("WorkerCollectable", "so Saaaad ${quantity}")
        return WorkerEntity(
            uid = id,
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
            parentId = parentId,
            workerEnum = workerEnum
        )
    }
}