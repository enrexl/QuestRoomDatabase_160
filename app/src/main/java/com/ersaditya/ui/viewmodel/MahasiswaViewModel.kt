package com.ersaditya.ui.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersaditya.data.entity.Mahasiswa
import com.ersaditya.repository.RepositoryMhs
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs) : ViewModel(){

    var uiState by mutableStateOf(MhsUIState())

    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.angkatan.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //Menyimpan data ke repository
    fun saveData(){
        val currentEvent = uiState.mahasiswaEvent
        if (validateFields()){
            viewModelScope.launch{
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(), // Reset input form
                        isEntryValid = FormErrorState() // Reset Error state
                    )
                }

                catch (e: Exception){
                 uiState = uiState.copy(
                     snackBarMessage = "Data gagal Disimpan"
                 )
                }
            }
        }
        else{
            uiState=uiState.copy(
                snackBarMessage = "Input tidak valid. Berikan Kembali"
            )
        }
    }
    fun resetSnackBarMessage(){
        uiState=uiState.copy(
            snackBarMessage = "Input tidak valid. Berikan Kembali"
        )
    }
}

data class MhsUIState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
){
    fun isValid(): Boolean{
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}


//data class Variable yang menyimpan data input form
data class MahasiswaEvent(
    val nim: String ="",
    val nama: String ="",
    val jenisKelamin: String ="",
    val alamat: String ="",
    val kelas: String ="",
    val angkatan: String ="",
)

// Menyimpan input form ke dalam activity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama ,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)