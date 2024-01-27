package com.example.portals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.portals.core.Click
import com.example.portals.core.PortalItem
import com.example.portals.ui.theme.PortalsTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortalsTheme {
                PortalsStoriesList(portalSlot = {
                    StoryItem(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color.Blue),
                    )
                }, onClick = {})
            }
        }
    }
}

@Composable
fun StoryItem(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = "L")
    }
}

@Preview(showBackground = true)
@Composable
fun StoryItemPreview() {
    PortalsTheme {
        StoryItem()
    }
}

@Composable
private fun PortalsStoriesList(
    portalSlot: @Composable () -> Unit,
    onClick: (Click) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val portalItems = listOf(
            PortalItem(
                id = UUID.randomUUID()
            ),
            PortalItem(
                id = UUID.randomUUID()
            ),
            PortalItem(
                id = UUID.randomUUID()
            )
        )
        LazyRow(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(portalItems, key = {it.id}) {
                onClick(Click.OnClick(it))
                portalSlot()
            }
        }
    }
}

@Preview
@Composable
fun StoriesPreview() {
    PortalsTheme {
        PortalsStoriesList(onClick = {}, portalSlot = {
            StoryItem(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
            )
        }
        )
    }
}