package `in`.curioustools.dagger1.a1_inject.constructor_injection

import `in`.curioustools.dagger1.a1_inject.Car
import dagger.Component


@Component
interface CarInstanceGenerator {
//usually called CarComponent (aka XComponent : idk why they want component as name)

    fun getCarInstance(): Car

}