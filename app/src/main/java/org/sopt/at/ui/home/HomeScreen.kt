package org.sopt.at.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.data.home.HomeRanking
import org.sopt.at.ui.component.TvingHomeTopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val state by viewModel.state.collectAsState()


    val context = LocalContext.current


    Scaffold(
        topBar = { TvingHomeTopBar() },

        ) { innerPadding ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {


            item {
                Spacer(modifier = Modifier.height(16.dp))
            }


            item {

                LazyRow(

                    contentPadding = PaddingValues(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    items(state.mainBanners) { banner ->
                        BannerItem(imageRes = banner)
                    }


                }

            }

            item {
                Spacer(modifier = Modifier.height(26.dp))
            }

            item {

                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = "오늘의 TVING TOP 20",
                    color = Color.White,
                    fontWeight = Bold,
                    fontSize = 14.sp
                )

            }


            item {

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(state.rankBanners) { item ->

                        RankingItem(rank = item)

                    }

                }

            }

            item {
                Spacer(modifier = Modifier.height(26.dp))
            }


            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(

                        text = "지금 방영 중인 콘텐츠",
                        color = Color.White,
                        fontWeight = Bold,
                        fontSize = 14.sp
                    )

                    Text(

                        text = "더보기",
                        color = Color.LightGray,
                        fontWeight = Medium,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable { }
                    )
                }

            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {


                    items(state.onAirImages) { item ->

                        OnAirItem(poster = item)

                    }
                }
            }
        }
    }
}

@Composable
fun BannerItem(imageRes: Int) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(13.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "홈 배너",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 5f),
            contentScale = ContentScale.Crop
        )
    }


}

@Composable
fun RankingItem(rank: HomeRanking) {

    Row(
        verticalAlignment = Alignment.Bottom
    ) {

        Text(
            text = "${rank.rank}",
            color = Color.White,
            fontWeight = Bold,
            fontSize = 80.sp,
            fontStyle = FontStyle.Italic,


            )

        Spacer(modifier = Modifier.height(4.dp))

        Image(
            painter = painterResource(id = rank.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(180.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(8.dp))
        )

    }
}


@Composable
fun OnAirItem(poster: Int) {

    Image(
        painter = painterResource(id = poster),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(180.dp)
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(8.dp))
    )

}


