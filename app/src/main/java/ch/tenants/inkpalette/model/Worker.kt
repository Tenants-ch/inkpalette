package ch.tenants.inkpalette.model

import CostModel
import WorkerCostModel
import ch.tenants.inkpalette.R


enum class Worker(
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
        WorkerCostModel(10, worker = PERSON),
        WorkerCostModel(10, worker = PERSON)
    ),
    FACTORY(
        order = 3,
        iconResourceId = R.drawable.ic_baseline_factory_24,
        WorkerCostModel(10, worker = CHURCH),
        WorkerCostModel(10, worker = CHURCH)
    ),
    OFFICE(
        order = 4,
        iconResourceId = R.drawable.ic_baseline_business_24,
        WorkerCostModel(10, worker = FACTORY),
        WorkerCostModel(10, worker = FACTORY)
    ),
    CASTLE(
        order = 5,
        iconResourceId = R.drawable.ic_baseline_castle_24,
        WorkerCostModel(10, worker = OFFICE),
        WorkerCostModel(10, worker = OFFICE)
    ),
    FINANCES(
        order = 6,
        iconResourceId = R.drawable.ic_baseline_account_balance_24,
        WorkerCostModel(10, worker = CASTLE),
        WorkerCostModel(10, worker = CASTLE)
    ),
    FIELD(
        order = 7,
        iconResourceId = R.drawable.ic_baseline_agriculture_24,
        WorkerCostModel(10, worker = FINANCES),
        WorkerCostModel(10, worker = FINANCES)
    ),
    RESTAURANT(
        order = 10,
        iconResourceId = R.drawable.ic_baseline_restaurant_24,
        WorkerCostModel(10, worker = FIELD),
        WorkerCostModel(10, worker = FIELD)
    ),
    GROCERYSTORE(
        order = 11,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, worker = RESTAURANT),
        WorkerCostModel(10, worker = RESTAURANT)
    ),
    GYM(
        order = 9,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, worker = GROCERYSTORE),
        WorkerCostModel(10, worker = GROCERYSTORE)
    ),
    DISCO(
        order = 8,
        R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, worker = GYM),
        WorkerCostModel(10, worker = GYM)
    ),
}