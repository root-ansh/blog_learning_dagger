@file:Suppress("SENSELESS_COMPARISON")

package `in`.curioustools.dagger1.a5_custom_builder_and_components.c_builder

import `in`.curioustools.dagger1.a5_custom_builder_and_components.DoneButton
import `in`.curioustools.dagger1.a5_custom_builder_and_components.OnDoneButtonClick
import `in`.curioustools.dagger1.a5_custom_builder_and_components.SuccessfullyCompletedDialog
import dagger.BindsInstance
import dagger.Component


/*
 Just a basic example of making a custom builder for our component.
 here, the dagger does neither generates the create method nor it generates the dagger code for build()
 method. but it still provides user with th build() functionality based on the @Component.Builder
 class we created.  we no longer need a module

* */

//===================ProviderModules================================================================
// not needed anymore, we are asking user to provide all the dependencies via builder

//=========================Components===============================================================

@Component /*(no modules required)*/
interface SuccessfullyDoneDialogGenerator {
    fun getSuccessFullyDoneDialog(): SuccessfullyCompletedDialog

    @Component.Builder
    interface CustomBuilder {

        @BindsInstance
        fun addButton(button: DoneButton): CustomBuilder

        @BindsInstance
        fun addDoneButtonClickListener(listener: OnDoneButtonClick): CustomBuilder

        fun build(): SuccessfullyDoneDialogGenerator


    }
}

//=============================Runner===============================================================
fun main() {

    var dialogGenerator: SuccessfullyDoneDialogGenerator? = null

    dialogGenerator =
        DaggerSuccessfullyDoneDialogGenerator.builder()
            .addButton(DoneButton("Yayy"))
            .addDoneButtonClickListener(
                object : OnDoneButtonClick {
                    override fun onClick(doneButton: DoneButton) {
                        println("${doneButton.text} is sexy")
                    }
                })
            .build()


    if (dialogGenerator != null) {
        val myDialog = dialogGenerator.getSuccessFullyDoneDialog()
        println(myDialog)
        println(myDialog.doneButton)
        println(myDialog.onDoneButtonClick.onClick(myDialog.doneButton))
    }
}

/*
if you notice care fully , dagger is still providing *some* dependencies , like CustomView,
because it has the sufficient guidance required for generation. thus if you uncomment the module code,
you will also have the create() function available where you can pass the module by yourself
. check the custom_factory_class for that code.

NOTE : WE CANNOT USE @COMONETN.VUILDER AND @COMPONENT.FACTORY FOR SAME COMPONENT.
and that's just stupid
* */












