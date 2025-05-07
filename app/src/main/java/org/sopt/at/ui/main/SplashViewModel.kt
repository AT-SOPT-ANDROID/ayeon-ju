package org.sopt.at.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.ui.component.navigation.AuthNavItem
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository : UserRepository

): ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        viewModelScope.launch {
            val isLoggedIn = userRepository.isLoggedIn().first()
            _startDestination.value = if(isLoggedIn) "main" else AuthNavItem.SignIn.route

        }
    }
}