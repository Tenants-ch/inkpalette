package ch.tenants.inkpalette.model

import ch.tenants.inkpalette.model.enums.StatisticEnum

class Statistic(val statisticEnum: StatisticEnum, val quantity: Int) {
    fun getVisibility(): Boolean {
        return !statisticEnum.hidden || quantity > statisticEnum.threshold
    }
}