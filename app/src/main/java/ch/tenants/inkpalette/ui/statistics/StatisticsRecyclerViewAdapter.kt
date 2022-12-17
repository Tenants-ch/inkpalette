package ch.tenants.inkpalette.ui.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.model.Statistic
import ch.tenants.inkpalette.model.enums.StatisticEnum

class StatisticsRecyclerViewAdapter(private val addStatistics: (List<StatisticEnum>) -> Unit) :
    RecyclerView.Adapter<StatisticViewHolder>() {

    var statistics: List<Statistic> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : StatisticViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StatisticViewHolder(inflater, parent, addStatistics)
    }

    override fun onBindViewHolder(
        holder: StatisticViewHolder,
        position: Int
    ) {
        holder.bind(statistics[position])
    }

    override fun getItemCount(): Int = statistics.size


}