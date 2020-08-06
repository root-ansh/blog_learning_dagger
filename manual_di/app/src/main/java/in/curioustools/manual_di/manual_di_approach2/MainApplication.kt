package `in`.curioustools.manual_di.manual_di_approach2

import `in`.curioustools.manual_di.manual_di_approach2.LoginContainer
import android.app.Application

class MainApplication :Application(){
    val appContainer =
        LoginContainer()

}