package org.sopt.at.ui.signup

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {


    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId.asStateFlow()

    private val _userPassword = MutableStateFlow("")
    val userPassword: StateFlow<String> = _userPassword.asStateFlow()

    private val _userNickname = MutableStateFlow("")
    val userNickname: StateFlow<String> = _userNickname.asStateFlow()


    enum class SignUpStep {
        ID, PASSWORD, NICKNAME
    }

    private val _signUpStep = MutableStateFlow(SignUpStep.ID)
    val signUpStep = _signUpStep.asStateFlow()

    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible = _isPasswordVisible.asStateFlow()


    private val _isIdScreen = MutableStateFlow(true)
    val isIdScreen = _isIdScreen.asStateFlow()


    companion object {
        private val idRegex = Regex("^[a-z0-9]{6,12}$")
        private val passwordRegex = Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,12}$")
    }



    fun updateId(id : String) {
        _userId.value = id
    }

    fun updatePassword(password : String) {
        _userPassword.value = password
    }



    fun updateNickname(nickname: String) {
        _userNickname.value = nickname
    }

    fun saveUserInfo() {
        viewModelScope.launch {
            userRepository.saveUserInfo(_userId.value, _userPassword.value, _userNickname.value)
        }
    }



    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    fun validateId(): Boolean = _userId.value.matches(idRegex)

    fun validatePassword(): Boolean = _userPassword.value.matches(passwordRegex)

    //onNextClick() 확장

    fun onNextClick(onComplete:() -> Unit ) {
        when(_signUpStep.value) {
            SignUpStep.ID -> {
                if (validateId()) {
                    _signUpStep.value = SignUpStep.PASSWORD
                }


            }

            SignUpStep.PASSWORD -> {
                if (validatePassword()) {
                    _signUpStep.value = SignUpStep.NICKNAME
                }
            }

            SignUpStep.NICKNAME -> {
                if (_userNickname.value.isNotBlank()) {
                    saveUserInfo()
                    onComplete()
                }
            }
        }

    }


}