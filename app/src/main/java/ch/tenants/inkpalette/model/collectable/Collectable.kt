package ch.tenants.inkpalette.model.collectable

import CostModel
import RealCost
import UpgradeCost
import UpgradeCostModel
import WorkerCost
import WorkerCostModel
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.data.entities.CollectableEntity
import ch.tenants.inkpalette.model.Action
import ch.tenants.inkpalette.model.enums.AttributeEnum
import ch.tenants.inkpalette.model.enums.ColorEnum

open class Collectable(
    val id: Int,
    var unlocked: Boolean,
    val color: ColorEnum,
    var quantity: Int = 0,
    var totalCollected: Int = 0,
    val section: Int = 1,
    val storage: Int = 500,      //storage Upgrade
    val ration: Int = 1000,      //ratio upgrade
    var level: Int = 1,
    val intermediateStorage: Int = 10,  // intermediate storage upgrade
    val costToBuy: Int = 10,
    val neededTicksToCollect: Int = 100,  //ticks needed to collect upgrade
    var ticks: Int = 0,
    var notCollectedCount: Int = 0,        // cost to upgrade upgrade
    var attributeUpgrade: AttributeEnum,
    var upgrades: List<Collectable> = emptyList()
) {
    open fun navigate(navController: NavController) {
        val bundle = bundleOf(
            "section" to section + 1,
            "color" to color.ordinal
        )
        navController.navigate(R.id.navigation_section, bundle)
    }

    fun tick() {
        ticks++
        calculateNewState()
    }

    fun getProgress(): Int {
        return if (ticks > 0) {
            val diff: Double = ((getNeededTicksWithUpgrades().toDouble()) / ticks.toDouble())
            val progress = 100 / diff
            progress.toInt()
        } else {
            0
        }
    }

    fun giveCostForAction(action: Action): RealCost {
        return when (action) {
            Action.UNLOCK -> {
                generateCostObject(getBuyCost())
            }
            Action.UPGRADE -> {
                generateCostObject(getUpgradeCost())
            }
            else -> {
                generateCostObject(
                    CostModel(
                        0
                    )
                )
            }
        }
    }

    open fun getUpgradeCost(): CostModel {
        return color.upgradeCost
    }

    open fun getBuyCost(): CostModel {
        return color.buyCost ?: (CostModel(
            0
        ))
    }

    private fun generateCostObject(costModel: CostModel): RealCost {
        var quantity = costModel.quantity - getAttributeLevels(AttributeEnum.UPGRADE_COST)
        quantity = if (quantity > 0) quantity else 1
        when (costModel) {
            is UpgradeCostModel -> {
                return UpgradeCost(
                    quantity = quantity * level,
                    colorEnum = costModel.colorEnum ?: color,
                    workerEnum = costModel.upgradeEnum.workerEnum,
                    upgradeEnum = costModel.upgradeEnum
                )
            }
            is WorkerCostModel -> {
                return WorkerCost(
                    quantity = quantity * level,
                    colorEnum = costModel.colorEnum ?: color,
                    workerEnum = costModel.workerEnum
                )
            }
            else -> {
                return RealCost(
                    quantity = quantity * level,
                    colorEnum = costModel.colorEnum ?: color,
                )
            }
        }
    }

    fun getCountDisplay(): String {
        return "$quantity/${getStorageWithUpgrades()}"
    }

    fun getNotCollectCountDisplay(): String {
        return "$notCollectedCount/${getIntermediateStorageWithUpgrades()}"
    }

    private fun calculateNewState() {
        if (notCollectedCount >= getIntermediateStorageWithUpgrades()) {
            ticks = getNeededTicksWithUpgrades()
        } else if (ticks >= getNeededTicksWithUpgrades()) {
            notCollectedCount += level
            ticks = 0
        }
    }

    fun getStorageWithUpgrades(): Int {
        return storage + (getAttributeLevels(AttributeEnum.STORAGE))
    }


    fun getNeededTicksWithUpgrades(): Int {
        return neededTicksToCollect - (getAttributeLevels(AttributeEnum.NEEDED_TICKS))
    }


    fun getIntermediateStorageWithUpgrades(): Int {
        return intermediateStorage + (getAttributeLevels(AttributeEnum.INTERMEDIATE_STORAGE))
    }

    fun getAttributeLevels(attributeEnum: AttributeEnum): Int {
        return this.upgrades.filter { it -> it.attributeUpgrade == attributeEnum }
            .sumOf { it.level }
    }

    fun collect() {
        quantity += notCollectedCount
        if (quantity > getStorageWithUpgrades()) {
            quantity = getStorageWithUpgrades()
        }
        notCollectedCount = 0
    }

    open fun getOrder(): Int {
        return color.order
    }


    open fun getIconId(): Int {
        return color.iconResourceId
    }

    fun performAction(action: Action) {
        when (action) {
            Action.UNLOCK -> {
                unlocked = true
            }
            Action.UPGRADE -> {
                level += 1
            }
            Action.CONVERT -> {
                quantity -= ration
            }
            else -> {}
        }
    }

    open fun asDatabaseModel(): CollectableEntity {
        return CollectableEntity(
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
            attributeUpgrade = attributeUpgrade
        )
    }
}
