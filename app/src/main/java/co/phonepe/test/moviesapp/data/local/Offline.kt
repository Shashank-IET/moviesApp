package co.phonepe.test.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import co.phonepe.test.moviesapp.data.local.models.Playlist

@Dao
interface Offline {
    @Insert
    suspend fun addPlaylist(playlist: Playlist)
    @Update
    suspend fun updatePlayList(vararg playlist: Playlist)
    @Query("SELECT * FROM playlist")
    suspend fun loadAll(): List<Playlist>?
    @Query("SELECT * FROM playlist where id =:pid")
    suspend fun getPlayList(pid: Long): List<Playlist>?
}