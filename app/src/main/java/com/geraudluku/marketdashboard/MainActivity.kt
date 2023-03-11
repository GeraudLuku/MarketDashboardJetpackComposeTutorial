package com.geraudluku.marketdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geraudluku.marketdashboard.ui.theme.MarketDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketDashboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    val processList = listOf(
        "Verification process with team",
        "Launch process with collegues"
    )
    val flows = listOf("document verification", "newbie onboarding")
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(text = "Welcome back,", fontSize = 16.sp, color = Color.Gray)
                    Text(text = "Carolina Terner", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }

                Box {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "This is a profile picture",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(RoundedCornerShape(50.dp))
                    )

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(border = BorderStroke(3.dp, color = Color.Black))
                            .background(Color.Black)
                            .align(alignment = Alignment.BottomStart),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "2",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )

                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = colorResource(id = R.color.wheat)
            ) {
                BottomNavigationItem(
                    selected = true, onClick = { /*TODO*/ }, icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home Icon"
                        )
                    }
                )

                BottomNavigationItem(
                    selected = false, onClick = { /*TODO*/ }, icon = {
                        Icon(
                            imageVector = Icons.Outlined.AccountBox,
                            contentDescription = "Home Icon"
                        )
                    }
                )

                BottomNavigationItem(
                    selected = false, onClick = { /*TODO*/ }, icon = {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Home Icon"
                        )
                    }
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
                    .padding(start = 16.dp)
            ) {

                // Search Bar
                SearchBar()

                // Spacer
                Spacer(modifier = Modifier.height(30.dp))

                // Heading Text
                Text(
                    text = "Task-based \nExplanation Process",
                    modifier = Modifier.padding(start = 16.dp, bottom = 20.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 30.sp
                )


                //Recycler view item
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    item {
                        LeadingRowItem()
                    }

                    items(processList) { item ->
                        ProcessItem(item)
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                //Bottom Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    //Heading Section
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Flows List",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 30.sp
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "",
                                tint = Color.Black
                            )
                        }
                    }

                    // see all section
                    Text(
                        text = "See all",
                        color = Color.Gray,
                        fontSize = 16.sp,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Pending Action List

                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    itemsIndexed(flows) { index, flow ->
                        // Pending Actions List Item
                        FlowItem(flow)
                        if (index < flows.lastIndex) {
                            Divider(
                                color = Color.LightGray,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 16.dp)
                            )
                        }
                    }
                }

            }
        },
        modifier = Modifier,
        backgroundColor = colorResource(id = R.color.wheat)
    )
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MarketDashboardTheme {
        Content()
    }
}

@Composable
fun SearchBar() {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp)),
        value = textState.value,
        onValueChange = { textState.value = it },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(text = "Try to find...", color = Color.LightGray, fontSize = 16.sp)
        }

    )
}

@Composable
fun LeadingRowItem() {
    Box(
        modifier = Modifier
            .width(width = 190.dp)
            .padding(start = 16.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Gray,
                    style = Stroke(
                        width = 2f,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(10f, 10f),
                            0f
                        )
                    ),
                    cornerRadius = CornerRadius(8.dp.toPx())
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .padding(20.dp),
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = colorResource(id = R.color.green)
                ),
                modifier = Modifier.clip(RoundedCornerShape(15.dp))
            ) {
                Text(
                    "Add Task",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Creative for branding",
                fontSize = 18.sp,
                color = Color.Black
            )

        }
    }
}

@Composable
fun ProcessItem(item: String) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        colorResource(id = R.color.wheat)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Review",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                item,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun FlowItem(flow: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        //Left Section
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = flow.capitalizeWords(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Text(text = "3 mins ago", color = Color.Gray, fontSize = 15.sp)
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(colorResource(id = R.color.green))
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.Black,
                contentDescription = "",
                modifier = Modifier.padding(7.dp)
            )
        }
    }
}

fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")