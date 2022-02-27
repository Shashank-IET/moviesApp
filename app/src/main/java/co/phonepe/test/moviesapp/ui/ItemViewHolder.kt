package co.phonepe.test.moviesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.phonepe.test.moviesapp.R
import co.phonepe.test.moviesapp.data.remote.models.DataPacket
import com.bumptech.glide.Glide

class ItemViewHolder(
    mView: View,
     private val listener: MoviesListAdapterContract? = null
     ): RecyclerView.ViewHolder(mView) {

    companion object{
        fun inflate( parent: ViewGroup,
                     listener: MoviesListAdapterContract,
                     layId: Int = R.layout.item_ui): ItemViewHolder {
            return ItemViewHolder(LayoutInflater.from(parent.context).inflate(layId, parent), listener)
        }
    }

    fun bind(data: DataPacket){
        itemView.findViewById<TextView>(R.id.tv_title).text = data.title
        itemView.findViewById<TextView>(R.id.tv_rating).text = data.rating.toString()
        itemView.findViewById<TextView>(R.id.tv_desc).text = data.desc
        Glide.with(itemView.context).load(data.imgUrl).into(
            itemView.findViewById<ImageView>(R.id.iv_bg)
        )
        itemView.findViewById<TextView>(R.id.tv_add_to_playlist)
            .setOnClickListener {
                listener?.onClickAddToPlayList(data.id)
            }
    }
}