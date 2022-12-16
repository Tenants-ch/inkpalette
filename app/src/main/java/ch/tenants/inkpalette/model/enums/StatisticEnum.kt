package ch.tenants.inkpalette.model.enums

import ch.tenants.inkpalette.R


enum class StatisticEnum(
    val order: Int,
    val stringResourceId: Int,
    val hidden: Boolean = false,
    val threshold: Int = 0
) {
    BUTTONS(
        order = 1,
        stringResourceId = R.string.stats_button,
    ),
    BUTTON_COLLECT(
        order = 2,
        stringResourceId = R.string.stats_button_collect,
    ),
    BUTTON_UPGRADE(
        order = 3,
        stringResourceId = R.string.stats_button_upgrade,
    ),
    BUTTON_CONFIRM(
        order = 4,
        stringResourceId = R.string.stats_button_confirm,
    ),
    BUTTON_CANCEL(
        order = 5,
        stringResourceId = R.string.stats_button_cancel,
    ),
    BUTTON_CONVERT(
        order = 5,
        stringResourceId = R.string.stats_button_convert,
    ),
    BUY_WITH_INK(
        order = 100,
        stringResourceId = R.string.stats_buy_with_ink,
    ),
    BUY_WITH_COINS(
        order = 101,
        stringResourceId = R.string.stats_buy_with_coins,
    ),
    STATS_CLICKED(
        order = 201,
        stringResourceId = R.string.stats_clicked,
        hidden = true,
        threshold = 1
    ),
    STATS_TOUCH_CLICKED(
        order = 202,
        stringResourceId = R.string.stats_touch_clicked,
        hidden = true,
        threshold = 5
    ),
    SETTINGS_CLICKED(
        order = 301,
        stringResourceId = R.string.settings_clicked,
        hidden = true,
        threshold = 1
    ),
    SETTINGS_TOUCH_CLICKED(
        order = 302,
        stringResourceId = R.string.settings_touch_clicked,
        hidden = true,
        threshold = 5
    ),
}