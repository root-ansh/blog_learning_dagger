package `in`.curioustools.dagger1.a1_inject.field_injection

import `in`.curioustools.dagger1.a1_inject.Car
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.inject.Inject

class Activity3 : AppCompatActivity() {

    @Inject lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        attachDI()
        Log.e("TAG>>>>>", "onCreate: car is ${car.getDriveStatus()} ")
    }

    private fun attachDI() {
        val carInstanceGenerator2:CarInstanceGenerator2 = DaggerCarInstanceGenerator2.create()
        carInstanceGenerator2.attachToActivity3(this)
    }

}