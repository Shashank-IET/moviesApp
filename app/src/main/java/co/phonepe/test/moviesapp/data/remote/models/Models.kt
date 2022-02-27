package co.phonepe.test.moviesapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class DataPacket(
    @SerializedName("id")
    val id: Int,
    @SerializedName("release_date")
    val relDate: String,
    @SerializedName("backdrop_path")
    val imgUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val desc: String,
    @SerializedName("vote_average")
    val rating: Double
)

data class ContainerPacket(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<DataPacket>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)