package com.ersaditya.dependenciesinjection

import android.content.Context
import com.ersaditya.data.database.KrsDatabase
import com.ersaditya.repository.LocalRepositoryMhs
import com.ersaditya.repository.RepositoryMhs

interface InterfaceContainerApp{ //semua repo masuk kesini
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context): InterfaceContainerApp
{
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao()) }


}