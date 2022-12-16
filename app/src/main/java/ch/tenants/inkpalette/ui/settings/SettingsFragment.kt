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

        val countingPreference: Preference? = findPreference("counting")

        countingPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { preference ->
                val text = preference.text
                if (TextUtils.isEmpty(text)) {
                    "Not set"
                } else {
                    "Length of saved value: " + (text?.length ?: 0)
                }
            }
    }

    fun updateStatistic(stats: List<StatisticEnum>) {
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


    fun resetCollectables() {
        lifecycleScope.launch(Dispatchers.IO) {
            collectableRepository?.initCollectables()
        }
    }

    fun resetStatistics() {
        lifecycleScope.launch(Dispatchers.IO) {
            statisticRepository?.initStatistics()
        }
    }
}