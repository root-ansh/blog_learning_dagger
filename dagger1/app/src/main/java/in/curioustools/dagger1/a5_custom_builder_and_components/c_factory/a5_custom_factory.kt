@file:Suppress("SENSELESS_COMPARISON")

package `in`.curioustools.dagger1.a5_custom_builder_and_components.c_factory

import `in`.curioustools.dagger1.a5_custom_builder_and_components.DoneButton
import `in`.curioustools.dagger1.a5_custom_builder_and_components.OnDoneButtonClick
import `in`.curioustools.dagger1.a5_custom_builder_and_components.SuccessfullyCompletedDialog
import dagger.Component
import dagger.Module
import dagger.Provides

//check the last line of a5_custom builder for more details

@Module
class UDPModule(private val text: String) {
    @Provides
    fun getGenericDoneButtonObj() =
        DoneButton(
            text
        )

    @Provides
    fun getGenericOnClickListener(): OnDoneButtonClick {
        return  object :
            OnDoneButtonClick {
            override fun onClick(doneButton: DoneButton) {
                println("Interface called with the done button having text as ${doneButton.text} baby!")
            }
        }
    }
}

//=========================Components===============================================================

@Component (modules = [UDPModule::class])
interface SuccessfullyDoneDialogGenerator2 {
    fun getSuccessFullyDoneDialog(): SuccessfullyCompletedDialog
    @Component.Factory
    interface CustomFactory{
        fun create( module: UDPModule): SuccessfullyDoneDialogGenerator2
    }
}

//=============================Runner===============================================================
fun main() {
    var dialogGeneratorDefault: SuccessfullyDoneDialogGenerator2? = null
    val defaultUDPModule = UDPModule("DefaultButtonText")
    dialogGeneratorDefault = DaggerSuccessfullyDoneDialogGenerator2.factory()
        .create(defaultUDPModule)

    if (dialogGeneratorDefault != null) {
        val myDialog = dialogGeneratorDefault.getSuccessFullyDoneDialog()
        println(myDialog)
        println(myDialog.doneButton)
        println(myDialog.onDoneButtonClick.onClick(myDialog.doneButton))
    }

}







