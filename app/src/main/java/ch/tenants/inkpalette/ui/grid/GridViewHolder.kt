package ch.tenants.inkpalette.ui.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R
import com.google.android.material.button.MaterialButton

class GridViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.button_group,
            parent, false
        )
    ) {
    private var mainButton: MaterialButton? = null
    private var infoButton: Button? = null
    private var upgradeButton: Button? = null

    init {
        mainButton = itemView.findViewById(R.id.button_main);
        infoButton = itemView.findViewById(R.id.button_info)
        upgradeButton = itemView.findViewById(R.id.button_upgrade)
    }

    fun bind(collectable: Collectable) {
        mainButton?.icon = collectable.icon
    }
}
