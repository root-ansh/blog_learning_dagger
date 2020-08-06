package `in`.curioustools.dagger1.a1_inject.field_injection

import dagger.Component

@Component
interface CarInstanceGenerator2{


    fun attachToActivity2(activityInstance: Activity2)
    fun attachToActivity3(activityInstance: Activity3)

}