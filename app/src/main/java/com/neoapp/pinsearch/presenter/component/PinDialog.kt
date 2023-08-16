package com.neoapp.pinsearch.presenter.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.neoapp.pinsearch.R


@Composable
fun LoaderDialog() {
    Dialog(onDismissRequest = {}) {
        Surface(modifier = Modifier.size(128.dp)) {
            LottieAnimation(
                resId = R.raw.loading,
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp)
            )
        }
    }
}

@Composable
fun FailureDialog(failureMessage: String, onDialogDismiss: () -> Unit = {}) {
    Dialog(onDismissRequest = onDialogDismiss) {
        androidx.compose.material.Surface {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LottieAnimation(
                    resId = R.raw.failure,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(84.dp)
                )
                Text(
                    text = failureMessage,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                Button(
                    onClick = onDialogDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(16.dp)

                ) {
                    Text(
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White,
                        text = "OK"
                    )
                }
            }
        }
    }
}