package com.ersaditya.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ersaditya.data.entity.Mahasiswa

@Dao
interface MahasiswaDao {

    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
}