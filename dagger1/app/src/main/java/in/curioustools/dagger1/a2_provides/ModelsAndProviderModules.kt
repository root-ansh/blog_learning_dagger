package `in`.curioustools.dagger1.a2_provides

import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Inject


//==================== MODELS =========================================


data class TimeStamp @Inject constructor(
    private val id: String,
    val date: Date,
    val computer: Computer,
    val partsStatusListener: PartsStatusListener
)

class Computer @Inject constructor()

interface PartsStatusListener{
    fun getPartDetails(part:String):String
}

//=================== MODULES ========================================
@Module
class UDPModuleDefault {  //Un-Anno-ta-table Dependency Provider Module (UDP Module)

    @Provides
    fun getCurrentDate(): Date = Date()

    @Provides
    fun getID(): String = UUID.randomUUID().toString().substring(0, 12)

    @Provides
    fun getPartsStatus():PartsStatusListener {
        return object : PartsStatusListener {
            override fun getPartDetails(part: String) = "$part is working just fine bro! I am an anonymous implementation"
        }
        //or you can just create an abstract class implementation. the thing is interface is
        // un injectable, but its implementation is injectable
    }

}

