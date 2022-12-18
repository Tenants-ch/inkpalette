package ch.tenants.inkpalette.ui.ink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.model.Action
import ch.tenants.inkpalette.model.collectable.Collectable


class GridRecyclerViewAdapter(private val updateCollectable: (Collectable, Action) -> Unit, private val confirmAction: (Collectable, Action) -> Unit) :
    RecyclerView.Adapter<GridViewHolder>() {

    var collectables: List<Collectable> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : GridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GridViewHolder(inflater, parent, updateCollectable, confirmAction)
    }

    override fun onBindViewHolder(
        holder: GridViewHolder,
        position: Int
    ) {
        val collectable: Collectable = collectables[position]
        holder.bind(collectable)
    }

    override fun getItemCount(): Int = collectables.size


}
