package org.sopt.at.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.at.R


@Composable
fun TvingHomeTopBar() {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_tving_logo),
            contentDescription = "Tving logo",
            modifier = Modifier.height(24.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_cast),
                tint = Color.White,
                contentDescription = null,

                )

            Icon(
                painter = painterResource(id = R.drawable.ic_history),
                tint = Color.White,
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .height(24.dp)
                    .clickable {

                    }
            )
        }
    }
}