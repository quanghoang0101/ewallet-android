package com.mobile.ewallet.ui

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.ewallet.R
import com.mobile.ewallet.activity.NotificationActivity

@Composable
fun HomeUi() {
    val context = LocalContext.current
    Column {
        Header(onActionPress = {
            context.startActivity(Intent(context, NotificationActivity::class.java))
        })
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                CardSliderUi()
            }
            item {
                Menu()
            }
            item {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart) {
                    Text(modifier = Modifier.padding(horizontal = 15.dp),
                        text = stringResource(R.string.my_pocket),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                PocketGrid()
            }
        }
    }
}

@Composable
fun Header(onActionPress: () -> Unit) {
    TopAppBar(
        elevation = 0.5.dp,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Column {
                Text(text = stringResource(R.string.good_mornig), color = Color(0xFFb4abaa), fontSize = 13.sp, fontWeight = FontWeight.Normal)
                Text(text = stringResource(R.string.username), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        },
        navigationIcon = {
            Text(modifier = Modifier
                .padding(start = 25.dp)
                .size(20.dp)
                .drawBehind {
                    drawCircle(
                        color = Color(0xFFe8eaed),
                        radius = this.size.maxDimension
                    )
                },
                color = Color(0xFFe8eaee),
                text = "\uD83D\uDC71\u200D♂\uFE0F", textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        actions = {
            IconButton(onClick = onActionPress) {
                Icon(Icons.Outlined.Notifications, null)
            }
        }
    )
}
@Composable
fun Menu() {
    val menuItems = listOf(
        Menu.Transfer,
        Menu.Scan,
        Menu.TopUp,
        Menu.More,
    )
    Row(
        modifier = Modifier.padding(top = 25.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        menuItems.forEach {
            Box(modifier = Modifier.padding(10.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedButton(onClick = { /*TODO*/ },
                        modifier= Modifier.size(60.dp),  //avoid the oval shape
                        shape = CircleShape,
                        border= BorderStroke(1.dp, Color(0xFF011132)),
                        contentPadding = PaddingValues(0.dp),  //avoid the little icon
                        colors = ButtonDefaults.outlinedButtonColors( backgroundColor = Color(0xFF011132))
                    ) {
                        Icon(
                            painter = painterResource(id = it.icon),
                            tint = Color.White,
                            contentDescription = "")
                    }
                    Text(text = stringResource(it.resourceId) ,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF011132)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PocketGrid() {
    val pockets = listOf(
        Pocket.Travel, Pocket.Investment, Pocket.Married, Pocket.Car, Pocket.Kid, Pocket.House,
        Pocket.Pregnant, Pocket.School, Pocket.Health, Pocket.Party, Pocket.Technology
    )
    val padding = 16
    val paddingInside = 10
    val columns = 2
    val itemSizeHeight = (LocalConfiguration.current.screenWidthDp / 2) - padding*3
    val numRows = Math.ceil((pockets.size.toDouble()/columns))
    val height = numRows*itemSizeHeight + numRows*padding*2 + numRows*paddingInside

    LazyVerticalGrid(columns = GridCells.Fixed(columns),
        modifier = Modifier.height(height.dp),
        contentPadding = PaddingValues(padding.dp),
        horizontalArrangement = Arrangement.spacedBy(padding.dp),
        verticalArrangement = Arrangement.spacedBy(padding.dp),
        userScrollEnabled = false) {
        items(pockets.size) { index ->
            val pocket = pockets[index]
            Box(
                modifier = Modifier
                    .border(
                        0.5.dp,
                        Color.LightGray,
                        RoundedCornerShape(8.dp),
                    )
                    .padding(paddingInside.dp)
                    .fillMaxWidth()
                    .height(itemSizeHeight.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            modifier = Modifier
                                .padding(padding.dp)
                                .drawBehind {
                                    drawCircle(
                                        color = Color(0xFFe8eaee),
                                        radius = this.size.maxDimension
                                    )
                                },
                            color = Color(0xFFe8eaee),
                            text = pocket.icon,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "",
                            modifier = Modifier.rotate(90F),
                            tint = Color.LightGray
                        )

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = pocket.title,
                            textAlign = TextAlign.Left,
                            fontSize = 17.sp, color = Color(0xFF939db3)
                        )
                        Text(
                            text = pocket.money,
                            textAlign = TextAlign.Left,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

sealed class Menu(val resourceId: Int, @DrawableRes val icon: Int) {
    data object Transfer: Menu(R.string.transfer, R.drawable.ic_transfer)
    data object Scan: Menu(R.string.scan, R.drawable.ic_scan)
    data object TopUp: Menu(R.string.top_up, R.drawable.ic_topup)
    data object More: Menu(R.string.more, R.drawable.ic_more)
}

sealed class Pocket(val icon: String, val title: String, val money: String) {
    data object Travel: Pocket("\uD83C\uDF34", title = "Travelling", money = "$2.000.00")
    data object Investment: Pocket("\uD83D\uDCB0", title = "Investment", money = "$2.000.50")
    data object Married: Pocket("\uD83D\uDC8D", title = "Married", money = "$4.555.10")
    data object Car: Pocket("\uD83D\uDE97", title = "Car", money = "$2.854.08")
    data object Kid: Pocket("\uD83D\uDC76", title = "Kid", money = "$5.000.00")
    data object House: Pocket("\uD83C\uDFE1", title = "House", money = "$4.100.10")
    data object Pregnant: Pocket("\uD83E\uDD30", title = "Pregnant", money = "$2.000.05")
    data object School: Pocket("\uD83C\uDFEB", title = "School", money = "$3.150.50")
    data object Health: Pocket("\uD83C\uDFE5", title = "Health", money = "$5.000.00")
    data object Party: Pocket("\uD83C\uDF89", title = "Party", money = "$1.000.05")
    data object Technology: Pocket("\uD83D\uDC68\u200D\uD83D\uDCBB", title = "Technology", money = "$3.000.10")
}
