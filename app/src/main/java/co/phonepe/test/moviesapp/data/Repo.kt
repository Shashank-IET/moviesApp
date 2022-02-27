package co.phonepe.test.moviesapp.data

import android.app.Application
import co.phonepe.test.moviesapp.data.local.Offline
import co.phonepe.test.moviesapp.data.local.models.Playlist
import co.phonepe.test.moviesapp.data.remote.ApiSource
import co.phonepe.test.moviesapp.data.remote.models.DataPacket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repo @Inject constructor(
    private val apiSource: ApiSource,
    private val app :Application,
    private val offline: Offline
) {

    suspend fun getMoviesList(): List<DataPacket>? = apiSource.getListData()

    suspend fun getPlayListData(playListId: Long): List<DataPacket>{
        return withContext(Dispatchers.IO) {
            val playlist = offline.getPlayList(playListId)
            val videoIds = playlist?.getOrNull(0)?.movies?.split(",")
            getMoviesList()?.filter {
                videoIds?.contains(it.id.toString()) ?: false
            } ?: emptyList()
        }
    }

    suspend fun getPlayLists(): List<Playlist>?{
        return withContext(Dispatchers.IO) { offline.loadAll() }
    }

    suspend fun createPlayList(playlist: Playlist){
        withContext(Dispatchers.IO){ offline.addPlaylist(playlist) }
    }
}