package com.androiddevs.ucanbit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CoinDatabase::class],
    version = 1
)
abstract class CoinDatabase : RoomDatabase() {


    abstract fun getCoinDao(): CoinDao

    companion object{

        @Volatile
        private var instance: CoinDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CoinDatabase::class.java,
                "coin_db.db"
            ).build()



    }


}