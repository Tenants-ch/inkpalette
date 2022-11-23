package ch.tenants.inkpalette.ui.section.grid

import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.model.Collectable
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator


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
    private var nonCollectedCount: TextView? = null
    private var progressIndicator: LinearProgressIndicator? = null

    init {
        mainButton = itemView.findViewById(R.id.button_main)
        infoButton = itemView.findViewById(R.id.button_info)
        upgradeButton = itemView.findViewById(R.id.button_upgrade)
        number = itemView.findViewById(R.id.countDisplay)
        nonCollectedCount = itemView.findViewById(R.id.nonCollectedCount)
        progressIndicator = itemView.findViewById(R.id.progress_linear)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun bind(collectable: Collectable) {


        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_pressed)
        )

        val colors = intArrayOf(
            collectable.color.color,
            collectable.color.color,
            collectable.color.color,
            collectable.color.color
        )

        val myList = ColorStateList(states, colors)
        mainButton?.setIconResource(collectable.getIconId())
        mainButton?.setBackgroundColor(collectable.color.color)
        mainButton?.isEnabled = collectable.unlocked
        infoButton?.isEnabled = collectable.unlocked
        infoButton?.backgroundTintList = myList
        upgradeButton?.isEnabled = collectable.unlocked
        nonCollectedCount?.text = collectable.getNotCollectCountDisplay()
        number?.text =
            if (collectable.unlocked) collectable.getCountDisplay() else itemView.context.getString(
                R.string.locked
            )

        /*
        upgradeButton?.setBackgroundDrawable(
            ButtonBackgroundDrawable(
                collectable.color.color,
                Color.BLACK,
                1,
                1
            )
        )
        infoButton?.setOnClickListener { view ->
            collectable.attribute.info.let {
                Snackbar.make(view, it, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }*/
        upgradeButton?.setOnClickListener {
            collectable.collect()
        }
        mainButton?.setOnClickListener { view ->
            val bundle = bundleOf(
                "section" to collectable.section + 1,
                "color" to collectable.color,
                "worker" to collectable.worker
            )
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.navigation_section, bundle)
        }
        progressIndicator?.setProgress(collectable.getProgress(), false)
    }
}
