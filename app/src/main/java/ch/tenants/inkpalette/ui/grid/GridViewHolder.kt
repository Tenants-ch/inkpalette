package ch.tenants.inkpalette.ui.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R

class GridViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(
        R.layout.button_group,
        parent, false)) {
    private var nameView: TextView? = null
    private var streetView: TextView? = null
    init {
        nameView = itemView.findViewById(R.id.name)
        streetView = itemView.findViewById(R.id.street)
    }
    fun bind(person: Person) {
        nameView?.text = person.name
        streetView?.text = person.street
    }
}
