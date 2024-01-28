package com.example.portals.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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

@Composable
fun DefaultItemView(
    modifier: Modifier = Modifier,
    ring: Ring = Ring(),
    image: Image
) {
    val showSkeleton = remember { mutableStateOf(true) }
    val showFallback = remember { mutableStateOf(false) }

    val ringPadding = if (ring.width > 0.dp) 10.dp else 0.dp

    Box(
        modifier = modifier
            .border(
                width = ring.width,
                shape = CircleShape,
                color = ring.color
            )
            .padding(ringPadding)
    ) {
        Box(
            modifier = modifier
                .size(100.dp)
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
        ring = Ring(width = 6.dp)
    )
}
