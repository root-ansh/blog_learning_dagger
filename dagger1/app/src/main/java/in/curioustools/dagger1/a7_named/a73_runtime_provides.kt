package `in`.curioustools.dagger1.a7_named

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class PencilRubberProviderModuleRuntime(
    private val pencilCompany: String,
    private val rubberCompany: String,
    private val defaultString: String
) {

    @Provides
    @Named(value = named_pencil)
    fun getPencilCompany() = pencilCompany

    @Provides
    @Named(value = named_rubber)
    fun getRubberCompany() = rubberCompany

    @Provides
    fun getDafaultString() = defaultString

}

@Component(modules = [PencilRubberProviderModuleRuntime::class])
interface PencilBoxComponentRuntime {
    fun getPencilBox(): PencilBox
}

fun main() {
    var pencilBoxComponentRuntime: PencilBoxComponentRuntime? = null

    pencilBoxComponentRuntime =
        DaggerPencilBoxComponentRuntime
            .builder()
            .pencilRubberProviderModuleRuntime(PencilRubberProviderModuleRuntime("pcil","robber","bui"))
            .build()


    if (pencilBoxComponentRuntime != null) {
        val pb = pencilBoxComponentRuntime.getPencilBox()
        println(pb)
    }
}