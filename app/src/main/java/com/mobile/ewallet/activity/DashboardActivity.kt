package com.mobile.ewallet.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.ewallet.R
import com.mobile.ewallet.ui.HomeUi

class DashboardActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                ContentApp()
            }
        }
    }
}

@Composable
fun ContentApp() {
    val screens = listOf(
        Screen.Home,
        Screen.Wallet,
        Screen.Statistic,
        Screen.Setting,
    )
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
                screens.forEach { screen ->
                    AddItem(screen, navController)
                }
            }
        },
        content = { padding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(padding)) {
                    composable(Screen.Home.route) { HomeUi(navController) }
                    composable(Screen.Wallet.route) { WalletUi(navController) }
                    composable(Screen.Statistic.route) { StatisticUi(navController) }
                    composable(Screen.Setting.route) { SettingUi(navController) }
                }
            }
        }
    )
}

@Composable
fun RowScope.AddItem(screen: Screen,  navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selected = currentRoute == screen.route
    val color = if (selected) Color(0xFF0C41E1) else Color.LightGray

    BottomNavigationItem(
        icon = {
            Image(
                painterResource(id = screen.icon),
                contentDescription = "",
                colorFilter = ColorFilter.tint(color)
            )
        },
        label = {
            Text(stringResource(screen.resourceId), color = color)
        },
        selectedContentColor = Color.White,
        unselectedContentColor = Color.White.copy(0.4f),
        selected = currentRoute == screen.route,
        onClick = {
            navController.navigate(screen.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        },
        modifier = Modifier.padding(8.dp)
    )

}
@Composable
fun <NavHostController> StatisticUi(navController: NavHostController) {
    Column {
    }
}

@Composable
fun SettingUi(navController: NavHostController) {
    Column {
    }
}

@Composable
fun <NavHostController> WalletUi(navHostController: NavHostController) {
    Column {
    }
}

@Preview(showBackground = true)
@Composable
fun ContentAppPreview() {
    ContentApp()
}

sealed class Screen(val route: String, @DrawableRes val icon: Int, @StringRes val resourceId: Int) {
    data object Home : Screen("home", R.drawable.ic_action_home, R.string.home)
    data object Wallet : Screen("wallet", R.drawable.ic_action_wallet, R.string.wallet)
    data object Statistic : Screen("statistic", R.drawable.ic_action_analytics,  R.string.statistic)
    data object Setting : Screen("setting",R.drawable.ic_action_account_setting, R.string.setting)
}