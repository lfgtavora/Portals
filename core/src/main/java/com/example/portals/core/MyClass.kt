package com.example.portals.core

class Portal(
    key: String,
    items: List<PortalItem>,
    events: Event
)

class PortalItem(

)

sealed class Swipe : Event {
    data object Left : Swipe()
    data object Up : Swipe()
    data object Right : Swipe()
    data object Down : Swipe()
}

sealed class Click : Event {
    data class OnLongClick(val item: PortalItem) : Click()
    data class OnClick(val item: PortalItem): Click()
}

interface Event