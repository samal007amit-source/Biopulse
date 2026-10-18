package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackedItemDao {
    @Query("SELECT * FROM tracked_items ORDER BY savedAtTimestamp DESC")
    fun getAllTrackedItems(): Flow<List<TrackedItemEntity>>

    @Query("SELECT * FROM tracked_items WHERE type = :type ORDER BY savedAtTimestamp DESC")
    fun getTrackedItemsByType(type: String): Flow<List<TrackedItemEntity>>

    @Query("SELECT * FROM tracked_items WHERE id = :id LIMIT 1")
    suspend fun getTrackedItemById(id: String): TrackedItemEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM tracked_items WHERE id = :id)")
    fun isItemTracked(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: TrackedItemEntity)

    @Update
    suspend fun update(item: TrackedItemEntity)

    @Delete
    suspend fun delete(item: TrackedItemEntity)

    @Query("DELETE FROM tracked_items WHERE id = :id")
    suspend fun deleteById(id: String)
}
