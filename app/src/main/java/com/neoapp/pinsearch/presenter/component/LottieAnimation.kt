package com.neoapp.pinsearch.presenter.component

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*

@Composable
fun LottieAnimation(
    @RawRes resId : Int,
    modifier: Modifier = Modifier,
    itrations : Int = LottieConstants.IterateForever,
    restartOnPlay : Boolean = true
){
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = itrations,
        restartOnPlay = restartOnPlay)
    LottieAnimation(composition,progress, modifier)
}