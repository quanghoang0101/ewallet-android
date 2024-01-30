package com.mobile.ewallet.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardSliderUi() {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 10 })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 35.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) { page ->
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = pagerState.getOffsetFractionForPage(page).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            // .aspectRatio(0.5f)
        ) {
            Box(
                Modifier.padding(0.dp)) {
                BankCardUi(
                    modifier = Modifier.align(Alignment.Center),
                    cardNumber = "1234567890123456",
                    cardHolder = "PHAN QUANG HOANG",
                    expires = "01/29",
                    cvv = "901",
                    brand = "VISA"
                )
            }
        }
    }
}
