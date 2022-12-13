package ch.tenants.inkpalette

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import ch.tenants.inkpalette.data.AppDatabase
import ch.tenants.inkpalette.data.CollectableRepository

class GameService : Service() {

    private var running: Boolean = true
    private var collectableRepository: CollectableRepository? = null

    private var thread : Thread? = null


    private fun run() {
        var count = 0
        while (running && count <= 199999) {
            val allCollectable = collectableRepository?.getAllUnlockedCollectables()
            allCollectable?.forEach { collectable ->
                collectable.tick()
                collectableRepository?.updateCollectable(collectable)
            }

            val allWorkers = collectableRepository?.getAllUnlockedWorkers()
            allWorkers?.forEach { collectable ->
                collectable.tick()
                collectableRepository?.updateCollectable(collectable)
            }

            val allUpgrades = collectableRepository?.getAllUnlockedUpgrades()
            allUpgrades?.forEach { collectable ->
                collectable.tick()
                collectableRepository?.updateCollectable(collectable)
            }
            count++
            Thread.sleep(1_000)
        }
    }

    override fun onCreate() {
        super.onCreate()
        thread = Thread {
            collectableRepository = CollectableRepository(AppDatabase.getDatabase(application))
            run()
        }
    }

    // execution of service will start
    // on calling this method
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        thread?.start()
        // returns the status
        // of the program
        return START_STICKY
    }

    // execution of the service will
    // stop on calling this method
    override fun onDestroy() {
        super.onDestroy()

        // stopping the process
        running = false
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}