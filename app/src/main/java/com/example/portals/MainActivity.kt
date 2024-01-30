package com.example.portals

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.portals.core.MyCustomPortalObj
import com.example.portals.core.MyCustomStepObj
import com.example.portals.core.Portal
import com.example.portals.ui.components.DefaultItemView
import com.example.portals.ui.components.Image
import com.example.portals.ui.components.Ring
import com.example.portals.ui.theme.PortalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortalsTheme {
                PortalList(
                    items = mutableListOf(
                        MyCustomPortalObj(
                            title = "title 1",
                            description = "description 1",
                            image = "https://www.picsum.photos/128/128",
                            steppers = mutableListOf(
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                            )
                        ),
                        MyCustomPortalObj(
                            title = "title 2",
                            description = "description 2",
                            image = "https://www.picsum.photos/128/129",
                            steppers = mutableListOf(
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                            )
                        ),
                        Portal(
                            steppers = mutableListOf(
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                                MyCustomStepObj(),
                            )
                        )
                    ),
                    renderView = {
                        when (it) {
                            is MyCustomPortalObj -> DefaultItemView(
                                image = Image(
                                    source = it.image,
                                    contentDescription = it.title
                                ),
                                ring = Ring(width = 3.dp, color = Color.Green),
                                onClick = {
                                    Toast.makeText(
                                        this,
                                        "click",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onLongClick = {
                                    Toast.makeText(
                                        this,
                                        "long click",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                            else -> CustomPortalItemView()
                        }
                    }, onClick = {})
            }
        }
    }
}

@Composable
fun CustomPortalItemView2(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    title: String? = null,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = title?.first().toString(), color = Color.White)
    }
}

@Composable
fun CustomPortalItemView(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    title: String? = null,
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(10))
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title?.first().toString(), color = Color.White)
    }
}

@Preview()
@Composable
fun CustomPortalItemViewPreview() {
    PortalsTheme {
        CustomPortalItemView(
            modifier = Modifier
                .height(100.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(10))
                .background(Color.Blue),
            title = "title"
        )
    }
}

@Preview()
@Composable
fun CustomPortalItem2ViewPreview() {
    PortalsTheme {
        CustomPortalItemView2(
            title = "re",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Color.Red)
        )
    }
}

@Composable
private fun PortalList(
    items: List<Portal>,
    renderView: @Composable (Portal) -> Unit,
    onClick: ((Click) -> Unit)? = null,
    onLongClick: ((Click.OnLongClick) -> Unit)? = null,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items, key = { it.id }) {
            renderView(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoriesPreview() {
    PortalsTheme {
        PortalList(
            items = mutableListOf(
                MyCustomPortalObj(
                    title = "title 1",
                    description = "description 1",
                    image = "",
                    steppers = mutableListOf(
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                    )
                ),
                MyCustomPortalObj(
                    title = "title 2",
                    description = "description 2",
                    image = "",
                    steppers = mutableListOf(
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                    )
                ),
                Portal(
                    steppers = mutableListOf(
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                        MyCustomStepObj(),
                    )
                )
            ),
            onClick = {},
            renderView = {
                when (it) {
                    is MyCustomPortalObj -> CustomPortalItemView2(
                        title = it.title,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color.Red),
                    )

                    else -> CustomPortalItemView()
                }
            }
        )
    }
}