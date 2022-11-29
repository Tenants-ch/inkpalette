package ch.tenants.inkpalette.ui.not_yet_implemented

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.databinding.FragmentNotYetBinding

class NotYetFragment : Fragment() {

    private var _binding: FragmentNotYetBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotYetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val notYetText = binding.notYetText
        notYetText.text = getString(R.string.not_yet_implemented_message)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}