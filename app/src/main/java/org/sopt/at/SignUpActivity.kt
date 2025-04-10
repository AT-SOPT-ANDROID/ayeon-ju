package org.sopt.at

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.component.SignUpScreen
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import kotlin.coroutines.coroutineContext

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    SignUp(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}


@Composable
fun SignUp(modifier: Modifier = Modifier) {

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isIdScreen by remember { mutableStateOf(true) }
    var isVisiblePassword by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // 아이디 입력
    if (isIdScreen) {
        SignUpScreen(
            title = "아이디를 입력해주세요.",
            placeholder = "아이디",
            buttonText = "다음",
            onNextClick = { input ->
                if (!input.matches(Regex("^[a-z0-9]{6,12}\$"))) {
                    Toast.makeText(
                        context,
                        "아이디는 영문 소문자, 숫자로 이루어진 6~12 자리여야합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@SignUpScreen
                }
                id = input
                isIdScreen = false
            }
        )

    }
    // 비밀번호 입력
    else {

        SignUpScreen(
            title = "비밀번호를 입력해주세요",
            placeholder = "비밀번호",
            buttonText = "다음",
            isPasswordField = !isVisiblePassword,
            onNextClick = { input ->
                if (!input.matches(Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,12}\$"))) {
                    Toast.makeText(
                        context,
                        "비밀번호는 영문, 숫자, 특수문자를 포함한 8~12자리여야 합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@SignUpScreen
                }
                password = input

                val resultIntent = Intent().apply {
                    putExtra("id", id)
                    putExtra("password", password)
                }

                (context as? Activity)?.setResult(Activity.RESULT_OK, resultIntent)
                (context as? Activity)?.finish()

            },

            // 눈모양 Button

            trailingIcon = {

                val icon = if (isVisiblePassword) {
                    R.drawable.ic_password_visible
                } else {
                    R.drawable.ic_password_invisible
                }

                IconButton(onClick = {
                    isVisiblePassword = !isVisiblePassword
                }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "비밀번호 표시 아이콘"
                    )
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ATSOPTANDROIDTheme {
        SignUp()
    }
}