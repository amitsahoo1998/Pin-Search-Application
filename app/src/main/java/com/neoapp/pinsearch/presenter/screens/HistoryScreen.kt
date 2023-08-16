package com.neoapp.pinsearch.presenter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.neoapp.pinsearch.R
import com.neoapp.pinsearch.data.local.PinCodeEntity
import com.neoapp.pinsearch.presenter.view.PinHistoryViewModel
import com.neoapp.pinsearch.utils.collectState

@Composable
fun HistoryScreen(viewModel: PinHistoryViewModel) {

    val state by viewModel.collectState()

    HistoryScreenContent(clearHistoryClick = viewModel::deleteAllHistoryData, postOfficeList = state.pinHistory)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreenContent(clearHistoryClick : () -> Unit , postOfficeList : List<PinCodeEntity>) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Pin Search") }, actions = {
            Row (modifier = Modifier.clickable {clearHistoryClick.invoke() }, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Clear All")
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "delete History")
            }
        }, elevation = 1.dp, backgroundColor = MaterialTheme.colorScheme.primary)
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = Modifier.testTag("Pin List")
            ) {
                items(items = postOfficeList,
                    itemContent = { post ->
                        PinCardWide(
                            name = post.name,
                            circle = post.circle,
                            branchType = post.branchType,
                            district = post.district,
                            division = post.division,
                            region = post.region,
                            block = post.block ?: "",
                            state = post.state,
                            country = post.country,
                            pinCode = post.pinCode
                        )
                    }
                )
            }
        }
    }
}
