package ch.tenants.inkpalette.model.collectable

import CostModel
import androidx.navigation.NavController
import ch.tenants.inkpalette.data.entities.UpgradeEntity
import ch.tenants.inkpalette.model.enums.AttributeEnum
import ch.tenants.inkpalette.model.enums.ColorEnum
import ch.tenants.inkpalette.model.enums.UpgradeEnum
import ch.tenants.inkpalette.model.enums.WorkerEnum

class UpgradeCollectable(
    id: Int,
    unlocked: Boolean,
    color: ColorEnum,
    workerEnum: WorkerEnum,
    val upgradeEnum: UpgradeEnum,
    parentId: Int,
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
    upgrades: List<Collectable> = emptyList(),
    attributeUpgrade: AttributeEnum
) : WorkerCollectable(
    id,
    unlocked,
    color,
    workerEnum,
    parentId,
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
    upgrades,
    attributeUpgrade
) {
    override fun navigate(navController: NavController) {
        navController.navigate(upgradeEnum.navigation)
    }

    override fun getBuyCost(): CostModel {
        return upgradeEnum.buyCost!!
    }

    override fun getUpgradeCost(): CostModel {
        return upgradeEnum.upgradeCost
    }

    override fun getIconId(): Int {
        return upgradeEnum.iconResourceId
    }

    override fun getOrder(): Int {
        return upgradeEnum.order
    }

    override fun asDatabaseModel(): UpgradeEntity {
        //Log.i("UpgradeCollectable", "so Saaaad ${quantity}")
        return UpgradeEntity(
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
            workerEnum = workerEnum,
            upgradeEnum = upgradeEnum,
            attributeUpgrade = attributeUpgrade
        )
    }
}