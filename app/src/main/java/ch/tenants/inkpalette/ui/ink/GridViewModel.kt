package ch.tenants.inkpalette.ui.ink

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.model.collectable.Collectable
import ch.tenants.inkpalette.model.enums.ColorEnum
import ch.tenants.inkpalette.model.enums.WorkerEnum


class GridViewModel(application: Application, section: Int, color: ColorEnum?, workerEnum: WorkerEnum?) :
    AndroidViewModel(application) {

    private var collectableRepository: CollectableRepository =
        CollectableRepository(AppDatabase.getDatabase(application))
    var collectableLiveData: LiveData<List<Collectable>> =
        collectableRepository.getCollectablesByValues(section, color, workerEnum)

}