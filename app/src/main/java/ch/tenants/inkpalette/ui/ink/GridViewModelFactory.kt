package ch.tenants.inkpalette.ui.ink

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import ch.tenants.inkpalette.model.enums.ColorEnum
import ch.tenants.inkpalette.model.enums.WorkerEnum


class GridViewModelFactory(
    private val mApplication: Application,
    private val section: Int,
    private var color: ColorEnum?,
    private var workerEnum: WorkerEnum?
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        @Suppress("UNCHECKED_CAST")
        return GridViewModel(mApplication, section, color, workerEnum) as T
    }
}