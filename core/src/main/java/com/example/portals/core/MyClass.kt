package com.example.portals.core

import java.util.UUID

open class Portal(
    val id: String = UUID.randomUUID().toString(),
    open val steppers: MutableList<Step>,
) {
    fun getStep(stepId: String): Step {
        return steppers.first { it.id == stepId }
    }

    fun getStepsBy(status: Status?): List<Step> {
        return steppers.filter { it.status == status }
    }
}

data class MyCustomPortalObj(
    val title: String = "",
    val description: String = "",
    val image: String = "",
    override val steppers: MutableList<Step>,
) : Portal(steppers = steppers)

abstract class Step(
    val id: String = UUID.randomUUID().toString(),
    val status: Status = Status.CREATED,
)

data class MyCustomStepObj(
    val title: String = "",
    val description: String = "",
    val image: String = ""
) : Step()


//status

sealed class Status {
    data object CREATED : Status()
    data object STARTED : Status()
    data object COMPLETED : Status()
    data class ERROR(val error: Throwable) : Status()
    data class LOADING(val progress: Int) : Status()
    data class PROGRESS(val progress: Int) : Status()
    data class SUCCESS(val data: Any) : Status()
    data class CANCELED(val data: Any) : Status()
    data class PAUSED(val data: Any) : Status()
    data class UNKNOWN(val data: Any) : Status()

}

//events
sealed class Swipe : Event {
    data object Left : Swipe()
    data object Up : Swipe()
    data object Right : Swipe()
    data object Down : Swipe()
}

sealed class Click : Event {
    data class OnLongClick(val item: Portal) : Click()
    data class OnClick(val item: Portal) : Click()
}

interface Event
