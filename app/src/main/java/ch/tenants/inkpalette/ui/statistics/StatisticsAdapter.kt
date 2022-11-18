package ch.tenants.inkpalette.ui.statistics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ch.tenants.inkpalette.databinding.StatisticCellBinding

class StatisticsAdapter(
    private var statistics: MutableList<Statistic>,
    val context: Context
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var _binding: StatisticCellBinding? = null
    private val binding get() = _binding!!
    private var bindings = mutableMapOf<View, StatisticCellBinding>()

    override fun getCount(): Int { //number of elements to display
        return statistics.size
    }

    override fun getItem(index: Int): Statistic { //item at index
        return statistics[index]
    }

    override fun getItemId(index: Int): Long { //itemId for index
        return index.toLong()
    }

    override fun getView(
        index: Int, oldView: View?,
        viewGroup: ViewGroup?
    ): View {
        val view: View
        if (oldView == null) { //check if we get a view to recycle
            _binding = StatisticCellBinding.inflate(
                layoutInflater,
                viewGroup, false
            )
            view = binding.root;bindings[binding.root] = binding
        } else { //if yes, use the oldview
            view = oldView
            _binding = bindings[view]
        }
        val statistic = getItem(index) //get the data for this index
        binding.name.text = statistic.name
        binding.value.text = statistic.value.toString()
        return view
    }
}