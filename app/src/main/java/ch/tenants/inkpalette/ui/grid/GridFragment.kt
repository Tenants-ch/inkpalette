package ch.tenants.inkpalette.ui.grid

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.databinding.FragmentGridBinding

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

        val data = mutableListOf(
            CollectableOldModel(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.BLUE,1, info = "Upgrade this will Double to production of your Blue color"),
            CollectableOldModel(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.YELLOW,1, info = "Upgrade this will Double to production of your Blue color"),
            CollectableOldModel(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.GREEN,1, info = "Upgrade this will Double to production of your Blue color"),
            CollectableOldModel(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.RED,1, info = "Upgrade this will Double to production of your Blue color"),
            CollectableOldModel(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.CYAN,1, info = "Upgrade this will Double to production of your Blue color"),
            CollectableOldModel(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color = Color.MAGENTA,1, info = "Upgrade this will Double to production of your Blue color"),
            CollectableOldModel(name = "Collected Diamonds", icon = resources.getDrawable(R.drawable.ic_baseline_keyboard_double_arrow_up_24, null), color =  Color.GRAY,1, info = "Upgrade this will Double to production of your Blue color")
        )

        val adapter = GridAdapter(data, this.requireContext())

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