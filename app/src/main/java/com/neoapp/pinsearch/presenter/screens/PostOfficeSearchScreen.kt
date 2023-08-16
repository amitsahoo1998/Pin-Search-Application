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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.neoapp.pinsearch.R
import com.neoapp.pinsearch.data.model.PostOffice
import com.neoapp.pinsearch.presenter.component.FailureDialog
import com.neoapp.pinsearch.presenter.component.LoaderDialog
import com.neoapp.pinsearch.presenter.view.PinViewModel
import com.neoapp.pinsearch.utils.collectState

@Composable
fun PostOfficeSearchScreen(viewModel: PinViewModel , navigateToHistoryScreen: () -> Unit) {
    val state by viewModel.collectState()

    PostOfficeSearchScreenContent(
        postOffice = state.postOffice,
        postOfficeList = state.postOfficeResponse,
        pinCodeChange = viewModel::setPostOffice,
        isLoading = state.isLoading,
        error = state.apiError,
        onDialogDismiss = viewModel::clearError,
        searchBtnClick = viewModel::postOfficeSearch,
        navigateToHistoryScreen = navigateToHistoryScreen
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostOfficeSearchScreenContent(
    postOffice: String,
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
        TopAppBar(title = { Text(text = "Post Office Search") }, actions = {
            Row (modifier = Modifier.clickable {navigateToHistoryScreen.invoke() }) {
                Text(text = "History")
                Icon(painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = "Search History")
            }
        }, elevation = 1.dp, backgroundColor = MaterialTheme.colorScheme.primary)
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                value = postOffice,
                onValueChange = pinCodeChange,
                label = { Text("Post Office Name") },
                placeholder = { Text("Enter Post Office") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                )
            )
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = searchBtnClick) {
                    Text(text = "Search Post Office")
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


