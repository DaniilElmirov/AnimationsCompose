package com.elmirov.animationscompose.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateContent() {

    var isFirstScreenLaunched by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isFirstScreenLaunched = !isFirstScreenLaunched }
        ) {
            Text(text = "Switch screens")
        }

        AnimatedContent(
            targetState = isFirstScreenLaunched,
            transitionSpec = {
                if (targetState) {
                    slideInHorizontally(tween(durationMillis = 2000)) { -it } with
                            slideOutHorizontally(tween(durationMillis = 2000)) { it }
                } else {
                    slideInHorizontally(tween(durationMillis = 2000)) { it } with
                            slideOutHorizontally(tween(durationMillis = 2000)) { -it }
                }
            }
        ) { launchFirstScreen ->
            if (launchFirstScreen) {
                Screen1()
            } else {
                Screen2()
            }
        }
    }
}

@Composable
private fun Screen1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    )
}

@Composable
private fun Screen2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    )
}