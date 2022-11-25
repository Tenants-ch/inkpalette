package ch.tenants.inkpalette.model

import CostModel
import WorkerCostModel
import ch.tenants.inkpalette.R


enum class Upgrade(
    val worker: Worker,
    val order: Int,
    val iconResourceId: Int,
    val upgradeCost: CostModel,
    var buyCost: CostModel? = null
) {
    HAMMER(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_hardware_24,
        WorkerCostModel(10, worker = Worker.PERSON),
        WorkerCostModel(10, worker = Worker.PERSON),
    ),
    WRENCH(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_build_24,
        WorkerCostModel(10, worker = Worker.PERSON),
        WorkerCostModel(10, worker = Worker.PERSON)
    ),
    PLUMING(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_plumbing_24,
        WorkerCostModel(10, worker = Worker.PERSON),
        WorkerCostModel(10, worker = Worker.PERSON)
    ),
    BRUSH(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_brush_24,
        WorkerCostModel(10, worker = Worker.PERSON),
        WorkerCostModel(10, worker = Worker.PERSON)
    ),
    SCISSORS(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_content_cut_24,
        WorkerCostModel(10, worker = Worker.PERSON),
        WorkerCostModel(10, worker = Worker.PERSON)
    ),
    BURGER(
        worker = Worker.RESTAURANT,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_lunch_dining_24,
        WorkerCostModel(10, worker = Worker.RESTAURANT),
        WorkerCostModel(10, worker = Worker.RESTAURANT)
    ),
    WINE(
        worker = Worker.RESTAURANT,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_wine_bar_24,
        WorkerCostModel(10, worker = Worker.RESTAURANT),
        WorkerCostModel(10, worker = Worker.RESTAURANT)
    ),
    BIOTECH(
        worker = Worker.FACTORY,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_biotech_24,
        WorkerCostModel(10, worker = Worker.FACTORY),
        WorkerCostModel(10, worker = Worker.FACTORY)
    ),
    MANUFACTURING(
        worker = Worker.FACTORY,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_precision_manufacturing_24,
        WorkerCostModel(10, worker = Worker.FACTORY),
        WorkerCostModel(10, worker = Worker.FACTORY)
    ),
    BOOK(
        worker = Worker.CHURCH,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_menu_book_24,
        WorkerCostModel(10, worker = Worker.CHURCH),
        WorkerCostModel(10, worker = Worker.CHURCH)
    )
}