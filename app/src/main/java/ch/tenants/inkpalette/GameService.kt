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


    private fun run() {
        var count = 0
        while (running && count <= 199999) {
            val all = collectableRepository?.getAllUnlocked()
            all?.forEach { collectable ->
                collectable.tick()
                collectableRepository?.updateCollectable(collectable)
            }
            count++
            Log.i("GameService", "we got to the run game")
            Thread.sleep(1_000)
        }
    }

    // execution of service will start
    // on calling this method
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Thread {
            // a potentially time consuming task
            collectableRepository = CollectableRepository(AppDatabase.getDatabase(application))
            run()
        }.start()
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