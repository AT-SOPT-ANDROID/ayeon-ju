package org.sopt.at.ui.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId.asStateFlow()

    private val _userNickname = MutableStateFlow("")
    val userNickname: StateFlow<String> = _userNickname.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut: StateFlow<Boolean> = _isLoggedOut.asStateFlow()


    init {

        viewModelScope.launch {
            userRepository.getUserIdLong().collect { userId ->

                if (userId != -1L) {
                    val result = userRepository.getNickname(userId)
                    if (result.isSuccess) {
                        _userNickname.value = result.getOrNull()?.nickname ?: ""
                    }
                }
            }
        }
    }
}

