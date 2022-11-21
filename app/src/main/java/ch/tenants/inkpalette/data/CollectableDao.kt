package ch.tenants.inkpalette.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.tenants.inkpalette.model.Colors
import ch.tenants.inkpalette.model.Worker

@Dao
interface CollectableDao {
    @Query("SELECT * FROM collectable")
    fun getAll(): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable")
    fun getAllSync(): List<CollectableEntity>

    @Query("SELECT * FROM collectable WHERE uid IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<CollectableEntity>

    @Query("SELECT * FROM collectable WHERE section LIKE :section")
    fun loadAllBySection(section: Int): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable WHERE section LIKE :section AND color LIKE :color")
    fun loadAllBySectionAndColor(section: Int, color: Colors): LiveData<List<CollectableEntity>>

    @Query(
        "SELECT * FROM collectable " +
                "WHERE section LIKE :section AND worker LIKE :worker AND color LIKE :color"
    )
    fun loadAllByValues(
        section: Int,
        color: Colors,
        worker: Worker
    ): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable WHERE worker LIKE :worker")
    fun loadAllByWorker(worker: Worker): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable WHERE worker LIKE :worker AND color LIKE :color")
    fun loadAllByColorAndWorker(color: Colors, worker: Worker): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable WHERE color LIKE :color")
    fun loadAllByColor(color: Colors): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable WHERE unlocked = 1")
    fun getAllUnlocked(): List<CollectableEntity>

    @Insert
    fun insertAll(vararg collectableEntities: CollectableEntity)

    @Update
    fun updateCollectable(collectableEntity: CollectableEntity)

    @Delete
    fun delete(collectableEntity: CollectableEntity)

    @Insert
    suspend fun insertCollectable(collectableEntity: CollectableEntity)
}