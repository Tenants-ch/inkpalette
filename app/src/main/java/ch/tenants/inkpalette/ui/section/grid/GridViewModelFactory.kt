package ch.tenants.inkpalette.ui.section.grid

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import ch.tenants.inkpalette.model.Colors
import ch.tenants.inkpalette.model.Worker


class GridViewModelFactory(
    private val mApplication: Application,
    private val section: Int,
    private var color: Colors?,
    private var worker: Worker?
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        @Suppress("UNCHECKED_CAST")
        return GridViewModel(mApplication, section, color, worker) as T
    }
}