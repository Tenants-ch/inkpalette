package ch.tenants.inkpalette

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.data.StatisticRepository
import ch.tenants.inkpalette.databinding.ActivityMainBinding
import ch.tenants.inkpalette.model.Action
import ch.tenants.inkpalette.model.collectable.Collectable
import ch.tenants.inkpalette.model.enums.StatisticEnum
import ch.tenants.inkpalette.ui.dialogs.BuyOrUpgradeDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), BuyOrUpgradeDialog.BuyDialogListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var collectableRepository: CollectableRepository? = null
    private var statisticRepository: StatisticRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectableRepository = CollectableRepository(AppDatabase.getDatabase(applicationContext))
        statisticRepository = StatisticRepository(AppDatabase.getDatabase(applicationContext))


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.mainContainer.navView

        setSupportActionBar(binding.toolbar)
        startService(Intent(this, GameService::class.java))

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_ink, R.id.navigation_statistics, R.id.navigation_grid
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.open_settings_fragment ->{
                val navController = findNavController(R.id.nav_host_fragment_activity_main)
                navController.navigate(R.id.settings)
                true}
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDialogPositiveClick(
        dialog: DialogFragment, collectable: Collectable, action: Action
    ) {
        val listEnum: MutableList<StatisticEnum> = mutableListOf(StatisticEnum.BUTTONS, StatisticEnum.BUTTON_CONFIRM, StatisticEnum.BUY_WITH_INK)

        lifecycleScope.launch(Dispatchers.IO) {
            collectableRepository?.performActionOnCollectable(collectable, action)
            statisticRepository?.addStats(listOf(StatisticEnum.BUTTONS, StatisticEnum.BUTTON_CONFIRM, StatisticEnum.BUY_WITH_INK))
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        lifecycleScope.launch(Dispatchers.IO) {
            statisticRepository?.addStats(listOf(StatisticEnum.BUTTONS, StatisticEnum.BUTTON_CANCEL))
        }
    }
}