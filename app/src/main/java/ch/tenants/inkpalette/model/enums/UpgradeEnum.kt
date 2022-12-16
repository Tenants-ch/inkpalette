package ch.tenants.inkpalette.model.enums

import CostModel
import WorkerCostModel
import ch.tenants.inkpalette.R


enum class UpgradeEnum(
    val workerEnum: WorkerEnum,
    val order: Int,
    val iconResourceId: Int,
    val upgradeCost: CostModel,
    var buyCost: CostModel? = null,
    var navigation: Int = R.id.navigation_not_yet
) {
    HAMMER(
        workerEnum = WorkerEnum.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_hardware_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON),
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON),
    ),
    WRENCH(
        workerEnum = WorkerEnum.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_build_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON),
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON)
    ),
    PLUMING(
        workerEnum = WorkerEnum.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_plumbing_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON),
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON)
    ),
    BRUSH(
        workerEnum = WorkerEnum.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_brush_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON),
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON)
    ),
    SCISSORS(
        workerEnum = WorkerEnum.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_content_cut_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON),
        WorkerCostModel(10, workerEnum = WorkerEnum.PERSON)
    ),
    BURGER(
        workerEnum = WorkerEnum.RESTAURANT,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_lunch_dining_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.RESTAURANT),
        WorkerCostModel(10, workerEnum = WorkerEnum.RESTAURANT)
    ),
    WINE(
        workerEnum = WorkerEnum.RESTAURANT,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_wine_bar_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.RESTAURANT),
        WorkerCostModel(10, workerEnum = WorkerEnum.RESTAURANT)
    ),
    BIOTECH(
        workerEnum = WorkerEnum.FACTORY,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_biotech_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.FACTORY),
        WorkerCostModel(10, workerEnum = WorkerEnum.FACTORY)
    ),
    MANUFACTURING(
        workerEnum = WorkerEnum.FACTORY,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_precision_manufacturing_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.FACTORY),
        WorkerCostModel(10, workerEnum = WorkerEnum.FACTORY)
    ),
    BOOK(
        workerEnum = WorkerEnum.CHURCH,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_menu_book_24,
        WorkerCostModel(10, workerEnum = WorkerEnum.CHURCH),
        WorkerCostModel(10, workerEnum = WorkerEnum.CHURCH)
    )
}