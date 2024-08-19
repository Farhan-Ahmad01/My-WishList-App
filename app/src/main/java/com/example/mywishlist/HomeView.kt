package com.example.mywishlist

import android.graphics.Color.rgb
import android.net.ipsec.ike.SaProposal
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButtonElevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlist.Data.Wish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    veiwModel: WishViewModel
) {
    val context = LocalContext.current
    Scaffold(
//        topBar = { AppBarView(title = "Wish List", {
//            Toast.makeText(context, "Button Clicked(chatt se..!)", Toast.LENGTH_SHORT).show()
//        })},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          navController.navigate(Screen.AddScreen.rout+"/0L")
                },
                modifier = Modifier.padding(all= 20.dp),
                contentColor = Color.White,
                containerColor = Color.Black,
                shape = CircleShape
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(rgb(95, 179, 194))),
        )
             {
                Column(
                    modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val wishList = veiwModel.getAllWishes.collectAsState(initial = listOf())
                    
                    Spacer(modifier = Modifier.height(40.dp))
                    
                    Text(text = "W I S H  L I S T",
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )

                        LazyColumn(modifier = Modifier
                            .fillMaxSize()
                            .padding(it)) {
                            items(wishList.value, key = {Wish -> Wish.id}) {
                                    wish ->

                                val dismissState = rememberDismissState(
                                    confirmValueChange = {
                                        if(it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                                            veiwModel.deleteWish(wish)
                                            Toast.makeText(context, "Wish Removed", Toast.LENGTH_SHORT).show()
                                        }
                                        true
                                    }
                                )

                                SwipeToDismiss(state = dismissState,
                                    background = {

                                    },
                                    directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                                    dismissContent = {
                                        WishItem(wish = wish) {
                                            val id = wish.id
                                            navController.navigate(Screen.AddScreen.rout+"/$id")
                                        }
                                    }
                                )
                            }
                        }
                    }
        }

    }
}

@Composable
fun WishItem(wish: Wish, onClick: () -> Unit) {
    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, bottom = 15.dp, end = 12.dp)
            .clickable { onClick() },
        elevation = 30.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Text(text = wish.description, color = Color.Black, fontSize = 15.sp)
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeView(navController = NavController(), view)
//}