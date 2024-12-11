package com.ersaditya.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ersaditya.data.dao.MahasiswaDao
import com.ersaditya.data.entity.Mahasiswa


//Define Database dengan tabel Mahasiswa
@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase(){

    //Mendefiniskan fungis untuk mengakses data Mahasiswa
    abstract fun mahasiswaDao(): MahasiswaDao

    companion object{
        @Volatile //Memastikan bahwa nilai variable Instance selalu sama di setiap
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase{
            return (Instance?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java, //class database
                    "KrsDatabase" //Nama Database
                )
                    .build().also { Instance = it }
            }
            )
        }
    }
}