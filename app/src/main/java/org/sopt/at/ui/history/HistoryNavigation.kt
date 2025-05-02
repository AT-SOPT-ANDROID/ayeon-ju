package org.sopt.at.ui.history

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.historyNavigation() {

    composable("history") { HistoryScreen() }
}