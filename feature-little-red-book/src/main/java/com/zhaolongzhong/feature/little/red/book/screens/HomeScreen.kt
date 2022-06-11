package com.zhaolongzhong.feature.little.red.book.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.zhaolongzhong.feature.little.red.book.ExploreScreen
import com.zhaolongzhong.feature.little.red.book.FollowScreen
import com.zhaolongzhong.feature.little.red.book.NearbyScreen
import kotlinx.coroutines.launch

enum class LittleRedBookTab(val tabName: String) {
    FOLLOW("Follow"),
    EXPLORE("Explore"),
    Nearby("Nearby"),
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    val pages = LittleRedBookTab.values()
    Column(
        Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color.White)
    ) {
        // Reference: https://google.github.io/accompanist/pager/
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            // Our selected tab is our current page
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .width(270.dp)
                .height(40.dp)
                .background(Color.White)
                .padding(start = 10.dp, end = 10.dp) // Fix a bug at end of the row
                .align(Alignment.CenterHorizontally),
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            // Indicator width is using default Tab width, so setting width will be override
            // by default width, using padding to control width instead
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .padding(start = 30.dp, end = 30.dp),
                    color = Color.Magenta
                )
            }
        ) {
            // Add tabs for all of our pages
            pages.forEachIndexed { index, tab ->
                // Tab margin: https://stackoverflow.com/a/68951035
                Tab(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(start = 0.dp, end = 0.dp),
                    text = {
                        Text(
                            text = tab.tabName,
                            fontSize = 12.sp,
                            color = if (pagerState.targetPage == index) Color.DarkGray else Color.LightGray,
                            modifier = Modifier
                                .padding(start = 0.dp, end = 0.dp)
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            SelectedTab(LittleRedBookTab.values()[pagerState.targetPage])
        }
    }

}

@Composable
fun SelectedTab(tab: LittleRedBookTab) {
    when (tab) {
        LittleRedBookTab.FOLLOW -> {
            FollowScreen()
        }
        LittleRedBookTab.EXPLORE -> {
            ExploreScreen()
        }
        LittleRedBookTab.Nearby -> {
            NearbyScreen()
        }
    }
}
