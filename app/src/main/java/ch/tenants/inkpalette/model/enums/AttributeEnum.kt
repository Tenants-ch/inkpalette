package ch.tenants.inkpalette.model.enums

enum class AttributeEnum(
    val order: Int,
) {
    STORAGE(
        1
    ),
    INTERMEDIATE_STORAGE(
        2,
    ),
    NEEDED_TICKS(
        3,
    ),
    COLLECT(
        4,
    ),
    RATIO(
        5,
    ),
    UPGRADE_COST(
        6,
    )
}

