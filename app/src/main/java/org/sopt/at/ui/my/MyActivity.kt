package org.sopt.at.ui.my

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.data.AuthPreferences
import org.sopt.at.ui.component.TvingBasicButton
import org.sopt.at.ui.signin.SignInActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val userId = intent.getStringExtra("id") ?: "id"
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {


                val context = this


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyPage(
                        modifier = Modifier.padding(innerPadding),
                        userId = userId,
                        onLogoutClick = { logout(context) }
                    )
                }
            }
        }
    }
}

@Composable
fun MyPage(
    modifier: Modifier = Modifier,
    userId: String,
    onLogoutClick: () -> Unit
) {


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

            onClick = onLogoutClick
        )

    }


}


fun logout(context: Context) {


    val authPrefs = AuthPreferences(context)
    authPrefs.setLoggedIn(false)

    val intent = Intent(context, SignInActivity::class.java)
    context.startActivity(intent)
    (context as? Activity)?.finish()
}

