package com.zhaolongzhong.feature.little.red.book.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.zhaolongzhong.feature.little.red.book.R
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen() {
    TopBar()
    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxWidth().background(color = Color(0xFF505454)),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            Box(modifier = Modifier.pin())
            Column(
                modifier = Modifier.road(
                    whenCollapsed = Alignment.BottomStart, whenExpanded = Alignment.BottomEnd
                )
            ) {
                ProfileSection()
            }
        }) {
        Column(modifier = Modifier) {
            val pages = ProfileTab.values()
            // Reference: https://google.github.io/accompanist/pager/
            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()

            TabRow(
                // Our selected tab is our current page
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(56.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .background(Color.White),
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
                SelectedProfileTab(ProfileTab.values()[pagerState.targetPage])
            }
        }
    }
}

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ProfileImageSection()
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Change your profile description",
            color = Color.White,
            fontSize = 14.sp
        )
        FollowSection()
    }
}

@Composable
fun ProfileImageSection() {
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(
                id = if (Random.nextInt(
                        1,
                        10
                    ) % 2 == 0
                ) R.drawable.plant_1 else R.drawable.plant_2
            ),
            contentDescription = "Localized description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(
                    CircleShape
                )
                .border(1.dp, Color.White, CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column() {
            Text(
                text = "Username",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "RED ID: 2222222",
                color = Color.White.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun FollowSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            FollowBlock(count = 1, title = "Follow")
            FollowBlock(count = 2, title = "Followers")
            FollowBlock(count = 3, title = "Likes & Favs")
        }

        Row {
            OutlinedButton(
                modifier = Modifier
                    .height(30.dp)
                    .padding(0.dp),
                onClick = { },
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent, contentColor = Color.White
                )
            ) {
                Text(
                    text = "Profile Info",
                    color = Color.White, fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(
                modifier = Modifier
                    .height(30.dp)
                    .width(50.dp)
                    .padding(0.dp),
                onClick = { },
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent
                )
            ) {
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = "not a favorite",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun FollowBlock(count: Int, title: String) {
    Column(
        modifier = Modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            color = Color.White, fontSize = 12.sp
        )
        Text(
            text = title,
            color = Color.White, fontSize = 12.sp
        )
    }
}

@Composable
fun TabView() {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Tab 1")
        Text("Tab 2")
        Text("Tab 3")
    }
}

@Composable
fun TabsContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)
    ) {
        repeat(30) {
            Text(text = "hello world", modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.size(24.dp)
        )
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Menu",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.size(24.dp)
        )
    }
}

enum class ProfileTab(val tabName: String) {
    POST("Posts"),
    COLLECTS("Collects"),
    LIKED("Liked"),
}

@Composable
fun SelectedProfileTab(tab: ProfileTab) {
    when (tab) {
        ProfileTab.POST -> {
            TabsContent()
        }
        ProfileTab.COLLECTS -> {
            TabsContent()
        }
        ProfileTab.LIKED -> {
            TabsContent()
        }
    }
}
