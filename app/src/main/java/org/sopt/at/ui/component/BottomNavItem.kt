package org.sopt.at.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.at.R

sealed class BottomNavItem(

    val title : String,
    @DrawableRes val icon : Int,
    val route : String

) {

    data object Home : BottomNavItem("Home", R.drawable.ic_home, "Home")
    data object Shorts : BottomNavItem("Shorts", R.drawable.ic_shorts, "Shorts")
    data object Live: BottomNavItem("Live", R.drawable.ic_live, "Live")
    data object Search: BottomNavItem("Search", R.drawable.ic_search, "Search")
    data object History: BottomNavItem("History", R.drawable.ic_history, "History")


}