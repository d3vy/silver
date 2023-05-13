package ru.devy.silver.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Landscape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import io.ktor.utils.io.*
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SilverScreen() {
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            TopAppBar {
                var query by remember {
                    mutableStateOf("")
                }
                TextField(
                    value = query,
                    onValueChange = { new ->
                        query = new
                    }, label = {
                        Text("search")
                    }, trailingIcon = {
                        Icon(Icons.Filled.Search, "")
                    }, modifier = Modifier.fillMaxWidth()
                )
            }
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(true, {}, icon = {
                    Icon(Icons.Rounded.Home, "")
                },
                    label = { Text("Main") }
                )
                BottomNavigationItem(false, {}, icon = {
                    Icon(Icons.Filled.Category, "")
                },
                    label = { Text("Catalog") }
                )
                BottomNavigationItem(false, {}, icon = {
                    Icon(Icons.Filled.ShoppingCart, "")
                },
                    label = { Text("Cart") })
                BottomNavigationItem(false, {}, icon = {
                    Icon(Icons.Filled.Favorite, "")
                },
                    label = { Text("Favorites") })
                BottomNavigationItem(false, {}, icon = {
                    Icon(Icons.Filled.Person, "")
                },
                    label = { Text("Profile") })
            }
        },
        floatingActionButton = {
            FloatingActionButton({}){
                Icon(Icons.Filled.PlusOne,"")
            }
        }
    ) { paddings ->

        Text("Hello")
        Column(modifier = Modifier.padding(paddings).fillMaxSize().verticalScroll(rememberScrollState())) {
            HorizontalPager(
                5,
                contentPadding = PaddingValues(16.dp),
                pageSpacing = 16.dp,
                state = pagerState
            ) {
                Card(modifier = Modifier.fillMaxWidth().aspectRatio(35 / 45f)) {
                }
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                val coroutineScope = rememberCoroutineScope()
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                ) {
                    Text("Previous")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                ) {
                    Text("Next")
                }

            }
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Spacer(Modifier)
                CategoryCard(modifier = Modifier.width(100.dp), label = "Серьги")
                CategoryCard(modifier = Modifier.width(100.dp), label = "Серьги")
                CategoryCard(modifier = Modifier.width(100.dp), label = "Серьги")
                CategoryCard(modifier = Modifier.width(100.dp), label = "Серьги")
                CategoryCard(modifier = Modifier.width(100.dp), label = "Серьги")
                Spacer(Modifier)
            }
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                CollectionCard(
                    title = "My darling",
                    description = "Для дорогой и любимой",
                    modifier = Modifier.fillMaxWidth().aspectRatio(35 / 22f)
                )
                CollectionCard(
                    title = "My darling",
                    description = "Для дорогой и любимой",
                    modifier = Modifier.fillMaxWidth().aspectRatio(35 / 22f)
                )
                CollectionCard(
                    title = "My darling",
                    description = "Для дорогой и любимой",
                    modifier = Modifier.fillMaxWidth().aspectRatio(35 / 22f)
                )
                CollectionCard(
                    title = "My darling",
                    description = "Для дорогой и любимой",
                    modifier = Modifier.fillMaxWidth().aspectRatio(35 / 22f)
                )
            }
            OutlinedButton(onClick = {},Modifier.fillMaxWidth().padding(horizontal = 16.dp)){ Text("All collections")}

        }
    }
}

@Composable
fun CollectionCard(title: String, description: String, modifier: Modifier) {
    Card(modifier) {
        Box(Modifier.fillMaxSize()){
            Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                Text(title.uppercase(Locale.getDefault()), style=MaterialTheme.typography.h5)
                Text(description)
            }
        }
    }
}

@Composable
fun CategoryCard(modifier: Modifier, label: String) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(Modifier.fillMaxWidth().aspectRatio(1f)) { }
        Text(label)
    }
}
