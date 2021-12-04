package Presentacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.marveltptaller.Modelo.CharactersService
import com.example.marveltptaller.datos.KtorCharactersRepository


class CharactersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val charactersApi = KtorCharactersRepository()
        val charactersService = CharactersService(charactersApi)
        return CharactersViewModel(charactersService) as T
    }
}
