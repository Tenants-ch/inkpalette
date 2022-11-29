package ch.tenants.inkpalette.ui.ink

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.model.Collectable
import ch.tenants.inkpalette.model.Colors
import ch.tenants.inkpalette.model.Worker
import kotlinx.coroutines.launch


class GridViewModel(application: Application, section: Int, color: Colors?, worker: Worker?) :
    AndroidViewModel(application) {

    private var collectableRepository: CollectableRepository =
        CollectableRepository(AppDatabase.getDatabase(application))
    var collectableLiveData: LiveData<List<Collectable>> =
        collectableRepository.getCollectablesByValues(section, color, worker)

    fun updateCollectable(collectable: Collectable) {
        viewModelScope.launch {
            collectableRepository.updateCollectable(collectable)
        }
    }
}