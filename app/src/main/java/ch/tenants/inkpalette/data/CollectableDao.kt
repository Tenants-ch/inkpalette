package ch.tenants.inkpalette.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CollectableDao {
    @Query("SELECT * FROM collectable")
    fun getAll(): LiveData<List<CollectableEntity>>

    @Query("SELECT * FROM collectable")
    fun getAllSync(): List<CollectableEntity>

    @Query("SELECT * FROM collectable WHERE uid IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<CollectableEntity>

    @Query("SELECT * FROM collectable WHERE name IN (:colors)")
    fun loadAllByColor(colors: List<String>): List<CollectableEntity>

    @Query("SELECT * FROM collectable WHERE section LIKE :section")
    fun loadAllBySection(section: Int): LiveData<List<CollectableEntity>>

    @Query(
        "SELECT * FROM collectable WHERE name LIKE :name LIMIT 1"
    )
    fun findByName(name: String): CollectableEntity

    @Insert
    fun insertAll(vararg collectableEntities: CollectableEntity)

    @Update
    fun updateCollectable(collectableEntity: CollectableEntity)

    @Delete
    fun delete(collectableEntity: CollectableEntity)

    @Insert
    suspend fun insertCollectable(collectableEntity: CollectableEntity)
}