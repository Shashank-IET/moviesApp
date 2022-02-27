package co.phonepe.test.moviesapp.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.phonepe.test.moviesapp.data.local.models.Playlist

@Database(entities = [Playlist::class], version = 1)
abstract class MRoomDB: RoomDatabase() {

    abstract fun playlistDao(): Offline

    fun getDataBase(application: Application): MRoomDB {
        return Room.databaseBuilder(application, MRoomDB::class.java, "roomDB")
                .build()
    }
}