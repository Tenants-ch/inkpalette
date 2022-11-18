package ch.tenants.inkpalette.ui.grid

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.ui.model.Collectable


class GridReviewAdapter :
    RecyclerView.Adapter<GridViewHolder>() {

    var collectables: List<Collectable> = emptyList()
        set(value) {
            field = value
            Log.i("GridReviewAdapter", "Set Collectables")
            notifyDataSetChanged()
        }



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
