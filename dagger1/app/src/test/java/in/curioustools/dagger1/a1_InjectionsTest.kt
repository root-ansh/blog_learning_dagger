package `in`.curioustools.dagger1

import `in`.curioustools.dagger1.a1_inject.Car
import `in`.curioustools.dagger1.a1_inject.constructor_injection.CarInstanceGenerator
import `in`.curioustools.dagger1.a1_inject.constructor_injection.DaggerCarInstanceGenerator
import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class a1_InjectionsTest {
    @Test
    fun addition_isCorrect() {



        assertEquals(4, 2 + 2)

    }


    @Test
    fun constructorInjection(){
        val carGenerator:CarInstanceGenerator = DaggerCarInstanceGenerator.create()
        val car = carGenerator.getCarInstance()
        println(car.getDriveStatus())
    }

    @Inject
     public lateinit var fieldInjectedCar: Car

    @Test
    fun fieldInjection(){
        //somehow does not work, maybe because dagger does not generate some "magic" for classes
        // which are not lifecycle bounded
        // update:  **maybe because dagger does not generate "generic injectors", and we have to add
        // another inject(test:InjectionTest) class for it to recognise InjectionTest class. but since
        // test classes are not accessible via main module, the final answer is yes , usual testing
        // does not work

//        val  carInstanceGenerator : CarInstanceGenerator2 = DaggerCarInstanceGenerator2.create()
//        carInstanceGenerator.inject(this::class.java)
//        println(fieldInjectedCar.getDriveStatus())



    }

}