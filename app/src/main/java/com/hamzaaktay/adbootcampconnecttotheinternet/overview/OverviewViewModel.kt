package com.hamzaaktay.adbootcampconnecttotheinternet.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzaaktay.adbootcampconnecttotheinternet.network.CharacterModel
import com.hamzaaktay.adbootcampconnecttotheinternet.network.HarryPotterApi
import com.hamzaaktay.adbootcampconnecttotheinternet.network.HarryPotterApiService
import kotlinx.coroutines.launch

enum class HarryPotterApiStatus { LOADING, ERROR, DONE }

enum class HarryPotterApiFilter (val filterWord: String) {
    SHOW_GRYFFINDOR("Gryffindor"),
    SHOW_HUFFLEPUFF("Hufflepuff"),
    SHOW_RAVENCLAW("Ravenclaw"),
    SHOW_SLYTHERIN("Slytherin"),
}

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<HarryPotterApiStatus>()
    val status: LiveData<HarryPotterApiStatus>
        get() = _status

    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>>
        get() = _characters

    private val _navigateToSelectedCharacter = MutableLiveData<CharacterModel?>()
    val navigateToSelectedCharacter : LiveData<CharacterModel?>
        get() = _navigateToSelectedCharacter

    init {
        //viewmodel oluşturulduğunda bunları çalıştır hemen.
        getCharacters ()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            _status.value = HarryPotterApiStatus.LOADING //--> Veri çekme işlemi başladı

            try {
                _characters.value = HarryPotterApi.retrofitService.getCharacters().characters
                _status.value = HarryPotterApiStatus.DONE // --> veri geldi ve verileri göster
            } catch (e: Exception) {
                _status.value = HarryPotterApiStatus.ERROR //--> hata geldi
                _characters.value = ArrayList() //--> boş veri göster
            }

        }
    }


    fun filterCharacters (filter: HarryPotterApiFilter) { //Filter Word ulaşacağız.
        viewModelScope.launch {
            _status.value = HarryPotterApiStatus.LOADING // --> Önce tüm verileri yükle

            try {
                _characters.value = HarryPotterApi.retrofitService.filterCharacters(filter.filterWord).characters
                _status.value = HarryPotterApiStatus.DONE // --> veri geldi ve verileri göster
            } catch (e: Exception) {
                _status.value = HarryPotterApiStatus.ERROR //--> hata geldi

            }
    }
     }


    fun displayCharacterDetail (characterModel: CharacterModel) {
        _navigateToSelectedCharacter.value = characterModel
    }

    fun displayCharacterDetailCompleted () {
        _navigateToSelectedCharacter.value = null
    }






}

