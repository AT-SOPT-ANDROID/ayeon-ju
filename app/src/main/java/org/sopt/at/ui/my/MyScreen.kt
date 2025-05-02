package org.sopt.at.ui.my

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.sopt.at.data.AuthPreferences
import org.sopt.at.ui.component.TvingBasicButton
import kotlin.jvm.java
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyScreen(

    viewModel: MyViewModel = hiltViewModel(),
    onLogOut : () -> Unit

) {

    val userId by viewModel.userId.collectAsStateWithLifecycle()
    val isLoggedOut by viewModel.isLoggedOut.collectAsStateWithLifecycle()


    LaunchedEffect(isLoggedOut) {
        if (isLoggedOut) {
            onLogOut()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {

        Text(
            text = "ID : ${userId}",
            color = Color.White,
            fontSize = 24.sp
        )

        TvingBasicButton(
            text = "로그아웃",

            onClick = {
                onLogOut()
            }
        )

    }


}







