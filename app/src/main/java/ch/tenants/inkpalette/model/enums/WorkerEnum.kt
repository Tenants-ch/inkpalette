package ch.tenants.inkpalette.model.enums

import CostModel
import WorkerCostModel
import ch.tenants.inkpalette.R


enum class WorkerEnum(
    val order: Int,
    val iconResourceId: Int,
    val upgradeCost: CostModel,
    var buyCost: CostModel? = null,
    var attributeUpgrade: AttributeEnum
) {
    PERSON(
        order = 1,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
        CostModel(3),
        CostModel(3),
        attributeUpgrade = AttributeEnum.STORAGE
    ),
    CHURCH(
        order = 2,
        iconResourceId = R.drawable.ic_baseline_church_24,
        WorkerCostModel(10, workerEnum = PERSON),
        WorkerCostModel(10, workerEnum = PERSON),
        attributeUpgrade = AttributeEnum.INTERMEDIATE_STORAGE
    ),
    FACTORY(
        order = 3,
        iconResourceId = R.drawable.ic_baseline_factory_24,
        WorkerCostModel(10, workerEnum = CHURCH),
        WorkerCostModel(10, workerEnum = CHURCH),
        attributeUpgrade = AttributeEnum.UPGRADE_COST
    ),
    OFFICE(
        order = 4,
        iconResourceId = R.drawable.ic_baseline_business_24,
        WorkerCostModel(10, workerEnum = FACTORY),
        WorkerCostModel(10, workerEnum = FACTORY),
        attributeUpgrade = AttributeEnum.COLLECT
    ),
    CASTLE(
        order = 5,
        iconResourceId = R.drawable.ic_baseline_castle_24,
        WorkerCostModel(10, workerEnum = OFFICE),
        WorkerCostModel(10, workerEnum = OFFICE),
        attributeUpgrade = AttributeEnum.RATIO
    ),
    FINANCES(
        order = 6,
        iconResourceId = R.drawable.ic_baseline_account_balance_24,
        WorkerCostModel(10, workerEnum = CASTLE),
        WorkerCostModel(10, workerEnum = CASTLE),
        attributeUpgrade = AttributeEnum.INTERMEDIATE_STORAGE
    ),
    FIELD(
        order = 7,
        iconResourceId = R.drawable.ic_baseline_agriculture_24,
        WorkerCostModel(10, workerEnum = FINANCES),
        WorkerCostModel(10, workerEnum = FINANCES),
        attributeUpgrade = AttributeEnum.COLLECT
    ),
    RESTAURANT(
        order = 10,
        iconResourceId = R.drawable.ic_baseline_restaurant_24,
        WorkerCostModel(10, workerEnum = FIELD),
        WorkerCostModel(10, workerEnum = FIELD),
        attributeUpgrade = AttributeEnum.STORAGE
    ),
    GROCERYSTORE(
        order = 11,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, workerEnum = RESTAURANT),
        WorkerCostModel(10, workerEnum = RESTAURANT),
        attributeUpgrade = AttributeEnum.STORAGE
    ),
    GYM(
        order = 9,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, workerEnum = GROCERYSTORE),
        WorkerCostModel(10, workerEnum = GROCERYSTORE),
        attributeUpgrade = AttributeEnum.NEEDED_TICKS
    ),
    DISCO(
        order = 8,
        R.drawable.ic_baseline_directions_run_24,
        WorkerCostModel(10, workerEnum = GYM),
        WorkerCostModel(10, workerEnum = GYM),
        attributeUpgrade = AttributeEnum.NEEDED_TICKS
    ),
}