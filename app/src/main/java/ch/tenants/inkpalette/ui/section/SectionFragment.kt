package ch.tenants.inkpalette.ui.section

import android.os.Bundle
import android.util.Log
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
import ch.tenants.inkpalette.model.Colors
import ch.tenants.inkpalette.model.Worker
import ch.tenants.inkpalette.ui.section.grid.GridRecyclerViewAdapter
import ch.tenants.inkpalette.ui.section.grid.GridViewModel
import ch.tenants.inkpalette.ui.section.grid.GridViewModelFactory

class SectionFragment : Fragment() {

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
        val color = arguments?.getInt("color")
        val worker = arguments?.getInt("worker")
        ViewModelProvider(
            this,
            GridViewModelFactory(activity.application, section = section, color = color?.let {
                enumValues<Colors>()[it]
            }, worker = worker?.let {
                enumValues<Worker>()[it]
            })
        )[GridViewModel::class.java]
    }
    private lateinit var adapter: GridRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.i("SectionFragement", "entered the Section Fragement")
        val section = arguments?.getInt("section")
        val color = arguments?.getString("color")
        val worker = arguments?.getString("worker")
        Log.i("SectionFragement", section.toString())
        Log.i("SectionFragement", worker.toString())
        Log.i("SectionFragement", color.toString())


        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        collectableRepository =
            CollectableRepository(AppDatabase.getDatabase(requireContext().applicationContext))

        val recyclerView: RecyclerView = binding.recyclerGrid
        adapter = GridRecyclerViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.collectableLiveData.observe(viewLifecycleOwner) {
            adapter.collectables = it
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}