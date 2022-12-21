package com.example.structure.presentation.model

data class StepperData(
    val value: String,
    var state: StepperState
)

sealed class StepperState {

    object Uncheck : StepperState()

    object Check : StepperState()
}

sealed class StepUi {
    object FirstStep : StepUi()
    object SecondStep : StepUi()
    object ThirdStep : StepUi()
    object StepFour : StepUi()
}
