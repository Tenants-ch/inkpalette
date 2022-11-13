package ch.tenants.inkpalette.ui.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class GridViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.button_group,
            parent, false
        )
    ) {
    private var mainButton: MaterialButton? = null
    private var infoButton: MaterialButton? = null
    private var upgradeButton: MaterialButton? = null
    private var number: TextView? = null

    init {
        mainButton = itemView.findViewById(R.id.button_main);
        infoButton = itemView.findViewById(R.id.button_info)
        upgradeButton = itemView.findViewById(R.id.button_upgrade)
        number = itemView.findViewById(R.id.number)
    }

    fun bind(collectable: Collectable) {
        mainButton?.icon = collectable.icon
        mainButton?.setBackgroundColor(collectable.color)
        infoButton?.setOnClickListener { view ->
            Snackbar.make(view, collectable.info, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        number?.text = collectable.collected.toString()
    }
}
