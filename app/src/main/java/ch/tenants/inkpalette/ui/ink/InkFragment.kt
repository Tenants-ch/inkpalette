package ch.tenants.inkpalette.ui.ink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.databinding.FragmentInkBinding
import ch.tenants.inkpalette.model.Action
import ch.tenants.inkpalette.model.collectable.Collectable
import ch.tenants.inkpalette.ui.dialogs.BuyOrUpgradeDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InkFragment : Fragment() {

    private var _binding: FragmentInkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var startup = true
    private var collectableRepository: CollectableRepository? = null
    private val viewModel: GridViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val section = arguments?.getInt("section") ?: 1
        ViewModelProvider(
            this,
            GridViewModelFactory(activity.application, section, null, null)
        )[GridViewModel::class.java]
    }
    private lateinit var adapter: GridRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        collectableRepository =
            CollectableRepository(AppDatabase.getDatabase(requireContext().applicationContext))

        val recyclerView: RecyclerView = binding.recyclerGrid
        adapter = GridRecyclerViewAdapter(::updateCollectable, ::showBuyOrUpgradeDialog)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.collectableLiveData.observe(viewLifecycleOwner) {
            adapter.collectables = it
        }

        return root
    }

    private fun updateCollectable(collectable: Collectable) {
        lifecycleScope.launch(Dispatchers.IO) {
            collectableRepository?.updateCollectable(collectable)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showBuyOrUpgradeDialog(collectable: Collectable, action: Action) {

        lifecycleScope.launch(Dispatchers.IO) {
            val dialog = BuyOrUpgradeDialog(collectable, action)
            activity?.let { dialog.show(it.supportFragmentManager, "NoticeDialogFragment") }
        }

    }

}