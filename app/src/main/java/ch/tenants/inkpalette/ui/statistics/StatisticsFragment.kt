package ch.tenants.inkpalette.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ch.tenants.inkpalette.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val data = mutableListOf(
            Statistic(name = "Collected Diamonds", value = 1),
            Statistic(name = "Collected Coins", value = 2),
            Statistic(name = "Collected Colors", value = 3),
            Statistic(name = "Hours Played", value = 4),
            Statistic(name = "Collected by Hand", value = 5),
            Statistic(name = "Upgraded Items", value = 6)
        )

        val adapter = StatisticsAdapter(data, this.requireContext())

        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.statsList.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}