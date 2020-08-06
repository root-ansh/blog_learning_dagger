package `in`.curioustools.dagger1.a2_provides

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



/*

injection using @Provides and @Module.
So far we have been using the following approach:

>> user wants an object of A(which is created as A aObj = new A(bObj) ) to be injected wherever
   it requires A's object.
1. user  annotate A's constructor and B's constructor with @Inject. This tells the DI to create
  factories for both A and B

2. user  creates an interface having A's (&/or B's)  Getter  and annotate it with @Component.
  this tells the compiler to what will be the ports/endpoints where user is expecting to get the
  instance of A.

3. user rebuilds. dagger generates the endpoint via interface from step#2 and user access the
   instance of A via the endpoint
   ( user can also use field injections to attach an activity  and make endpoints in a particular
     activity's  global objects)

> But what if A has a dependency on X whose constructor cannot be annotated by us? like what if
  user's class is like this : class TimeStamp(val date:Date, val id:String, listenr:Listenr) ?

  Here , if user was to create a TimeStamp class of its own,he could have easily created it as:
  val date =Date()
  val string = "Hello"
  val listener = new Listener{...]
  val Timestamp = TimeStamp(date,string)

  but dagger does not know how to create such objects without annotations. So it lets user create
 the object generator functions on its own and it simply uses them to create the final instance.

 thus, user creates a module with provider:
     @Module
     class UnAnnotatedDependenciesProviderModule{
        @Provides
        fun getMeADatePleaseTinder() = Date()

        @Provides
        fun getFallbackString() = "error_fallback_used"

        @Provides
        fun getListenr() = new Listener {...}

     }
-------------------------------------------------------------------------
and add it to component interface annotation:

    @Component(modules=[UnAnnotatedDependenciesProviderModule::class])
    interface TimeStampComponent{ //aka CoolerGenerator
        fun getTimeStamp():TimeStamp
    }

* */


class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val timeStampObjGenerator:TimeStampObjGenerator? = DaggerTimeStampObjGenerator.create()

        if(timeStampObjGenerator!=null){
            val timestamp = timeStampObjGenerator.getTimestampInstance()
            println(timestamp)
        }
    }
}

/*
* NOTE : although, @Component can take multiple modules, the generators for each data type must be
* created only once. so you cannot, for eg create @provides fun getStringA():String in 1st module and
* getStringB():String in 2nd module
* .The best use case for multiple modules is to use @binds and @provides in the same dagger project
* , @bind(discussed later)s being more efficient than @Provides for generating "implementaions for interfaces" ( i mean
*   objects whose class implement the interface so that dagger could use that as an "interface object",
*   don't know the actual terminology for this)
*
* */