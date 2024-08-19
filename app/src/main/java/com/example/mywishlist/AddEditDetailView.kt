package com.example.mywishlist

import android.content.Context
import android.graphics.Color.rgb
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlist.Data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val snackMessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if(id != 0L) {
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    } else {
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }


    Scaffold(
//        topBar = {
//            AppBarView(
//                title =
//                    if(id != 0L) "Update Wish" else "Add Wish"
//            ) {
//                navController.navigateUp()
//            }
//        },
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier
            .wrapContentSize()
            .fillMaxSize()
            .padding(it)
            .background(colorResource(id = R.color.myColor2)),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Spacer(modifier = Modifier.heightIn(50.dp))

            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged = {viewModel.onWishTileChanged(it)}
            )
            Spacer(modifier = Modifier.heightIn(10.dp))

            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {viewModel.onWishDescriptionChanged(it)}
            )

            Spacer(modifier = Modifier.heightIn(10.dp))

            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()) {
                    if(id != 0L) {
                            viewModel.updateWish(
                                Wish(
                                    id = id,
                                    title = viewModel.wishTitleState.trim(),
                                    description = viewModel.wishDescriptionState.trim()
                                )
                            )
                        Toast.makeText(context, "Wish Updated", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
//                        snackMessage.value = "Wish Created"
                        Toast.makeText(context, "Wish Created", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // TODO add wish
                    snackMessage.value = "Enter Wish feilds"
                }
                scope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
                             },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black))
            )
            {
                Text(
                    text = if (id != 0L) "Update Wish" else "Add Wish",
                    fontSize = 18.sp,
                    color = Color.White
                 )

            }
            
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    androidx.compose.material3.OutlinedTextField(
        value = value, onValueChange = onValueChanged,
        modifier = Modifier
            .width(350.dp),
        label = { Text(text = label, color = Color.Black) },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

@Preview(showBackground = false)
@Composable
fun ThisPreview() {
    WishTextField(label = "Text", value = "This Value", onValueChanged = {})
}




















