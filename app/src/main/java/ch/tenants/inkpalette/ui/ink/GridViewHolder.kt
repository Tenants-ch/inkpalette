package ch.tenants.inkpalette.ui.ink

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.model.Action
import ch.tenants.inkpalette.model.Collectable
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator


class GridViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    val updateCollectable: (Collectable) -> Unit,
    val confirmAction: (Collectable, Action) -> Unit
) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.button_group,
            parent, false
        )
    ) {
    private var mainButton: MaterialButton? = null
    private var collectButton: MaterialButton? = null
    private var upgradeButton: MaterialButton? = null
    private var number: TextView? = null
    private var level: TextView? = null
    private var nonCollectedCount: TextView? = null
    private var progressIndicator: LinearProgressIndicator? = null

    init {
        mainButton = itemView.findViewById(R.id.button_main)
        collectButton = itemView.findViewById(R.id.button_collect)
        upgradeButton = itemView.findViewById(R.id.button_upgrade)
        number = itemView.findViewById(R.id.countDisplay)
        level = itemView.findViewById(R.id.level)
        nonCollectedCount = itemView.findViewById(R.id.nonCollectedCount)
        progressIndicator = itemView.findViewById(R.id.progress_linear)
    }

    fun bind(collectable: Collectable) {

        setButtonColors(collectable)
        setButtonIcons(collectable)

        collectButton?.isEnabled = collectable.unlocked
        upgradeButton?.isEnabled = collectable.unlocked
        nonCollectedCount?.text = collectable.getNotCollectCountDisplay()
        number?.text =
            if (collectable.unlocked) collectable.getCountDisplay() else itemView.context.getString(
                R.string.locked
            )

        level?.text =
            if (collectable.unlocked) collectable.level.toString() else ""

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
            confirmAction(collectable, Action.UPGRADE)
        }
        mainButton?.setOnClickListener { view ->
            if (collectable.unlocked) {
                val navController = Navigation.findNavController(view)
                collectable.navigate(navController)
            } else {
                confirmAction(collectable, Action.UNLOCK)
            }
        }
        collectButton?.setOnClickListener {
            collectable.collect()
            updateCollectable(collectable)
        }
        progressIndicator?.progress = collectable.getProgress()

        nonCollectedCount?.isVisible = collectable.unlocked
        if (!collectable.unlocked) {
            progressIndicator?.hide()
        } else {
            progressIndicator?.show()
        }
    }

    private fun setButtonIcons(collectable: Collectable) {
        if (collectable.unlocked) {
            mainButton?.setIconResource(collectable.getIconId())
            collectButton?.setIconResource(R.drawable.ic_baseline_color_lens_24)
            upgradeButton?.setIconResource(R.drawable.ic_baseline_keyboard_double_arrow_up_24)
        } else {
            mainButton?.setIconResource(R.drawable.ic_baseline_lock_open_24)
            collectButton?.setIconResource(R.drawable.ic_baseline_lock_24)
            upgradeButton?.setIconResource(R.drawable.ic_baseline_lock_24)
        }
    }


    fun setButtonColors(collectable: Collectable) {


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

        val buttonColor = ColorStateList(states, colors)
        collectButton?.backgroundTintList = buttonColor
        mainButton?.backgroundTintList = buttonColor
        upgradeButton?.backgroundTintList = buttonColor
    }
}
