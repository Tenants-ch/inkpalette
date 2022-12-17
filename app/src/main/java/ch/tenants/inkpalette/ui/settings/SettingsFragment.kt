package ch.tenants.inkpalette.ui.settings

import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.lifecycleScope
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import ch.tenants.inkpalette.R
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.data.StatisticRepository
import ch.tenants.inkpalette.model.enums.StatisticEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsFragment : PreferenceFragmentCompat() {

    private var statisticRepository: StatisticRepository? = null
    private var collectableRepository: CollectableRepository? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        statisticRepository =
            StatisticRepository(AppDatabase.getDatabase(requireContext().applicationContext))

        collectableRepository =
            CollectableRepository(AppDatabase.getDatabase(requireContext().applicationContext))

        setPreferencesFromResource(R.xml.preferences, rootKey)

        val touch: Preference? = findPreference("touch")
        touch?.setOnPreferenceClickListener {
            updateStatistic(
                listOf(
                    StatisticEnum.SETTINGS_CLICKED,
                    StatisticEnum.SETTINGS_TOUCH_CLICKED
                )
            )
            true
        }

    }

    private fun configureResetOptions() {
        val resetGame: Preference? = findPreference("reset_game")
        resetGame?.setOnPreferenceClickListener {
            resetGame()
            true
        }

        val resetCollectables: Preference? = findPreference("reset_collectables")
        resetCollectables?.setOnPreferenceClickListener {
            resetCollectables()
            true
        }

        val resetStatistics: Preference? = findPreference("reset_statistics")
        resetStatistics?.setOnPreferenceClickListener {
            resetStatistics()
            true
        }
    }

    private fun updateStatistic(stats: List<StatisticEnum>) {
        lifecycleScope.launch(Dispatchers.IO) {
            statisticRepository?.addStats(stats)
        }
    }


    fun resetGame() {
        lifecycleScope.launch(Dispatchers.IO) {
            collectableRepository?.initCollectables()
            statisticRepository?.initStatistics()
        }
    }


    private fun resetCollectables() {
        lifecycleScope.launch(Dispatchers.IO) {
            collectableRepository?.initCollectables()
        }
    }

    private fun resetStatistics() {
        lifecycleScope.launch(Dispatchers.IO) {
            statisticRepository?.initStatistics()
        }
    }
}