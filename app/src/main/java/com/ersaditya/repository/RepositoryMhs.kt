package com.ersaditya.repository

import com.ersaditya.data.entity.Mahasiswa
// tiap fungsi di DAO harus ada juga di reposityo
interface RepositoryMhs
{
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}