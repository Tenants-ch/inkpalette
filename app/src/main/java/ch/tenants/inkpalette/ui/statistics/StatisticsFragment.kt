package ch.tenants.inkpalette.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.StatisticRepository
import ch.tenants.inkpalette.databinding.FragmentStatisticsBinding
import ch.tenants.inkpalette.model.Statistic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private var statisticRepository: StatisticRepository? = null
    private var data: List<Statistic>? = null

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

        val context = this.requireContext()
        lifecycleScope.launch(Dispatchers.IO) {
            data = statisticRepository!!.getAll()
        }
        val adapter = data?.let { StatisticsAdapter(it, context) }
        binding.statsList.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}