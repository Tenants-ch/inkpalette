package ch.tenants.inkpalette.ui.grid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.tenants.inkpalette.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GridViewModel : ViewModel() {

    private val game: Game = TODO();

    private val _text = MutableLiveData<String>().apply {
        value = "This is grid Fragment"
    }
    val text: LiveData<String> = _text
    fun runGame() {
        viewModelScope.launch(Dispatchers.IO) {
            game.run()
        }
    }
}