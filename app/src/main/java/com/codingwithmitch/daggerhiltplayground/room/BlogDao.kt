package com.codingwithmitch.daggerhiltplayground.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity) : Long

    @Query("Select * From blogs")
    suspend fun get(): List<BlogCacheEntity>
}