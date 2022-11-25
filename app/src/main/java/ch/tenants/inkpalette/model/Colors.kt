package ch.tenants.inkpalette.model

import Cost
import CostModel
import android.graphics.Color
import ch.tenants.inkpalette.R

enum class Colors(
    val order: Int,
    val color: Int,
    val iconResourceId: Int,
    val upgradeCost: CostModel,
    var buyCost: CostModel? = null
) {
    YELLOW(
        1, Color.YELLOW, R.drawable.ic_baseline_account_balance_24, CostModel(10, colors = Colors.YELLOW)
    ),
    RED(
        2,
        Color.RED,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(1, colors = Colors.YELLOW),
        CostModel(1, colors = Colors.YELLOW)
    ),
    GREEN(
        3,
        Color.GREEN,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colors = Colors.RED),
        CostModel(10, colors = Colors.RED)
    ),
    MAGENTA(
        4,
        Color.MAGENTA,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colors = Colors.GREEN),
        CostModel(10, colors = Colors.GREEN)
    ),
    GRAY(
        5,
        Color.GRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colors = Colors.MAGENTA),
        CostModel(10, colors = Colors.MAGENTA)
    ),
    DKGRAY(
        6,
        Color.DKGRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colors = Colors.GRAY),
        CostModel(10, colors = Colors.GRAY)
    ),
    CYAN(
        7,
        Color.CYAN,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colors = Colors.DKGRAY),
        CostModel(10, colors = Colors.DKGRAY)
    ),
    LTGRAY(
        8,
        Color.LTGRAY,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colors = Colors.LTGRAY),
        CostModel(10, colors = Colors.LTGRAY)
    ),
    BLUE(
        9,
        Color.BLUE,
        R.drawable.ic_baseline_account_balance_24,
        CostModel(10, colors = Colors.LTGRAY),
        CostModel(10, colors = Colors.LTGRAY)
    )
}

