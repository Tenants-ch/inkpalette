package ch.tenants.inkpalette.ui.statistics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.model.Statistic
import ch.tenants.inkpalette.model.enums.StatisticEnum


class StatisticViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    val addStatistics: (List<StatisticEnum>) -> Unit,
) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.statistic_cell, parent, false
    )
) {
    private var name: TextView? = null
    private var value: TextView? = null

    init {
        name = itemView.findViewById(R.id.name)
        value = itemView.findViewById(R.id.value)
    }

    fun bind(statistic: Statistic) {
        name?.text = itemView.context.getString(statistic.statisticEnum.stringResourceId)
        value?.text = statistic.quantity.toString()
        itemView.setOnClickListener {
            val statisticEnums = mutableListOf(StatisticEnum.STATS_CLICKED)
            if (statistic.statisticEnum == StatisticEnum.SETTINGS_TOUCH_CLICKED) {
                statisticEnums.add(StatisticEnum.STATS_TOUCH_CLICKED)
            }
            addStatistics(statisticEnums as List<StatisticEnum>)
        }
        if (statistic.getVisibility()) {
            itemView.visibility = View.VISIBLE
            itemView.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        } else {
            itemView.visibility = View.GONE
            itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
    }


}
