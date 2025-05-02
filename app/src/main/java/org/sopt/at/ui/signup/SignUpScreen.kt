package org.sopt.at.ui.signup

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.at.R
import org.sopt.at.data.AuthPreferences
import org.sopt.at.ui.component.SignUpTextField
import org.sopt.at.ui.component.TvingBasicButton
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import kotlin.math.sin


@Composable
fun SignUpScreen(


    viewModel: SignUpViewModel = hiltViewModel(),
    onSignUpSuccess:() -> Unit

) {

    val userId by viewModel.userId.collectAsState()
    val userPassword by viewModel.userPassword.collectAsState()

    val isPasswordVisible by viewModel.isPasswordVisible.collectAsState()

    val isIdScreen by viewModel.isIdScreen.collectAsState()

    val context = LocalContext.current

    val authPreferences = remember { AuthPreferences(context) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painterResource(R.drawable.ic_back_arrow),
            contentDescription = "back arrow",
            modifier = Modifier.padding(20.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        if (isIdScreen) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "아이디를 입력해주세요.",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = Bold

                )

                Spacer(modifier = Modifier.padding(10.dp))

                SignUpTextField(
                    value = userId,
                    onValueChange = viewModel::updateId,
                    label = "아이디",
                    onPasswordVisibilityToggle = {},
                    isPasswordField = false,
                    isVisiblePassword = false,
                    onNextClick = {},
                    trailingIcon = null
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = "영문 소문자 또는 영문 소문자, 숫자 조합 6~12자리",
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth()
                )


            }

            TvingBasicButton(
                text = "다음",
                onClick = {
                    if (viewModel.validateId()) {
                        viewModel.onNextClick()


                    } else {

                        Toast.makeText(
                            context,
                            "아이디는 영문 소문자, 숫자로 이루어진 6~12 자리여야합니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@TvingBasicButton

                    }
                },

                )

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "비밀번호를 입력해주세요",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = Bold

                )

                Spacer(modifier = Modifier.padding(10.dp))


                SignUpTextField(
                    value = userPassword,
                    onValueChange = viewModel::updatePassword,
                    label = "비밀번호",
                    isPasswordField = true,
                    isVisiblePassword = isPasswordVisible,
                    onPasswordVisibilityToggle = { viewModel.togglePasswordVisibility() },
                    onNextClick = {},
                    trailingIcon = {
                        val icon =
                            if (isPasswordVisible) R.drawable.ic_password_visible else R.drawable.ic_password_invisible
                        IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                            Icon(painter = painterResource(icon), contentDescription = "비밀번호 보기")
                        }

                    }


                )

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = "영문 숫자, 특수문자(~!@#$%^&*) 조합 8~15자리",
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth()
                )

            }



            TvingBasicButton(
                text = "다음",
                onClick = {
                    if (viewModel.validatePassword()) {
                        authPreferences.saveAuthPreference(userId, userPassword)
                        onSignUpSuccess()
                    } else {
                        Toast.makeText(
                            context,
                            "비밀번호는 영문, 숫자, 특수문자를 포함한 8~12자리여야 합니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@TvingBasicButton
                    }
                },

                )
        }


    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview4() {
//    ATSOPTANDROIDTheme {
//
//        SignUpScreen(
//            SignUpViewModel(), {})
//
//
//    }
//}