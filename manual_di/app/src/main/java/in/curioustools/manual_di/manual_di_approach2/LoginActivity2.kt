package `in`.curioustools.manual_di.manual_di_approach2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //manual injection part 2

        /*
        in the second approach, we move all the  initialisation to a seperate class called
        LoginContainer and create its instance in the main application. thus we have an instance
        that is shared across different activities
        every time we need a view model, all the nested dependencies for viewmodel,
        aka repository-> offline/online data source->login dao, etc will be reused without needing
        for a seperate instance or configuration.
        (note that this is not a singleton, the Container class is actually managing the dependencies
        and rather injecting them into different classes)

        */

        val repository = (application as MainApplication).appContainer.repository
        val viewModel =
            LoginViewModel2(
                repository
            )


        /*


        You might be asking why not save the viewmodel itself in the LoginContainer? well,idk.
        maybe viewmodels are necessary to be created as new instance for every activity? let's
        assume that.

        But we can still go another step ahead and ensure that we are no more doing the task of
        passing the parameters to viewmodel ourselvs, instead this massive dependency manager
        container(say dmc) handles the task of "injecting repo in viewmodel" on its own,
        using "Factory" Pattern(kind of like closure,do the research yourself, not gonna add here).
        just a summary, in factory pattern, all the dependencies required for making an object are
        supplied by the factory. checkout the login container kt file having loginviewmodelfactory.

        I would call these factories  as RUNTIME PRE-INJECTED OBJECT GENERATOR REPLACEBALE CLASSES
        (rpiogrc):

        they generate  object on demand at runtime, and they "inject" already
        created dependencies inside the object  before generation and they are child classes to an
         abstract interface defining the generator, therefore are replaceable

        note that the interface there is just to adhere to another DI principle of replacability:
        instead of using the direct Viemodel generator factory, we are using an Abstract factory,
        such that any class having the viewmodel eiter function(enforced by such interface)
        will
        */

        val viewModel2 = (application as MainApplication).appContainer.loginMaker.create()

        /*
         * Now let's get back to the problems from approach1 :
         *   1. code duplication and boiler plate: PARTIALLY() SOLVED :  we no longer will need to write
         *      all those code for multiple activites which require a viewmodel. but we still have to
         *      write all those factories for pre runtime injecting ourselves
         *
         *   2. unnecessary sequential initialisation: UNSOLVED
         *
         *  3. no reusability : SOLVED . NO SINGLETON , the injected dependencies are just created
         *     once and share across different activities( unless they are "needed" to be generated
         *     every time)
         */

        /* and what problems did this approach added?
         * 4. we have to manage the container by ourself.
         * 5. there is still a lot of boilerplate in the container
         * 6. LIFECYCLE OF DEPENDENCIES . it is very important issue. the dependencies remain alive
         *    throughout the app even if they are not needed. There should be a way to limit those
         *    dependencies to only a particular "flow", i.e a dependency is only alive for particular
         *    classes but dies in other classes
         *
         *
         * aka problems 1,2,4,5,6  (1,4,5,6 and maybe 2 can all be handled by a library. we can almost handle 6 by ourselves,
         * as in next example)
         *
         *
         * */


    }
}





