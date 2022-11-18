package ch.tenants.inkpalette.ui.grid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ch.tenants.inkpalette.databinding.ButtonGroupBinding
import com.google.android.material.snackbar.Snackbar

class GridAdapter(
    var collectableOldModels: MutableList<CollectableOldModel>,
    val context: Context
) : BaseAdapter() {
    var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var _binding: ButtonGroupBinding? = null
    private val binding get() = _binding!!
    private var bindings = mutableMapOf<View, ButtonGroupBinding>()

    override fun getCount(): Int { //number of elements to display
        return collectableOldModels.size
    }

    override fun getItem(index: Int): CollectableOldModel { //item at index
        return collectableOldModels.get(index)
    }

    override fun getItemId(index: Int): Long { //itemId for index
        return index.toLong()
    }

    override fun getView(
        index: Int, oldView: View?,
        viewGroup: ViewGroup?
    ): View {
        var view: View
        if (oldView == null) { //check if we get a view to recycle
            _binding = ButtonGroupBinding.inflate(
                layoutInflater,
                viewGroup, false
            )
            view = binding.root;bindings[binding.root] = binding
        } else { //if yes, use the oldview
            view = oldView
            _binding = bindings[view]
        }
        val collectable = getItem(index) //get the data for this index
        binding.buttonMain.text = collectable.name
        binding.buttonMain.icon = collectable.icon
        binding.buttonMain.setBackgroundColor(collectable.color)
        binding.buttonInfo.setOnClickListener {
            Snackbar.make(it, collectable.info, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        binding.number.text = collectable.collected.toString()
        return view
    }
}