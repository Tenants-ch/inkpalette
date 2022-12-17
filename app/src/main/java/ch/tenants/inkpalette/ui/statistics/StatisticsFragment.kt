package ch.tenants.inkpalette.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.StatisticRepository
import ch.tenants.inkpalette.databinding.FragmentStatisticsBinding
import ch.tenants.inkpalette.model.Statistic
import ch.tenants.inkpalette.model.collectable.Collectable
import ch.tenants.inkpalette.model.enums.StatisticEnum
import ch.tenants.inkpalette.ui.ink.GridRecyclerViewAdapter
import ch.tenants.inkpalette.ui.ink.GridViewModel
import ch.tenants.inkpalette.ui.ink.GridViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private var statisticRepository: StatisticRepository? = null
    private lateinit var adapter: StatisticsRecyclerViewAdapter
    private val viewModel: StatisticViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        statisticRepository =
            StatisticRepository(AppDatabase.getDatabase(requireContext().applicationContext))


        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.statsList
        adapter = StatisticsRecyclerViewAdapter(::addStatistics)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.statisticsLiveData.observe(viewLifecycleOwner) {
            adapter.statistics = it
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun addStatistics(stats: List<StatisticEnum>) {
        lifecycleScope.launch(Dispatchers.IO) {
            statisticRepository?.addStats(stats)
        }
    }
}