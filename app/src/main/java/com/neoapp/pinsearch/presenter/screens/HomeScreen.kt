package com.neoapp.pinsearch.presenter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.neoapp.pinsearch.R
import com.neoapp.pinsearch.data.model.PostOffice
import com.neoapp.pinsearch.presenter.component.MenuTabItem

@Composable
fun HomeScreen(navigateToPinSearch: () -> Unit, navigateToPostOffice: () -> Unit , navigateToHistoryScreen: () -> Unit) {

    HomeScreenContent(
        navigateToPinSearch = navigateToPinSearch,
        navigateToPostOffice = navigateToPostOffice,
        navigateToHistoryScreen = navigateToHistoryScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(navigateToPinSearch: () -> Unit, navigateToPostOffice: () -> Unit , navigateToHistoryScreen : () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Pin Search Application") }, actions = {
            Row (modifier = Modifier.clickable {navigateToHistoryScreen.invoke() }) {
                Text(text = "History")
                Icon(
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = "Search History"
                )
            }
        }, elevation = 1.dp, backgroundColor = MaterialTheme.colorScheme.primary)
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                MenuTabItem(
                    title = "Pin Code",
                    description = "Search With Pin Code",
                    icon = R.drawable.logo_pin_code,
                    color = Color(0xFFECF6EE),
                    modifier = Modifier
                        .weight(1.0f)
                        .height(200.dp)
                        .width(150.dp),
                    onClick = { navigateToPinSearch.invoke() }
                )
                MenuTabItem(
                    title = "Post Office",
                    description = "Search With Post Office",
                    icon = R.drawable.logo_post_office,
                    color = Color(0xFFEBD6E5),
                    modifier = Modifier
                        .weight(1.0f)
                        .height(200.dp)
                        .width(150.dp),
                    onClick = { navigateToPostOffice.invoke() }
                )
            }
        }
    }
}
