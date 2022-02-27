package co.phonepe.test.moviesapp.data.remote

import android.app.Application
import co.phonepe.test.moviesapp.R
import co.phonepe.test.moviesapp.data.remote.models.ContainerPacket
import co.phonepe.test.moviesapp.data.remote.models.DataPacket
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiSource @Inject constructor(
    private val application: Application
) {
    suspend fun getListData(): List<DataPacket>?{
        return withContext(Dispatchers.IO){
            val text = application.resources.
                openRawResource(R.raw.data)
                .bufferedReader().use { it.readText() }
            val data: ContainerPacket = Gson().fromJson(text, ContainerPacket::class.java)
            return@withContext data.results
        }
    }
}