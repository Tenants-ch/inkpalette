package ch.tenants.inkpalette.model.enums

import CostModel
import android.graphics.Color
import ch.tenants.inkpalette.R

enum class ColorEnum(
    val order: Int,
    val color: Int,
    val iconResourceId: Int,
    val upgradeCost: CostModel,
    var buyCost: CostModel? = null,
    var attributeUpgrade: AttributeEnum,
) {
    YELLOW(
        1,
        Color.YELLOW,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = YELLOW),
        attributeUpgrade = AttributeEnum.STORAGE
    ),
    RED(
        2,
        Color.RED,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(1, colorEnum = YELLOW),
        CostModel(1, colorEnum = YELLOW),
        attributeUpgrade = AttributeEnum.INTERMEDIATE_STORAGE
    ),
    GREEN(
        3,
        Color.GREEN,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = RED),
        CostModel(10, colorEnum = RED),
        attributeUpgrade = AttributeEnum.COLLECT
    ),
    MAGENTA(
        4,
        Color.MAGENTA,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = GREEN),
        CostModel(10, colorEnum = GREEN),
        attributeUpgrade = AttributeEnum.RATIO
    ),
    GRAY(
        5,
        Color.GRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = MAGENTA),
        CostModel(10, colorEnum = MAGENTA),
        attributeUpgrade = AttributeEnum.UPGRADE_COST
    ),
    DKGRAY(
        6,
        Color.DKGRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = GRAY),
        CostModel(10, colorEnum = GRAY),
        attributeUpgrade = AttributeEnum.NEEDED_TICKS
    ),
    CYAN(
        7,
        Color.CYAN,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = DKGRAY),
        CostModel(10, colorEnum = DKGRAY),
        attributeUpgrade = AttributeEnum.STORAGE
    ),
    LTGRAY(
        8,
        Color.LTGRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = CYAN),
        CostModel(10, colorEnum = CYAN),
        attributeUpgrade = AttributeEnum.STORAGE
    ),
    BLUE(
        9,
        Color.BLUE,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = LTGRAY),
        CostModel(10, colorEnum = LTGRAY),
        attributeUpgrade = AttributeEnum.STORAGE
    )
}

