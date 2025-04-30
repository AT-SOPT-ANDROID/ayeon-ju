package org.sopt.at.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.R
import org.sopt.at.data.home.HomeRanking
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state :StateFlow<HomeState> = _state.asStateFlow()

    init {
        updateBannerData()
    }



    private fun updateBannerData() {

        val banners = listOf(

            R.drawable.banner_1,
            R.drawable.banner_2,
            R.drawable.banner_3

        )


        val rankImage = listOf(
            R.drawable.img_rank_1,
            R.drawable.img_rank_2,
            R.drawable.img_rank_3,
            R.drawable.img_rank_4,
            R.drawable.img_rank_5,
            R.drawable.img_rank_6,
            R.drawable.img_rank_7,
            R.drawable.img_rank_8,
            R.drawable.img_rank_1,
            R.drawable.img_rank_2,
            R.drawable.img_rank_3,
            R.drawable.img_rank_4,
            R.drawable.img_rank_5,
            R.drawable.img_rank_6,
            R.drawable.img_rank_7,
            R.drawable.img_rank_8,
        )

        val rankBanners = rankImage.mapIndexed { index, resId ->
            HomeRanking(imageRes = resId, rank = index + 1)
        }

        val onAirImage = listOf(
            R.drawable.img_rank_8,
            R.drawable.img_rank_7,
            R.drawable.img_rank_6,
            R.drawable.img_rank_5
        )

        _state.value = _state.value.copy(
            mainBanners = banners,
            rankBanners = rankBanners,
            onAirImages = onAirImage
        )
    }



}