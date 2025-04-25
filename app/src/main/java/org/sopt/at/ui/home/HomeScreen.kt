package org.sopt.at.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.data.HomeOnAir
import org.sopt.at.data.HomeRanking

@Composable
fun HomeScreen() {

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

    )

    val rankBanners = rankImage.mapIndexed { index, resId ->
        HomeRanking(imageRes = resId, rank = index+1)
    }

    val onAirImage = listOf(
        R.drawable.img_rank_8,
        R.drawable.img_rank_7,
        R.drawable.img_rank_6,
        R.drawable.img_rank_5
    )



    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(vertical = 16.dp))
    {

        item {

            Text(

                text = "TVING",
                color = Color.Red,
                fontWeight = Bold,
                fontSize = 24.sp
            )

        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }


        item {

            LazyRow(

                contentPadding = PaddingValues(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                items(banners) { banner ->
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

                items(rankBanners) { item ->

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
                        .clickable {  }
                )
            }

        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {


                items(onAirImage) { item ->

                    OnAirItem(poster = item)

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
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 5f),
            contentScale = ContentScale.Crop
        )
    }


}

@Composable
fun RankingItem(rank : HomeRanking) {

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
fun OnAirItem(poster : Int) {

    Image(
        painter = painterResource(id = poster),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(180.dp)
            .aspectRatio(2f/3f)
            .clip(RoundedCornerShape(8.dp))
    )

}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    HomeScreen()
}