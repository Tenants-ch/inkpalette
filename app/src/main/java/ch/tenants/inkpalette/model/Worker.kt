package ch.tenants.inkpalette.model

import ch.tenants.inkpalette.R


enum class Worker(val order: Int, val iconResourceId: Int) {
    PERSON(
        order = 1,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
    ),
    CHURCH(
        order = 2,
        iconResourceId = R.drawable.ic_baseline_church_24,
    ),
    FACTORY(
        order = 3,
        iconResourceId = R.drawable.ic_baseline_factory_24,
    ),
    OFFICE(
        order = 4,
        iconResourceId = R.drawable.ic_baseline_business_24,
    ),
    CASTLE(
        order = 5,
        iconResourceId = R.drawable.ic_baseline_castle_24,
    ),
    FINANCES(
        order = 6,
        iconResourceId = R.drawable.ic_baseline_account_balance_24,
    ),
    FIELD(
        order = 7,
        iconResourceId = R.drawable.ic_baseline_agriculture_24,
    ),
    DISCO(
        order = 8,
        R.drawable.ic_baseline_directions_run_24,
    ),
    GYM(
        order = 9,
        iconResourceId = R.drawable.ic_baseline_directions_run_24,
    ),
    RESTAURANT(
        order = 10,
        iconResourceId = R.drawable.ic_baseline_restaurant_24
    ),
    GROCERYSTORE(
        order = 11,
        iconResourceId = R.drawable.ic_baseline_directions_run_24
    )
}