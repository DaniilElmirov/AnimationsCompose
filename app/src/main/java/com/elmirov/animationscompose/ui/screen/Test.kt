package com.elmirov.animationscompose.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Test() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(
                state = rememberScrollState(),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isIncreased by remember {
            mutableStateOf(true)
        }
        val size by animateDpAsState(
            targetValue = if (isIncreased) 200.dp else 100.dp,
            animationSpec = tween(durationMillis = 2000, delayMillis = 1000, easing = EaseOutBounce)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isIncreased = !isIncreased }
        ) {
            Text(
                text = "Animate size",
            )
        }
        AnimatedContainer(
            text = "Size",
            size = size,
        )

        var isRectangle by remember {
            mutableStateOf(true)
        }
        //val radius by animateDpAsState(targetValue = if (isRectangle) 8.dp else 96.dp)
        val radiusPercent by animateIntAsState(
            targetValue = if (isRectangle) 20 else 48, //TODO аккуратнее с %, так как может выйти за пределы [0, 100]
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessMediumLow,
            )
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isRectangle = !isRectangle }
        ) {
            Text(
                text = "Animate shape",
            )
        }
        AnimatedContainer(
            text = "Shape",
            //radius = radius,
            radiusPercent = radiusPercent,
        )

        var isBordered by remember {
            mutableStateOf(false)
        }
        val borderWidth by animateDpAsState(targetValue = if (isBordered) 4.dp else 0.dp)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isBordered = !isBordered }
        ) {
            Text(
                text = "Animate border",
            )
        }
        AnimatedContainer(
            text = "Border",
            borderWidth = borderWidth
        )

        var isColored by remember {
            mutableStateOf(false)
        }
        val color by animateColorAsState(targetValue = if (isColored) Color.Magenta else Color.Blue)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isColored = !isColored }
        ) {
            Text(
                text = "Animate color",
            )
        }
        AnimatedContainer(
            text = "Color",
            color = color,
        )

        var isTransparent by remember {
            mutableStateOf(false)
        }
        val infiniteTransition = rememberInfiniteTransition()
        val transparent by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 3000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isTransparent = !isTransparent }
        ) {
            Text(
                text = "Animate visibility",
            )
        }
        AnimatedContainer(
            text = "Visibility",
            alpha = transparent,
        )
    }
}

@Composable
private fun AnimatedContainer(
    text: String,
    size: Dp = 200.dp,
    //radius: Dp = 8.dp,
    radiusPercent: Int = 4,
    borderWidth: Dp = 0.dp,
    color: Color = Color.Blue,
    alpha: Float = 1f,
) {
    Box(
        modifier = Modifier
            .alpha(alpha)
            .clip(RoundedCornerShape(radiusPercent))
            .border(width = borderWidth, color = Color.Black)
            .background(color = color)
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}