package `in`.curioustools.dagger1.a7_named

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named


/*
* A basic Provider module example
* */


@Module
class PencilRubberProviderModule {

    @Provides
    @Named(value = named_pencil)
    fun getPencilCompany() = "DOMS"

    @Provides
    @Named(value = named_rubber)
    fun getRubberCompany() = "Apsara"

    @Provides
    fun getDafultString() = "Default"

}

@Component(modules = [PencilRubberProviderModule::class])
interface PencilBoxComponent {
    fun getPencilBox(): PencilBox
}

fun main() {
    val pencilBoxComponent: PencilBoxComponent? = DaggerPencilBoxComponent.create()
    if (pencilBoxComponent != null) {
        val pb = pencilBoxComponent.getPencilBox()
        println(pb)
    }
}

