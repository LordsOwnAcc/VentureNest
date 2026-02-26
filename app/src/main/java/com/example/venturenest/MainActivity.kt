 package com.example.venturenest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.lazy.grid.items

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil.compose.AsyncImage

import com.example.bottombar.AnimatedBottomBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.ItemStyle
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AuthViewModel
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.LoadingStateViewmodel
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.DataBase.DataViewModel
import com.example.venturenest.ui.theme.Navigation.AboutECell
import com.example.venturenest.ui.theme.Navigation.AboutVentureNest
import com.example.venturenest.ui.theme.Navigation.AchievementPage
import com.example.venturenest.ui.theme.Navigation.ContactPage
import com.example.venturenest.ui.theme.Navigation.CouncilScreen
import com.example.venturenest.ui.theme.Navigation.EventsPage
import com.example.venturenest.ui.theme.Navigation.GalleryPage
import com.example.venturenest.ui.theme.Navigation.HomePage
import com.example.venturenest.ui.theme.Navigation.LoginPage
import com.example.venturenest.ui.theme.Navigation.NavigationItems
import com.example.venturenest.ui.theme.Navigation.Profile
import com.example.venturenest.ui.theme.Navigation.SignUpPage
import com.example.venturenest.ui.theme.Navigation.Start
import com.example.venturenest.ui.theme.Navigation.StartScreen
import com.example.venturenest.ui.theme.Navigation.partnerScreen
import com.example.venturenest.ui.theme.Navigation.startupsScreen
import com.example.venturenest.ui.theme.Presentation.EventPage.ReEvent
import com.example.venturenest.ui.theme.Presentation.Setting.AboutEcell
import com.example.venturenest.ui.theme.Presentation.Setting.AboutVenturenest
import com.example.venturenest.ui.theme.Presentation.Setting.ProfilePage

import com.example.venturenest.ui.theme.VentureNestTheme
import com.example.venturenest.ui.theme.auth.AuthStateCompanion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color(0xffA30D33).toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color(0xffA30D33).toArgb())
        )
        setContent {
            val autViewmodel: AuthViewModel = hiltViewModel()
            val dataViewModel: DataViewModel = hiltViewModel()
            val homeViewModel: LoadingStateViewmodel = hiltViewModel()

            val state by autViewmodel.authState.collectAsState()
            var navController = rememberNavController()
            val windowInsets = androidx.compose.foundation.layout.WindowInsets.statusBars

            VentureNestTheme {

                val bottomBarRoutes = listOf(
                    "com.example.venturenest.ui.theme.Navigation.HomePage",
                    "com.example.venturenest.ui.theme.Navigation.EventsPage",
                    "com.example.venturenest.ui.theme.Navigation.GalleryPage",

                   "com.example.venturenest.ui.theme.Navigation.AchievementPage"
                )
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination
                val shouldShowBottomBar = bottomBarRoutes.contains(currentDestination?.route)



                LaunchedEffect(key1 = Unit) {


                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (shouldShowBottomBar) {

                            AnimatedBottomBar(
                                containerColor = Color.White,
                                modifier = Modifier, contentColor = Color.Black


                            ) {

                                NavigationItems.entries.forEach { it ->
                                    BottomBarItem(
                                        selected = currentDestination?.route == it.route,
                                        onClick = {
                                            if (currentDestination?.route != it.route) {
                                                navController.navigate(it.route) {
                                                    popUpTo(navController.graph.startDestinationId) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }
                                        },
                                        imageVector = it.icon,
                                        label = it.title, contentColor = Color.White,
                                        iconColor = Color.Black,
                                        textColor = Color.White, itemStyle = ItemStyle.STYLE4
, modifier = Modifier.scale(0.8f)

                                    )
                                }

                            }
                        }
                    }) { innerPadding ->

                    // Global Pull-to-Refresh
                    // Global Pull-to-Refresh with Material 3
                    @OptIn(ExperimentalMaterial3Api::class)
                    val pullRefreshState = rememberPullToRefreshState()
                    val coroutineScope = rememberCoroutineScope()

                    if (pullRefreshState.isRefreshing) {
                        LaunchedEffect(true) {
                            homeViewModel.fetchData()
                            delay(1500)
                            pullRefreshState.endRefresh()
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(pullRefreshState.nestedScrollConnection)
                    ) {
                        com.example.venturenest.ui.theme.Presentation.helper.HideSystemBars()
                        NavHost(
                            navController = navController,
                            startDestination = if (state.state == AuthStateCompanion.UserExist) Start else StartScreen,
                        enterTransition = {
                            when (targetState.destination.route) {
                                "com.example.venturenest.ui.theme.Navigation.HomePage" -> {
                                    slideIntoContainer(
                                        AnimatedContentTransitionScope.SlideDirection.Right,
                                        animationSpec = tween(
                                            durationMillis = 100,
                                            easing = FastOutSlowInEasing
                                        )
                                    )
                                }
                                else -> {
                                    slideIntoContainer(
                                        AnimatedContentTransitionScope.SlideDirection.Left,
                                        animationSpec = tween(
                                            durationMillis = 100,
                                            easing = FastOutSlowInEasing
                                        )
                                    )
                                }
                            }
                        },
                        exitTransition = {
                            when (targetState.destination.route) {
                                "com.example.venturenest.ui.theme.Navigation.HomePage" -> {
                                    slideOutOfContainer(
                                        AnimatedContentTransitionScope.SlideDirection.Left,
                                        animationSpec = tween(
                                            durationMillis = 100,
                                            easing = FastOutSlowInEasing
                                        )
                                    )
                                }
                                else -> {
                                    slideOutOfContainer(
                                        AnimatedContentTransitionScope.SlideDirection.Right,
                                        animationSpec = tween(
                                            durationMillis = 100,
                                            easing = FastOutSlowInEasing
                                        )
                                    )
                                }
                            }
                        },
                        popEnterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                tween(100, easing = FastOutSlowInEasing)
                            )
                        },
                        popExitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                tween(100, easing = FastOutSlowInEasing)
                            )
                        }

                    ) {

                        composable<HomePage> {

                            com.example.venturenest.ui.theme.Presentation.HomePage.HomePage(
                                window = windowInsets, navController = navController
                            , homeViewModel = homeViewModel)
                        }

                        composable<EventsPage> {
                            ReEvent(windowInsets, Modifier,navController)
                        }
                        composable<AchievementPage> {

                            com.example.venturenest.ui.theme.Presentation.AchievementsPage.ExpandableBottoSheet(
                                windowInsets,
                                Modifier, navController
                            )

                        }
                        composable<ContactPage> {
                            com.example.venturenest.ui.theme.Presentation.Setting.AboutPage(window = windowInsets, navController = navController)
                        }

                        composable<LoginPage> {
                            com.example.venturenest.ui.theme.Presentation.Login.LoginPage(
                                windowInsets,
                                navController
                            )
                        }
                        composable<StartScreen> {
                            com.example.venturenest.ui.theme.Presentation.Login.StartScreen(
                                windowInsets,
                                navController
                            )
                        }
                        composable<SignUpPage> {
                            com.example.venturenest.ui.theme.Presentation.Login.SignUpPage(
                                windowInsets,
                                navController
                            )
                        }
                        composable<GalleryPage> {
                            com.example.venturenest.ui.theme.Presentation.Gallery.GalleryScreen(window = windowInsets)
                        }

                        composable<CouncilScreen> {
                            val args = it.toRoute<CouncilScreen>()
                            com.example.venturenest.ui.theme.Presentation.AchievementsPage.CouncilScreen(
                                windowInsets, Modifier,
                                args.search,
                                category = args.category,
                                color = args.color

                            )
                        }
                        composable<startupsScreen> {
                            val args = it.toRoute<startupsScreen>()
                            com.example.venturenest.ui.theme.Presentation.AchievementsPage.startupsScreen(
                                windowInsets, Modifier, args.search, args.type, args.contain
                            )
                        }
                        composable<partnerScreen> {
                            val args = it.toRoute<partnerScreen>()
                            com.example.venturenest.ui.theme.Presentation.AchievementsPage.partnerScreen(
                                windowInsets, Modifier, args.search, args.color, args.category
                            )
                        }
                        composable<Start> {
                            com.example.venturenest.ui.theme.Presentation.Profile.ProfileStarter(
                                navController, Modifier, windowInsets
                            )
                        }
                        composable<AboutVentureNest> {
                            AboutVenturenest(modifier = Modifier,navController,windowInsets)
                        }
                        composable<AboutECell> {
                            AboutEcell(modifier = Modifier,navController,windowInsets)
                        }

                        composable<Profile> {
                            ProfilePage(Modifier,navController)
                        }

                    }
                        PullToRefreshContainer(
                            state = pullRefreshState,
                            modifier = Modifier.align(Alignment.TopCenter),
                            containerColor = Color.White,
                            contentColor = Color.Gray
                        )
                    }
                }
            }
        }

    }}

