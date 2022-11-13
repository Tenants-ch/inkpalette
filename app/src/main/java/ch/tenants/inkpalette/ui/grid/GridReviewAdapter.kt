package ch.tenants.inkpalette.ui.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class GridReviewAdapter(private val collectables: List<Collectable>) :
    RecyclerView.Adapter<GridViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : GridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GridViewHolder(inflater, parent)
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
