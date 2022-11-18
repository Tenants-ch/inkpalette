package ch.tenants.inkpalette

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import ch.tenants.inkpalette.data.CollectableRepository
import ch.tenants.inkpalette.data.asDomainModel
import ch.tenants.inkpalette.data.getDatabase

class GameService : Service() {

    var running: Boolean = true
    private var collectableRepository: CollectableRepository? = null


    fun run() {
        var count = 0
        while (running && count <= 199999) {
            var all = collectableRepository?.getAllSynced()
            all?.filter { it.unlocked }?.forEach { collectable ->
                collectable.tick()
                collectableRepository?.updateCollectable(collectable.asDatabaseModel())
            }
            count++
            Log.i("GameService", "we got to the run game")
            Thread.sleep(1_000)
        }
    }

    // execution of service will start
    // on calling this method
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Thread(Runnable {
            // a potentially time consuming task
            collectableRepository = CollectableRepository(getDatabase(application))
            run()
        }).start()
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