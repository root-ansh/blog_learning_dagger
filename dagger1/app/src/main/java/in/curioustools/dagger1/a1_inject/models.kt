@file:Suppress("SENSELESS_COMPARISON")

package `in`.curioustools.dagger1.a1_inject

import javax.inject.Inject


//========================vvv-----MODELS----vvv ====================================================

class Car @Inject constructor(private val engine: Engine, private val wheels: Wheels) {


    fun getDriveStatus():String {
        var s ="CAR STATUS:\n"

        s+= (if(engine==null) "engine not ready" else "engine is ready")+"\n"
        s+= (if(wheels==null) "wheels not ready" else "wheels are functioning")+"\n"

        return s

    }
}

class Engine @Inject constructor()


class Wheels @Inject constructor()

