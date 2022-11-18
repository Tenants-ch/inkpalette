package ch.tenants.inkpalette.ui.grid

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.data.getDatabase
import ch.tenants.inkpalette.ui.model.Collectable


class GridViewModel(application: Application, section: Int) : AndroidViewModel(application) {

    private var collectableRepository: CollectableRepository =
        CollectableRepository(getDatabase(application))
    var collectableLiveData: LiveData<List<Collectable>> =
        collectableRepository.getCollectableBySection(section)


}

class GridViewModelFactory(private val mApplication: Application, private val section: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return GridViewModel(mApplication, section) as T
    }
}