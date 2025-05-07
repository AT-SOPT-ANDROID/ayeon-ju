package org.sopt.at.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object ATSOPTANDROIDTheme{

    val colors: TvingColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTvingColorsProvider.current

    val typography: TvingTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTvingTypographyProvider.current

}

@Composable
fun ProvideATSOPTANDROIDColorAndTypography(
    colors: TvingColors,
    typography: TvingTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTvingColorsProvider provides colors,
        content = content
    )
}


@Composable
fun ATSOPTANDROIDTheme(
    content: @Composable () -> Unit
) {
    ProvideATSOPTANDROIDColorAndTypography (
        colors = defaultTvingColors,
        typography = defaultTvingTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
                }
            }
        }

        MaterialTheme(
            content = content
        )
    }
}