@Composable
fun MeetTheBoardCard(
    members: List<councilmembers>,
    onclick:()-> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.96f)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
        , colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ðŸ”¹ CENTERED TITLE
            Text(
                text = "Meet the Board",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "The visionaries leading our mission",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.heightIn(max = 320.dp)
            ) {
                items(members) { member ->
CouncilMemberItem(member)
                }
            }
            Button(onClick = onclick
                , shape = RoundedCornerShape(25f)
                , //colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.2f))
             modifier = modifier.padding(top = 16.dp).fillMaxWidth(0.9f).border(width = 1.dp,
                color = Color(0xFFA30D33), shape = RoundedCornerShape(7.5.dp))) {
                Text("view all"
                , color = Color(0xFFA30D33))
            }
        }
    }
}

@Composable
fun CouncilMemberItem(member: councilmembers) {



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        AsyncImage(
            model = member.imgpath,
            contentDescription = member.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = member.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = member.company,
            fontSize = 12.sp,
            color = com.example.venturenest.ui.theme.bg,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun OurJourneyCard(
    imageRes: String,
    modifier: Modifier = Modifier
) {

        Column(modifier.fillMaxWidth(0.9f).padding(top = 16.dp)) {

            // ðŸ”¹ Top Image
            AsyncImage(
                model = imageRes,
                contentDescription = "Our Journey",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(15f))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ðŸ”¹ Content
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Our Journey",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Founded in 2015, our club has become the premier hub for innovation on campus. We bridge the gap between academic theory and real-world startup execution, fostering a community of ambitious creators and leaders.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "What started as a small group of three students in a basement has grown into a vibrant ecosystem of over 200 active members, collaborating across disciplines to solve tomorrowâ€™s problems today.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

    }
}


@Composable
fun StatCard(
    item: StatItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(180.dp)
            .height(110.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFBFBFB)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, Color(0xFFF0F0F0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.value,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = com.example.venturenest.ui.theme.bg
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black.copy(alpha = 0.6f),
                lineHeight = 14.sp
            )
        }
    }
}
data class StatItem(
    val value: String,
    val label: String
)
val stats = listOf(
    StatItem("50+", "Startups Launched"),
    StatItem("$2M+", "Funding Raised"),
    StatItem("200+", "Active Members"),
    StatItem("30+", "Industry Mentors"),
    StatItem("100+", "Events Hosted")
)




