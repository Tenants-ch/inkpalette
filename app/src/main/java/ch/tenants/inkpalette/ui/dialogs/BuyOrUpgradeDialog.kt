package ch.tenants.inkpalette.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.DialogFragment
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.model.Action
import ch.tenants.inkpalette.model.Collectable


class BuyOrUpgradeDialog(val collectable: Collectable, val action: Action) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Build the dialog and set up the button click handlers
            val builder = AlertDialog.Builder(it)

            builder.setTitle(action.title).setMessage(createMessageForAction(action))
                .setIcon(action.icon)
                .setPositiveButton(action.buttonText) { dialog, id ->
                    // Send the positive button event back to the host activity
                    listener.onDialogPositiveClick(this, collectable, action)
                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                    // Send the negative button event back to the host activity
                    listener.onDialogNegativeClick(this)
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    // Use this instance of the interface to deliver action events
    private lateinit var listener: BuyDialogListener

    interface BuyDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, collectable: Collectable, action: Action)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as BuyDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(
                ("$context must implement BuyDialogListener")
            )
        }
    }

    private fun createMessageForAction(action: Action): SpannableString {
        val realCost = collectable.giveCostForAction(action)
        val messageString =
            SpannableString(getString(R.string.spend_quantity_icon, realCost.quantity))
        val d = resources.getDrawable(realCost.getCostIcon(), context?.theme)
        d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
        d.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
            realCost.colors.color, BlendModeCompat.SRC_ATOP
        )
        val span = ImageSpan(d, ImageSpan.ALIGN_BOTTOM)
        messageString.setSpan(
            span,
            messageString.toString().indexOf("@"),
            messageString.toString().indexOf("@") + 1,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

/*
            messageString.setSpan(
                ForegroundColorSpan(realCost.colors.color),
                spannableString.length - 5,
                spannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );
*/
        return messageString
    }
}