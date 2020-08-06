package `in`.curioustools.dagger1.a5_custom_builder_and_components

import javax.inject.Inject


//===============MODELS AND INTERFACES (or classes and their dependencies)

// ---------------------main model------------------------------------------------------------------
data class SuccessfullyCompletedDialog @Inject constructor(
    private val mainView: CustomDetailsView,
    val doneButton: DoneButton,
    val onDoneButtonClick: OnDoneButtonClick
)

// ------------------dependencies and sub dependencies ---------------------------------------------

// adm1 : annotatable(or injectable) dependency for model 1
class CustomDetailsView @Inject constructor()

// udm1 : unannotatable(or uninjectable) dependency for model 1)
data class DoneButton(val text: String)

// udi1 : unannotatable(or uninjectable) dependency for interface object 1)
interface OnDoneButtonClick {
    fun onClick(doneButton: DoneButton)
}

