package `in`.curioustools.dagger1.a1_inject.constructor_injection

import `in`.curioustools.dagger1.a1_inject.Car
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        A basic dagger "constructor injection" example.
        Here , we :
        1. annotate our model class AND its related dependencies via @Inject annotation
        2. create a  interface called CarInstanceGenerator (or CarComponent, in more famous terms)
        3. annotate the interface as @Component
        4. re build to make dagger generate new classes

        then dagger generates an implementation of our interface as DaggerX,(DaggerCarGenerator in
        our example) which is mainly a object builder class that implemented the interface
        and provided us with a instance generator that we previously made ourselves.

        we can get the instance of car using the following lines:( you can run in junit tests too)

        // NOTE:  all dependency injection classes fucks up if  you as simple as move them to
                  different package
         * */


        val carGenerator: CarInstanceGenerator? = DaggerCarInstanceGenerator.create()

        if(carGenerator!=null) {
            val myCar: Car = carGenerator.getCarInstance()
            Log.e("TAG", "onCreate: car running status"+myCar.getDriveStatus() )
        }


        /*
         in terms of std terms, dagger did follow the rules:
         "Injector" implemented an "interface" on itself . the interface  defined what "services"
         the "client" wanted and injector injected them into client accordingly, whenever the client's
         instance was requested via injector

        * */


        //IDK if i am saying this correctly or not, but it still feels like a service locator.
        // here  we are not asking the injector to "create a new instance for us with all the
        // dependencies already injected" , BUT we rather ask ANY CLASS THAT HAS IMPLEMENTED OUR
        // SERVICE REQUEST INTERFACE to provide us with such instance. this could be injector or
        // anyone else, but this is SErvice locator pattern i believe

        //checkout activity 2 for "field injection" example. field injections are not recommended
        // but somewhat represents the true representation of DI , i guess




    }



}