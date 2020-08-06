package `in`.curioustools.dagger1.a7_named

import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

/*
A basic builder example. note : named is used with parameters and not on function
 */


@Component
interface PencilBoxComponent2{

    fun getPencilBox():PencilBox

    @Component.Builder
    interface  MyCustomBuilder{

        @BindsInstance
        fun setPencilCompany(@Named(named_rubber) company:String):MyCustomBuilder

        @BindsInstance
        fun setRubberCompany(@Named(named_pencil) company:String):MyCustomBuilder

        @BindsInstance
        fun setDefaultString(str:String):MyCustomBuilder



        fun build():PencilBoxComponent2

    }
}

fun main() {
    var pencilBoxComponent: PencilBoxComponent2? = null

    pencilBoxComponent =
        DaggerPencilBoxComponent2
            .builder()
            .setPencilCompany("Apsara")
            .setRubberCompany("Natraj")
            .setDefaultString("None").build()


    if (pencilBoxComponent != null) {
        val pb = pencilBoxComponent.getPencilBox()
        println(pb)
    }
}

