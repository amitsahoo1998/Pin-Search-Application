package com.neoapp.pinsearch.presenter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neoapp.pinsearch.R
import com.neoapp.pinsearch.data.model.PostOffice
import com.neoapp.pinsearch.presenter.component.FailureDialog
import com.neoapp.pinsearch.presenter.component.LoaderDialog
import com.neoapp.pinsearch.presenter.view.PinViewModel
import com.neoapp.pinsearch.utils.collectState

@Composable
fun PinSearchScreen(viewModel: PinViewModel,navigateToHistoryScreen: () -> Unit) {
    val state by viewModel.collectState()

    PinSearchScreenContent(
        pinCode = state.pinCode,
        postOfficeList = state.postOfficeResponse,
        pinCodeChange = viewModel::setPinCode,
        isLoading = state.isLoading,
        error = state.apiError,
        onDialogDismiss = viewModel::clearError,
        searchBtnClick = viewModel::pinSearch,
        navigateToHistoryScreen = navigateToHistoryScreen
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinSearchScreenContent(
    pinCode: String,
    pinCodeChange: (String) -> Unit,
    isLoading : Boolean,
    error : String?,
    onDialogDismiss : () -> Unit,
    searchBtnClick: () -> Unit,
    postOfficeList: List<PostOffice>,
    navigateToHistoryScreen : () -> Unit
) {
    if (isLoading){
        LoaderDialog()
    }

    if (error != null){
        FailureDialog(failureMessage = error, onDialogDismiss = onDialogDismiss)
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Pin Search") }, actions = {
            Row (modifier = Modifier.clickable {navigateToHistoryScreen.invoke() }) {
                Text(text = "History")
                Icon(painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = "Search History")
            }
        }, elevation = 1.dp, backgroundColor = MaterialTheme.colorScheme.primary)
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                value = pinCode,
                onValueChange = pinCodeChange,
                label = { Text("Pin Code") },
                placeholder = { Text("Enter Pin Code") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                )
            )
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = searchBtnClick) {
                    Text(text = "Search Pin")
                }
            }
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

@Composable
fun PinCardWide(
    name: String, circle: String,branchType: String, district: String,
    division: String, region: String,
    block: String, state: String,
    country: String, pinCode: String
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = androidx.compose.material.MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { }
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = pinCode,
                    fontSize = 18.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.W600
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Circle :- $circle",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = "District :- $district",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Division :- $division",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = "Region :- $region",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Branch Type :- $branchType",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = "Block :- $block",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = state,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = country,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W600
                )
            }
        }
    }
}

