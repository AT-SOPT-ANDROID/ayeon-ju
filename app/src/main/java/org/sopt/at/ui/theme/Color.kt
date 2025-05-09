package org.sopt.at.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Gray01 = Color(0xFFAAAAAA)
val Gray02 = Color(0xFF888888)
val Gray03 = Color(0xFF666666)
val Gray04 = Color(0xFF444444)
val Gray05 = Color(0xFF222222)

val BasicBlack = Color(0xFF000000)
val BasicWhite = Color(0xFFFFFFFF)
val BrandRed = Color(0xFFFF1F45)

@Immutable
data class TvingColors(

    val gray01: Color,
    val gray02: Color,
    val gray03: Color,
    val gray04: Color,
    val gray05: Color,
    val basicBlack: Color,
    val basicWhite: Color,
    val brandRed: Color


)


val defaultTvingColors = TvingColors(
    gray01 = Gray01,
    gray02 = Gray02,
    gray03 = Gray03,
    gray04 = Gray04,
    gray05 = Gray05,
    basicBlack = BasicBlack,
    basicWhite = BasicWhite,
    brandRed = BrandRed

)

val LocalTvingColorsProvider = staticCompositionLocalOf { defaultTvingColors }