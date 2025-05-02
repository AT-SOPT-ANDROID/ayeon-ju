package org.sopt.at.ui.my

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.core.content.edit

@HiltViewModel
class MyViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)


   private val _userId = MutableStateFlow("")
   val userId: StateFlow<String> = _userId.asStateFlow()

   private val _isLoggedOut = MutableStateFlow(false)
   val isLoggedOut : StateFlow<Boolean> = _isLoggedOut.asStateFlow()


    init {
        _userId.value = prefs.getString("id", "") ?:""
    }


    fun logout() {
        prefs.edit() { putBoolean("is_logged_in", false) }
        _isLoggedOut.value = true
    }
}

