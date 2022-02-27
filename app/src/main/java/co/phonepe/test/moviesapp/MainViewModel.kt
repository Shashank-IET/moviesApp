package co.phonepe.test.moviesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.phonepe.test.moviesapp.data.Repo
import co.phonepe.test.moviesapp.data.local.models.Playlist
import co.phonepe.test.moviesapp.data.remote.models.DataPacket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    fun getMoviesData(): LiveData<Resource<List<DataPacket>>>{
        val result = MutableLiveData<Resource<List<DataPacket>>>()
        result.value = Resource.loading()
        viewModelScope.launch {
            val data = repo.getMoviesList()
            withContext(Dispatchers.Main){
                if(data != null)
                    result.value = Resource.success(data)
                else result.value = Resource.error(Exception("No Data Found"))
            }
        }
        return result
    }

    fun getPlayLists(): LiveData<Resource<List<Playlist>>>{
        val result = MutableLiveData<Resource<List<Playlist>>>()
        result.value = Resource.loading()
        viewModelScope.launch {
            val data = repo.getPlayLists()
            withContext(Dispatchers.Main){
                if(data != null)
                    result.value = Resource.success(data)
                else result.value = Resource.error(Exception("No Data Found"))
            }
        }
        return result
    }

    fun getVideosInPlaylist(playlist: Playlist): LiveData<Resource<List<DataPacket>>>{
        val result = MutableLiveData<Resource<List<DataPacket>>>()
        result.value = Resource.loading()
        viewModelScope.launch {
            val data = repo.getPlayListData(playlist.id)
            withContext(Dispatchers.Main){
                if(data != null)
                    result.value = Resource.success(data)
                else result.value = Resource.error(Exception("No Data Found"))
            }
        }
        return result
    }

    fun createNewPlayList(name: String){
        val playlist = Playlist(System.currentTimeMillis(),name, "")
        viewModelScope.launch {
            repo.createPlayList(playlist)
        }
    }
}