package com.example.portals.ui.components

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class Image(
    val source: String?,
    val contentDescription: String
)

class Ring(
    val width: Dp = (-1).dp,
    val steppers: Int = 0,
    val color: Color = Color.LightGray
)

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun DefaultItemView(
    modifier: Modifier = Modifier,
    ring: Ring = Ring(),
    image: Image,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null
) {
    val showSkeleton = remember { mutableStateOf(true) }
    val showFallback = remember { mutableStateOf(false) }

    val selected = remember { mutableStateOf(false) }

    val scale = animateFloatAsState(
        if (selected.value) 0.9f else 1f,
        label = "",

        animationSpec = tween(durationMillis = 300)
    )

    val ringPadding = ring.width * 2

    Box(
        modifier = modifier
            .scale(scale.value)
            .border(
                width = ring.width,
                shape = CircleShape,
                color = ring.color
            )
            .padding(ringPadding)
            .pointerInput(Unit){
                detectTapGestures(
                    onPress = {
                        selected.value = true
                        tryAwaitRelease()
                        selected.value = false
                    },
                    onTap = {
                        onClick()
                    },
                    onLongPress = {
                        onLongClick?.invoke()
                    }
                )
            }

    ) {
        Box(
            modifier = modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {

            AsyncImage(
                model = image.source,
                contentDescription = image.contentDescription,
                onSuccess = {
                    showSkeleton.value = false
                },
                contentScale = ContentScale.Fit,
                onError = {
                    showSkeleton.value = false
                    showFallback.value = true
                },
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize()
            )

            if (image.source.isNullOrBlank() || showFallback.value) {
                Text(
                    text = image.contentDescription.first().uppercase(),
                    color = Color.DarkGray,
                    fontSize = 48.sp
                )
            }

        }
    }
}

@Preview
@Composable
fun DefaultItemViewPreview() {
    DefaultItemView(
        image = Image(
            source = "https://www.picsum.photos/100/100",
            contentDescription = "text"
        ),
        ring = Ring(width = 3.dp),
        onClick = {},
    )
}
