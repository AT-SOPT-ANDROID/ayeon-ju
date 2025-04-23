package org.sopt.at.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme


@Composable
fun SignUpTextField(

    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    isPasswordField: Boolean = false,
    isVisiblePassword: Boolean = false,
    onPasswordVisibilityToggle: () -> Unit,
    onNextClick: (String) -> Unit,
    trailingIcon: (@Composable (() -> Unit))? = null,


    ) {


    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth(),

        label = { Text(label, color = Color.Gray) },
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            disabledContainerColor = Color.DarkGray,
            focusedTrailingIconColor = Color.White,
            unfocusedTrailingIconColor = Color.White,

            focusedIndicatorColor = Color.White.copy(alpha = 0.5f),
            errorIndicatorColor = Color.Red
        ),

        visualTransformation = if (isPasswordField && !isVisiblePassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (isPasswordField) trailingIcon else null

    )
}

