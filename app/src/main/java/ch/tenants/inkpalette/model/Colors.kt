package ch.tenants.inkpalette.model

import android.graphics.Color
import ch.tenants.inkpalette.R

enum class Colors(val order: Int, val color: Int, val iconResourceId: Int) {
    YELLOW(1, Color.YELLOW, R.drawable.ic_baseline_account_balance_24),
    RED(2, Color.RED, R.drawable.ic_baseline_account_balance_24),
    GREEN(3, Color.GREEN, R.drawable.ic_baseline_account_balance_24),
    MAGENTA(4, Color.MAGENTA, R.drawable.ic_baseline_account_balance_24),
    GRAY(5, Color.GRAY, R.drawable.ic_baseline_account_balance_24),
    DKGRAY(6, Color.DKGRAY, R.drawable.ic_baseline_account_balance_24),
    CYAN(7, Color.CYAN, R.drawable.ic_baseline_account_balance_24),
    LTGRAY(8, Color.LTGRAY, R.drawable.ic_baseline_account_balance_24),
    BLUE(9, Color.BLUE, R.drawable.ic_baseline_account_balance_24)
}