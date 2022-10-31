package ch.tenants.inkpalette.ui.grid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GridViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is grid Fragment"
    }
    val text: LiveData<String> = _text
}