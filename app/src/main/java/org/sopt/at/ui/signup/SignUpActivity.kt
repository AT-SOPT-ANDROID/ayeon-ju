package org.sopt.at.ui.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.R
import org.sopt.at.ui.signin.SignInActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignUpActivity : ComponentActivity() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {




                    SignUpScreen(
                        viewModel = viewModel,
                        onSignUpComplete = {

                            val intent = Intent().apply {
                                putExtra("id", viewModel.id)
                                putExtra("password", viewModel.password)
                            }

                            setResult(Activity.RESULT_OK, intent)
                            finish()

                        }
                        )


            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ATSOPTANDROIDTheme {
        //SignUp()
    }
}