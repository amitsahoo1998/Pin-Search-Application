package com.neoapp.pinsearch.presenter.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuTabItem(
    modifier: Modifier = Modifier,
    height: Dp = 150.dp,
    color: Color = Color.White,
    textColor : Color = Color.Black,
    title: String,
    description: String,
    onClick : () -> Unit,
    @DrawableRes icon: Int
) {
    Box(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            )
            .clickable { onClick.invoke() }
            .padding(5.dp)
            .height(height)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "new car image",
                modifier = Modifier
                    .width(180.dp)
                    .height(120.dp)
            )
            Text(
                text = title,
                color = textColor,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = description,
                color = textColor,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            )

        }

    }
}