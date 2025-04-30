package org.sopt.at.ui.component.navigation

import androidx.annotation.DrawableRes
import org.sopt.at.R

sealed class BottomNavItem(

    val title : String,
    @DrawableRes val icon : Int,
    val route : String

) {

    data object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    data object Shorts : BottomNavItem("Shorts", R.drawable.ic_shorts, "shorts")
    data object Live: BottomNavItem("Live", R.drawable.ic_live, "live")
    data object Search: BottomNavItem("Search", R.drawable.ic_search, "search")
    data object History: BottomNavItem("History", R.drawable.ic_history, "history")


}