package ch.tenants.inkpalette.model

import CostModel
import RealCost
import UpgradeCost
import UpgradeCostModel
import WorkerCost
import WorkerCostModel
import ch.tenants.inkpalette.data.CollectableEntity

open class Collectable(
    val id: Int,
    var quantity: Int,
    var totalCollected: Int,
    var unlocked: Boolean,
    val color: Colors,
    val section: Int = 1,
    val worker: Worker?,
    val upgrade: Upgrade?,
    val storage: Int = 500,
    val ration: Int = 1000,
    var level: Int = 1,
    val intermediateStorage: Int = 10,
    val costToBuy: Int = 10,
    val neededTicksToCollect: Int = 100,
    var ticks: Int = 0,
    var notCollectedCount: Int = 0
) {

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

    fun getUpgradeCost(): CostModel {
        return upgrade?.upgradeCost ?: (worker?.upgradeCost ?: color.upgradeCost)
    }

    fun getBuyCost(): CostModel {
        return upgrade?.buyCost ?: (worker?.buyCost ?: color.buyCost ?: (CostModel(
            0
        )))
    }


    fun generateCostObject(costModel: CostModel): RealCost {
        when (costModel) {
            is UpgradeCostModel -> {
                return UpgradeCost(
                    quantity = costModel.quantity,
                    colors = costModel.colors ?: color,
                    worker = costModel.upgrade.worker,
                    upgrade = costModel.upgrade
                )
            }
            is WorkerCostModel -> {
                return WorkerCost(
                    quantity = costModel.quantity,
                    colors = costModel.colors ?: color,
                    worker = costModel.worker
                )
            }
            else -> {
                return RealCost(
                    quantity = costModel.quantity,
                    colors = costModel.colors ?: color,
                )
            }
        }
    }

    fun getCountDisplay(): String {
        return "$quantity  / $storage"
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
        quantity += notCollectedCount
        if (quantity > storage) {
            quantity = storage
        }
        notCollectedCount = 0
    }

    fun getOrder(): Int {
        return upgrade?.order ?: (worker?.order ?: color.order)
    }


    fun getIconId(): Int {
        return upgrade?.iconResourceId ?: (worker?.iconResourceId ?: color.iconResourceId)
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

    fun asDatabaseModel(): CollectableEntity {
        return CollectableEntity(
            uid = id,
            quantity = quantity,
            totalCollected = totalCollected,
            unlocked = unlocked,
            color = color,
            section = section,
            worker = worker,
            upgrade = upgrade,
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