package org.sopt.at.ui.signin

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.ui.home.HomeActivity
import org.sopt.at.ui.home.HomeScreen
import org.sopt.at.ui.my.MyActivity
import org.sopt.at.ui.signup.SignUpActivity


@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState
) {

    var idText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var isVisiblePassword by remember { mutableStateOf(false) }

    var signupId by rememberSaveable { mutableStateOf(idText) }
    var signupPassword by rememberSaveable { mutableStateOf(passwordText) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            idText = data?.getStringExtra("id") ?: ""
            passwordText = data?.getStringExtra("password") ?: ""



            signupId = idText
            signupPassword = passwordText
        }

    }



    Column(
        modifier = Modifier
            .background(Color.Black)
    )
    {
        Image(
            painterResource(R.drawable.ic_back_arrow),
            contentDescription = "back arrow", modifier = Modifier.padding(20.dp)
        )


        Spacer(modifier = Modifier.padding(30.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column {

                Text(
                    text = "TVING ID 로그인",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = Bold,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)

                )

                Spacer(modifier = Modifier.padding(10.dp))

                TextField(
                    value = idText,
                    onValueChange = { idText = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(5.dp),

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        disabledContainerColor = Color.DarkGray
                    ),


                    placeholder = {
                        Text(
                            "아이디",
                            textAlign = TextAlign.Center,
                            color = Color.LightGray,
                            fontSize = 14.sp
                        )
                    }
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = passwordText,
                    onValueChange = { passwordText = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(5.dp),


                    visualTransformation = if (isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation(),

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        disabledContainerColor = Color.DarkGray,
                        focusedTrailingIconColor = Color.White,
                        unfocusedTrailingIconColor = Color.White
                    ),

                    trailingIcon = {

                        val icon = if (isVisiblePassword) {
                            R.drawable.ic_password_visible
                        } else {
                            R.drawable.ic_password_invisible
                        }

                        IconButton(onClick = { isVisiblePassword = !isVisiblePassword }) {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = "비밀번호 표시 아이콘"
                            )
                        }

                    },
                    placeholder = {
                        Text(
                            "비밀번호",
                            textAlign = TextAlign.Center,
                            color = Color.LightGray,
                            fontSize = 14.sp
                        )
                    }
                )

                Button(
                    onClick = {
                        if (idText == signupId && passwordText == signupPassword) {

                            val intent = Intent(context, MyActivity::class.java).apply {
                                putExtra("id", idText)
                            }

                            val homeIntent = Intent(context, HomeActivity::class.java).apply {
                                putExtra("id", idText)
                            }

                            context.startActivity(homeIntent)

                        } else {
                            coroutineScope.launch {
                                snackBarHostState.showSnackbar("아이디 또는 비밀번호가 일치하지 않습니다")
                            }
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.DarkGray
                    )
                ) {
                    Text("로그인하기", color = Color.Gray)
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {

                    Text(
                        text = "아이디 찾기",
                        color = Color.White,
                        fontSize = 12.sp

                    )

                    VerticalDivider(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.CenterVertically)
                    )


                    Text(
                        text = "비밀번호 찾기",
                        color = Color.White,
                        fontSize = 12.sp
                    )

                    VerticalDivider(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Text(
                        text = "회원가입",
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .clickable {

                                resultLauncher.launch(
                                    Intent(
                                        context,
                                        SignUpActivity::class.java
                                    )
                                )
                            }
                    )
                }

                Text(
                    text = stringResource(R.string.login_detail_info).replace(
                        "보호되며,",
                        "보호되며,\n"
                    ),
                    color = Color.LightGray,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}