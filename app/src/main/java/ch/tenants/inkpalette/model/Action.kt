package ch.tenants.inkpalette.model

import ch.tenants.inkpalette.R


enum class Action(val icon: Int, val title: Int, val buttonText: Int) {
    UNLOCK(
        R.drawable.ic_baseline_lock_open_24,
        R.string.unlock, R.string.unlock_ink
    ),
    UPGRADE(
        R.drawable.ic_baseline_keyboard_double_arrow_up_24,
        R.string.upgrade,
        R.string.upgrade_ink
    ),
    CONVERT(
        R.drawable.ic_baseline_autorenew_24, R.string.convert, R.string.convert_ink
    ),
    COLLECT(
        R.drawable.ic_baseline_autorenew_24, R.string.convert, R.string.convert_ink
    )
}