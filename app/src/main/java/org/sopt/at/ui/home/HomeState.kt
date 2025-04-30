package org.sopt.at.ui.home

import org.sopt.at.data.home.HomeOnAir
import org.sopt.at.data.home.HomeRanking

data class HomeState(
    val mainBanners: List<Int> = emptyList(),
    val rankBanners: List<HomeRanking> = emptyList(),
    val onAirImages: List<Int> = emptyList()
)

