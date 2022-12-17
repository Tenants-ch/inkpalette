package ch.tenants.inkpalette.ui.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.data.StatisticRepository
import ch.tenants.inkpalette.model.Statistic
import ch.tenants.inkpalette.model.collectable.Collectable
import ch.tenants.inkpalette.model.enums.ColorEnum
import ch.tenants.inkpalette.model.enums.WorkerEnum
import kotlinx.coroutines.launch


class StatisticViewModel(application: Application) :
    AndroidViewModel(application) {

    private var statisticRepository: StatisticRepository =
        StatisticRepository(AppDatabase.getDatabase(application))
    var statisticsLiveData: LiveData<List<Statistic>> =
        statisticRepository.getAll()

}