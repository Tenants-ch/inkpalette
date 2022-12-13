package ch.tenants.inkpalette.model

import CostModel
import WorkerCostModel
import ch.tenants.inkpalette.R


enum class WorkerEnum(
    val order: Int,
    val iconResourceId: Int,
    val upgradeCost: CostModel,
    var buyCost: CostModel? = null
) {
    PERSON(
        order = 1,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
        CostModel(3),
                CostModel (3)
    ),
    CHURCH(
        order = 2,
        iconResourceId = R.drawable.ic_baseline_church_24,
        WorkerCostModel(10, workerEnum = PERSON),
        WorkerCostModel(10, workerEnum = PERSON)
    ),
    FACTORY(
        order = 3,
        iconResourceId = R.drawable.ic_baseline_factory_24,
        WorkerCostModel(10, workerEnum = CHURCH),
        WorkerCostModel(10, workerEnum = CHURCH)
    ),
    OFFICE(
        order = 4,
        iconResourceId = R.drawable.ic_baseline_business_24,
        WorkerCostModel(10, workerEnum = FACTORY),
        WorkerCostModel(10, workerEnum = FACTORY)
    ),
    CASTLE(
        order = 5,
        iconResourceId = R.drawable.ic_baseline_castle_24,
        WorkerCostModel(10, workerEnum = OFFICE),
        WorkerCostModel(10, workerEnum = OFFICE)
    ),
    FINANCES(
        order = 6,
        iconResourceId = R.drawable.ic_baseline_account_balance_24,
        WorkerCostModel(10, workerEnum = CASTLE),
        WorkerCostModel(10, workerEnum = CASTLE)
    ),
    FIELD(
        order = 7,
        iconResourceId = R.drawable.ic_baseline_agriculture_24,
        WorkerCostModel(10, workerEnum = FINANCES),
        WorkerCostModel(10, workerEnum = FINANCES)
    ),
    RESTAURANT(
        order = 10,
        iconResourceId = R.drawable.ic_baseline_restaurant_24,
        WorkerCostModel(10, workerEnum = FIELD),
        WorkerCostModel(10, workerEnum = FIELD)
    ),
    GROCERYSTORE(
        order = 11,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, workerEnum = RESTAURANT),
        WorkerCostModel(10, workerEnum = RESTAURANT)
    ),
    GYM(
        order = 9,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, workerEnum = GROCERYSTORE),
        WorkerCostModel(10, workerEnum = GROCERYSTORE)
    ),
    DISCO(
        order = 8,
        R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, workerEnum = GYM),
        WorkerCostModel(10, workerEnum = GYM)
    ),
}