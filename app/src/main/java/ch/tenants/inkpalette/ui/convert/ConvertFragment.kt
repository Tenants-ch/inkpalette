package ch.tenants.inkpalette.ui.convert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.databinding.FragmentSettingsBinding
import ch.tenants.inkpalette.ui.section.grid.GridRecyclerViewAdapter
import ch.tenants.inkpalette.ui.section.grid.GridViewModel
import ch.tenants.inkpalette.ui.section.grid.GridViewModelFactory

class ConvertFragment : Fragment() {

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
        ViewModelProvider(
            this,
            GridViewModelFactory(activity.application, section,null, null)
        )[GridViewModel::class.java]
    }
    private lateinit var adapter: GridRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        collectableRepository =
            CollectableRepository(AppDatabase.getDatabase(requireContext().applicationContext))

        val recyclerView: RecyclerView = binding.recyclerGrid
        adapter = GridRecyclerViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.collectableLiveData.observe(viewLifecycleOwner) {
            adapter.collectables = it
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}