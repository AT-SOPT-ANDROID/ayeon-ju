package org.sopt.at.ui.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.at.R
import org.sopt.at.ui.component.SignUpTextField
import org.sopt.at.ui.component.TvingBasicButton
import org.sopt.at.ui.signup.SignUpViewModel.SignUpStep
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme


@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignUpSuccess: () -> Unit

) {

    val userId by viewModel.userId.collectAsState()
    val userPassword by viewModel.userPassword.collectAsState()
    val userNickname by viewModel.userNickname.collectAsState()

    val isPasswordVisible by viewModel.isPasswordVisible.collectAsState()

    val signUpStep by viewModel.signUpStep.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.signUpResult.collect { isSuccess ->
            if (isSuccess) {
                onSignUpSuccess()
            } else {
                Toast.makeText(context, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }

        }
    }

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

        when (signUpStep) {

            SignUpStep.ID -> {

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

                            viewModel.onNextClick(onSignUpSuccess)


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
            }

            SignUpStep.PASSWORD -> {

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
                                Icon(
                                    painter = painterResource(icon),
                                    contentDescription = "비밀번호 보기"
                                )
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

                            viewModel.onNextClick(onSignUpSuccess)

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

            SignUpStep.NICKNAME -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "닉네임을 입력해주세요",
                        color = ATSOPTANDROIDTheme.colors.basicWhite,
                        fontSize = 20.sp,
                        fontWeight = Bold

                    )

                    Spacer(modifier = Modifier.padding(10.dp))


                    SignUpTextField(
                        value = userNickname,
                        onValueChange = viewModel::updateNickname,
                        label = "닉네임",
                        onPasswordVisibilityToggle = {},
                        isPasswordField = false,
                        isVisiblePassword = false,
                        onNextClick = {},
                        trailingIcon = null


                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "한글/영문/숫자만 사용 가능, 1자 이상 20자 이하",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.fillMaxWidth()
                    )

                }


                TvingBasicButton(
                    text = "다음",
                    onClick = {
                        if (viewModel.validateNickname()) {

                            viewModel.onCompleteLogin()

                        } else {

                            Toast.makeText(
                                context,
                                "닉네임은 한글/영문/숫자 1~20자여야 합니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }
        }
    }
}






