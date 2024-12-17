package com.ersaditya.repository

import com.ersaditya.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

// tiap fungsi di DAO harus ada juga di reposityo
interface RepositoryMhs
{
    suspend fun insertMhs(mahasiswa: Mahasiswa)

    fun getAllMhs(): Flow<List<Mahasiswa>>

    fun getMhs(nim: String): Flow<Mahasiswa>

    suspend fun deleteMhs(mahasiswa: Mahasiswa)

    suspend fun updateMhs(mahasiswa: Mahasiswa)
}