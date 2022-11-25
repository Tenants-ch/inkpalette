import ch.tenants.inkpalette.model.CollectableType
import ch.tenants.inkpalette.model.Colors
import ch.tenants.inkpalette.model.Upgrade
import ch.tenants.inkpalette.model.Worker

data class Cost(
    val quantity: Int,
    val costType: CollectableType,
    val colors: Colors? = null,
    var worker: Worker? = null,
    var upgrade: Upgrade? = null
)

open class CostModel(val quantity: Int, val colors: Colors? = null)

open class WorkerCostModel(quantity: Int, val worker: Worker, colors: Colors? = null) :
    CostModel(quantity, colors)

open class UpgradeCostModel(quantity: Int, val upgrade: Upgrade, colors: Colors?) :
    CostModel(quantity, colors)

open class RealCost(val quantity: Int, val colors: Colors) {

}

open class WorkerCost(quantity: Int, colors: Colors, val worker: Worker) :
    RealCost(quantity, colors) {

}

class UpgradeCost(quantity: Int, colors: Colors, worker: Worker, val upgrade: Upgrade) :
    WorkerCost(quantity, colors, worker)