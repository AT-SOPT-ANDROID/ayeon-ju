package org.sopt.at.ui.signin

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.at.data.dto.request.SignInRequestDto
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

//    private val _userId = MutableStateFlow(userRepository.getUserId())
//    val userId : StateFlow<String> = _userId.asStateFlow()
//
//    private val _userPassword = MutableStateFlow(userRepository.getUserPassword())
//    val userPassword : StateFlow<String> = _userPassword.asStateFlow()



    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()



    private val _isVisiblePassword = MutableStateFlow(false)
    val isVisiblePassword = _isVisiblePassword.asStateFlow()


    private val _signInResult = MutableSharedFlow<Boolean>()
    val signInResult: MutableSharedFlow<Boolean> = _signInResult



    fun updateId(id: String) {
       _state.value = _state.value.copy(id = id)

    }

    fun updatePassword(password : String) {
        _state.value = _state.value.copy(password= password)
    }

//    fun isValidUser() : Boolean {
//        return userId.value == userRepository.getUserId() &&
//                userPassword.value == userRepository.getUserPassword()
//    }

    suspend fun isValidUser() : Boolean {
        val savedId = userRepository.getUserId().first()
        val savedPassword = userRepository.getUserPassword().first()

        return _state.value.id == savedId && _state.value.password == savedPassword
    }

//    fun login() {
//        authPreferences.setLoggedIn(true)
//    }


    fun onSuccessLogin(id: String, password: String) {

        viewModelScope.launch {

            val result = userRepository.signInUser(
                SignInRequestDto(id, password)
            )

            if (result.isSuccess) {
                _signInResult.emit(true)

            } else {
                _signInResult.emit(false)
            }
        }

    }




}

