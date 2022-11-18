package ch.tenants.inkpalette.ui.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.ui.model.Collectable
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
        mainButton?.setIconResource(collectable.iconResourceId)
        mainButton?.setBackgroundColor(collectable.color)
        infoButton?.setOnClickListener { view ->
            collectable.info.let {
                Snackbar.make(view, it, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
        mainButton?.setOnClickListener { view ->
            val bundle = bundleOf("section" to collectable.section+1)
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.navigation_section, bundle)
        }
        number?.text = collectable.count.toString()
    }
}
