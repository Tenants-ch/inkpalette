package ch.tenants.inkpalette.model

import CostModel
import RealCost
import UpgradeCost
import UpgradeCostModel
import WorkerCost
import WorkerCostModel
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.data.CollectableEntity

open class Collectable(
    val id: Int,
    var unlocked: Boolean,
    val color: ColorEnum,
    var quantity: Int = 0,
    var totalCollected: Int = 0,
    val section: Int = 1,
    val storage: Int = 500,
    val ration: Int = 1000,
    var level: Int = 1,
    val intermediateStorage: Int = 10,
    val costToBuy: Int = 10,
    val neededTicksToCollect: Int = 100,
    var ticks: Int = 0,
    var notCollectedCount: Int = 0,
    var upgrades: List<Collectable> = emptyList()
) {
    open fun navigate(navController: NavController){
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
        if (ticks > 0) {
            var diff: Double = ((neededTicksToCollect.toDouble()) / ticks.toDouble())
            var progress = 100 / diff
            return progress.toInt()
        } else {
            return 0
        }
    }

    fun giveCostForAction(action: Action): RealCost {
        when (action) {
            Action.UNLOCK -> {
                return generateCostObject(getBuyCost())
            }
            Action.UPGRADE -> {
                return generateCostObject(getUpgradeCost())
            }
            else -> {
                return generateCostObject(
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


    fun generateCostObject(costModel: CostModel): RealCost {
        when (costModel) {
            is UpgradeCostModel -> {
                return UpgradeCost(
                    quantity = costModel.quantity * level,
                    colorEnum = costModel.colorEnum ?: color,
                    workerEnum = costModel.upgradeEnum.workerEnum,
                    upgradeEnum = costModel.upgradeEnum
                )
            }
            is WorkerCostModel -> {
                return WorkerCost(
                    quantity = costModel.quantity * level,
                    colorEnum = costModel.colorEnum ?: color,
                    workerEnum = costModel.workerEnum
                )
            }
            else -> {
                return RealCost(
                    quantity = costModel.quantity * level,
                    colorEnum = costModel.colorEnum ?: color,
                )
            }
        }
    }

    fun getCountDisplay(): String {
        return "$quantity  / $storage UPGRADES ${upgrades.size}"
    }

    fun getNotCollectCountDisplay(): String {
        return "$notCollectedCount / $intermediateStorage"
    }

    private fun calculateNewState() {
        if (notCollectedCount >= intermediateStorage) {
            ticks = neededTicksToCollect
        } else if (ticks >= neededTicksToCollect) {
            notCollectedCount += level
            ticks = 0
        }
    }

    fun collect() {
        Log.i("Collectable", "Collect ${quantity}")
        quantity += notCollectedCount
        if (quantity > storage) {
            quantity = storage
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
        }
    }

    open fun asDatabaseModel(): CollectableEntity {
        //Log.i("Collectable", "so Saaaad ${quantity}")
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
            notCollectedCount = notCollectedCount
        )
    }
}
