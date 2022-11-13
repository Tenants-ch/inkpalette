package ch.tenants.inkpalette.ui.settings

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.databinding.FragmentSettingsBinding
import ch.tenants.inkpalette.ui.grid.Collectable
import ch.tenants.inkpalette.ui.grid.GridReviewAdapter

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val data = mutableListOf(
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.BLUE,1, info = "Upgrade this will Double to production of your Blue color"),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.YELLOW,1, info = "Upgrade this will Double to production of your Blue color"),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.GREEN,1, info = "Upgrade this will Double to production of your Blue color"),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.RED,1, info = "Upgrade this will Double to production of your Blue color"),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.CYAN,1, info = "Upgrade this will Double to production of your Blue color"),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.MAGENTA,1, info = "Upgrade this will Double to production of your Blue color"),
            Collectable(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color =  Color.GRAY,1, info = "Upgrade this will Double to production of your Blue color")
        )
        val recyclerView: RecyclerView = binding.recyclerGrid
        recyclerView.adapter = GridReviewAdapter(data)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            recyclerView.hasPendingAdapterUpdates()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}