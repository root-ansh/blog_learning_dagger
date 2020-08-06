package `in`.curioustools.dagger1.a1_inject.field_injection

import `in`.curioustools.dagger1.a1_inject.Car
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.inject.Inject


/*
* This is an example for "field injection"
* It does look like an alternative to " constructor injection" , but its more of an "extended feature"
* than that of an alternative.
*
* By definition, and its automatically creating an injected instance for us without us even
* making a call for object's instance (therby following the true definition of DI)
*/


/*
   STEPS TO FIELD INJECTION
   0. it will follow the same steps for models, ie annotation all the models and its dependencies
      with @Inject
   0. in our interface, we no longer need the getCar() function for accessing the car object(because
      dagger will be automatically injecting them), so it can be removed.
   1. add a special attachment function in our interface for each activity in which you want the
      dagger to automatically inject the instances. its syntax will be like this:
            fun attachToActivity2(activityInstance: Activity2)
            fun attachToActivity3(activityInstance: Activity3)
            ...
   2. create global instances which needs to be injected( previously defined by @Inject in our models)
      and annotate with @inject

  3. in on create, create the interface aka component instance as usual, and this time attach the
     activity using our previously created function attachToActivity2(..)

  4. use any service annotated with @inject freely without worrying about its initialisation!


 * */

class Activity2 : AppCompatActivity() {

    @Inject lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        attachDI()

        Log.e("TAG>>>>>", "onCreate: car is ${car.getDriveStatus()} ")
    }

    private fun attachDI() {
        val carInstanceGenerator2:CarInstanceGenerator2 = DaggerCarInstanceGenerator2.create()
        carInstanceGenerator2.attachToActivity2(this)
    }


    /*

working
in the background, di is generatin a special Activity2_MemberInjector.class having some
"provider implementations" which reates all the servies for an activity at 1 go. and thus we can
easily use it without requesting for a new service's object ourselves.

thus dagger will first execute a constructor injection followed by field and then service injection
for any particular class

DOWNSIDES :Here are some downsides:

0. [not sure, saying from personal experience] IT WILL ONLY GENERATE THE CODE FOR INJECTING
   DEPENDENCIES IF ITS ACTUALLY BEING USED SOMEWHERE. thus most of the time, you will be writing
   error code to trigger dagger to generate correct classes for u.
1. it cannot automatically inject for private variables.
2. it can only inject for global variables and nothing else
3. you still have to provide all the annotations for the service and its dependencies
4. it does not generate code for generic param (fun inject(x:Any)) in  attachment functions
   (or even generic activity param function, eg fun inject(x:AppCompatActivity) param  or via
    operator overloading. this causes some issues like :
    1. you will have to make multiple functions to let dagger automatically attach and inject for
       different activities
    2. you can NEVER TEST THE WORKING OF FIELD INJECTION USING STATIC JVM, because static jvm test
       classes are not visible/accessible from inside the app module and since the you cannot create a
       Test.class specific attachment function, you can't do attachTo_X(test.class).
       tldr: this won't work:

            class Test{
                @Inject lateinit var car: Car
                fun test(){
                    val dg2 = DaggerCarInstanceGenerator2.create()
                    dg2.attachTo_TestClass(this)

                    println(attachedCar.getDriveStatus() )
            }

use case
 - IT SHOULD ONLY BE USED FOR ANDROID SPECIFIC CLASSES WITH DAGGER.ANDROID PACKAGE because it is
   difficult to test.  OR for classes whose constructor is not availble to you for annotation.

   field injection is meant for framework classes like activity,fragments, etc


*/





}