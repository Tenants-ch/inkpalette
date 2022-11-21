package ch.tenants.inkpalette.model

import ch.tenants.inkpalette.R


enum class Upgrade(val worker: Worker, val order: Int, val iconResourceId: Int) {
    HAMMER(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_hardware_24,
    ),
    WRENCH(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_build_24
    ),
    PLUMING(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_plumbing_24
    ),
    BRUSH(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_brush_24
    ),
    SCISSORS(
        worker = Worker.PERSON,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_content_cut_24
    ),
    BURGER(
        worker = Worker.RESTAURANT,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_lunch_dining_24
    ),
    WINE(
        worker = Worker.RESTAURANT,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_wine_bar_24
    ),
    BIOTECH(
        worker = Worker.FACTORY,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_biotech_24
    ),
    MANUFACTURING(
        worker = Worker.FACTORY,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_precision_manufacturing_24
    ),
    BOOK(
        worker = Worker.CHURCH,
        order = 1,
        iconResourceId = R.drawable.ic_baseline_menu_book_24
    )
}