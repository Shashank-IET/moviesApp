package co.phonepe.test.moviesapp.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.phonepe.test.moviesapp.data.remote.models.DataPacket

class MoviesListAdapter(
    private val data: List<DataPacket>,
    private val listener: MoviesListAdapterContract
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder.inflate(parent, listener)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(data.get(position))
    }
    override fun getItemCount(): Int = data.size
}

interface MoviesListAdapterContract{
    fun onClickAddToPlayList(videoId: Int)
}

