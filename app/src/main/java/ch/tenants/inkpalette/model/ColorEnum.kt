package ch.tenants.inkpalette.model

import CostModel
import android.graphics.Color
import ch.tenants.inkpalette.R

enum class ColorEnum(
    val order: Int,
    val color: Int,
    val iconResourceId: Int,
    val upgradeCost: CostModel,
    var buyCost: CostModel? = null
) {
    YELLOW(
        1,
        Color.YELLOW,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = YELLOW)
    ),
    RED(
        2,
        Color.RED,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(1, colorEnum = YELLOW),
        CostModel(1, colorEnum = YELLOW)
    ),
    GREEN(
        3,
        Color.GREEN,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = RED),
        CostModel(10, colorEnum = RED)
    ),
    MAGENTA(
        4,
        Color.MAGENTA,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = GREEN),
        CostModel(10, colorEnum = GREEN)
    ),
    GRAY(
        5,
        Color.GRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = MAGENTA),
        CostModel(10, colorEnum = MAGENTA)
    ),
    DKGRAY(
        6,
        Color.DKGRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = GRAY),
        CostModel(10, colorEnum = GRAY)
    ),
    CYAN(
        7,
        Color.CYAN,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = DKGRAY),
        CostModel(10, colorEnum = DKGRAY)
    ),
    LTGRAY(
        8,
        Color.LTGRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = CYAN),
        CostModel(10, colorEnum = CYAN)
    ),
    BLUE(
        9,
        Color.BLUE,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colorEnum = LTGRAY),
        CostModel(10, colorEnum = LTGRAY)
    )
}

