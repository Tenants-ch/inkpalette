package ch.tenants.inkpalette.ui.settings

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.data.CollectableEntity
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.data.getDatabase
import ch.tenants.inkpalette.databinding.FragmentSettingsBinding
import ch.tenants.inkpalette.ui.grid.GridReviewAdapter
import ch.tenants.inkpalette.ui.grid.GridViewModel
import ch.tenants.inkpalette.ui.grid.GridViewModelFactory

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var collectableRepository: CollectableRepository? = null
    private val viewModel: GridViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val section = arguments?.getInt("section") ?: 1
        ViewModelProvider(this, GridViewModelFactory(activity.application, section))[GridViewModel::class.java]
    }
    private lateinit var adapter: GridReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        collectableRepository =
            CollectableRepository(getDatabase(requireContext().applicationContext))

        val recyclerView: RecyclerView = binding.recyclerGrid
        adapter = GridReviewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.collectableLiveData.observe(viewLifecycleOwner) {
            adapter.collectables = it
        }


/*
        lifecycleScope.launch(Dispatchers.IO) {
            collectableRepository!!.deleteAll();
            insertUsers()
        }
*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun insertUsers() {
        collectableRepository?.insertAll(
            CollectableEntity(
                name = "Collected BLUE",
                iconResourceId = R.drawable.ic_baseline_keyboard_double_arrow_up_24,
                color = Color.BLUE,
                count = 1,
                info = "Upgrade this will Double to production of your Blue color",
                order = 1, totalCollected = 0, unlocked = true, section = 1
            ),
            CollectableEntity(
                name = "Collected YELLOW",
                iconResourceId = R.drawable.ic_baseline_keyboard_double_arrow_up_24,
                color = Color.YELLOW,
                count = 1,
                info = "Upgrade this will Double to production of your Blue color",
                order = 1, totalCollected = 0, unlocked = true, section = 1
            ),
            CollectableEntity(
                name = "Collected GREEN",
                iconResourceId = R.drawable.ic_baseline_keyboard_double_arrow_up_24,
                color = Color.GREEN,
                count = 1,
                info = "Upgrade this will Double to production of your Blue color",
                order = 1, totalCollected = 0, unlocked = true, section = 1
            ),
            CollectableEntity(
                name = "Collected RED",
                iconResourceId = R.drawable.ic_baseline_keyboard_double_arrow_up_24,
                color = Color.RED,
                count = 1,
                info = "Upgrade this will Double to production of your Blue color",
                order = 1, totalCollected = 0, unlocked = true, section = 1
            ),
            CollectableEntity(
                name = "Collected CYAN",
                iconResourceId = R.drawable.ic_baseline_keyboard_double_arrow_up_24,
                color = Color.CYAN,
                count = 1,
                info = "Upgrade this will Double to production of your Blue color",
                order = 1, totalCollected = 0, unlocked = true, section = 2
            ),
            CollectableEntity(
                name = "Collected MAGENTA",
                iconResourceId = R.drawable.ic_baseline_keyboard_double_arrow_up_24,
                color = Color.MAGENTA,
                count = 1,
                info = "Upgrade this will Double to production of your Blue color",
                order = 1, totalCollected = 0, unlocked = true, section = 2
            ),
            CollectableEntity(
                name = "Collected GRAY",
                iconResourceId = R.drawable.ic_baseline_keyboard_double_arrow_up_24,
                color = Color.GRAY,
                count = 1,
                info = "Upgrade this will Double to production of your Blue color",
                order = 1, totalCollected = 0, unlocked = true, section = 2
            )
        )
    }

}