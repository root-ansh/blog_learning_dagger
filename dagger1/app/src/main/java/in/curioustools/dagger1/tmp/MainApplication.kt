package `in`.curioustools.dagger1.tmp

import android.app.Application

class MainApplication :Application(){
    val appContainer =
        LoginContainer()

}