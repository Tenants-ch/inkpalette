package ch.tenants.inkpalette.ui.grid

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.databinding.FragmentGridBinding
import ch.tenants.inkpalette.databinding.FragmentStatisticsBinding
import ch.tenants.inkpalette.ui.statistics.Statistic
import ch.tenants.inkpalette.ui.statistics.StatisticsAdapter

class GridFragment : Fragment() {

    private var _binding: FragmentGridBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(GridViewModel::class.java)

        val data = mutableListOf(
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.BLUE),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.YELLOW),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.GREEN),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.RED),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.CYAN),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.MAGENTA),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color =  Color.GRAY)
        )

        val adapter = GridAdapter(data, this.requireContext());

        _binding = FragmentGridBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.gridView.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}