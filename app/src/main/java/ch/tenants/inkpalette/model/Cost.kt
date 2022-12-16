import ch.tenants.inkpalette.model.CollectableType
import ch.tenants.inkpalette.model.enums.ColorEnum
import ch.tenants.inkpalette.model.enums.UpgradeEnum
import ch.tenants.inkpalette.model.enums.WorkerEnum

open class CostModel(var quantity: Int, val colorEnum: ColorEnum? = null)

open class WorkerCostModel(quantity: Int, val workerEnum: WorkerEnum, colorEnum: ColorEnum? = null) :
    CostModel(quantity, colorEnum)

open class UpgradeCostModel(quantity: Int, val upgradeEnum: UpgradeEnum, colorEnum: ColorEnum?) :
    CostModel(quantity, colorEnum)

open class RealCost(val quantity: Int, val colorEnum: ColorEnum) {
    open fun getCostIcon(): Int {
        return colorEnum.iconResourceId
    }
}

open class WorkerCost(quantity: Int, colorEnum: ColorEnum, val workerEnum: WorkerEnum) :
    RealCost(quantity, colorEnum) {
    override fun getCostIcon(): Int {
        return workerEnum.iconResourceId
    }
}

class UpgradeCost(quantity: Int, colorEnum: ColorEnum, workerEnum: WorkerEnum, val upgradeEnum: UpgradeEnum) :
    WorkerCost(quantity, colorEnum, workerEnum) {
    override fun getCostIcon(): Int {
        return upgradeEnum.iconResourceId
    }
}