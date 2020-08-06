package `in`.curioustools.manual_di.manual_di_approach3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity3 : AppCompatActivity() {
    //    private lateinit  var loginContainer: LoginContainer
    private lateinit var appC: AppContainer
    private var loginVm: LoginViewModel3? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //here , we simply moved our  login logic to a seperate container LoginContainer
        // the login container's instance is also available in the appContainer, and the activity
        // should be using that only, but now the activity has the ability to handle the lifecycle of
        // container. so for eg, if the activity comprises of multiple fragments doing
        // different stages of login, the activity can start the container in oncreate and end it in
        // on  Destroy
        appC = (application as MainApplication3).appContainer

        appC.loginContainer = LoginContainer(appC.repository)

        loginVm = appC.loginContainer?.loginMaker?.create()


        //here the problems remaining are:
        //1. code duplication and boiler plate:                 PARTIALLY() SOLVED in eg2, still remains same
        //2. unnecessary sequential initialisation:             UNSOLVED
        //3. no reusability w/o singleton :                     SOLVED WITH NO SINGLETON in eg2
        //4. we have to manage the container by ourselves:      UNSOLVED
        //5. Limiting the lifecycle of dependencies:            SOLVED  here. now activity can manage the dependency lifecycle

    }

    override fun onDestroy() {
        super.onDestroy()

        appC.loginContainer = null
        loginVm = null


    }
}





