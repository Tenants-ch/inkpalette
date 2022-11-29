package ch.tenants.inkpalette.ui.convert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConvertViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Converter Fragment"
    }
    val text: LiveData<String> = _text
}