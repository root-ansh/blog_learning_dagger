@file:Suppress("SpellCheckingInspection")

package `in`.curioustools.dagger1.a6_singleton

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/*
A simple  @Singleton example using minimum basic dagger annotations
environment details:
>> Tanga depends on Ghoda and License
>> License is an injectable(or as i say, annotable) class, so we let dagger generate its dependecies for us
>> Ghoda is an uninjectable(aka unannotable) class, so we create a provider-module for it.

By default , whenever we will request a tanga instance, dagger will create new ghoda nad new license
instance and a new Tanga instance (you can check by running the below testcase after removing all
@Singleton annotations )

Now there could be some cases of singletons:
- We might want to reuse the same ghoda instance everytimea new tanga instance is created.
- We might want to reuse the same  license instance everytime a new tanga instance is created.
- We might want to reuse the same  tanga instance itself  whnever tanga is requested( which automatically
reuses the same ghoda and license instance)

for all those cases, there is a simple 2 step procedure:
1. annotate the component class as @Singleton (Necessary)
2. anotate @singleton on places where the dagger will look for creating the instance of your class,
  for whichever class you want to be a singleton.

thus in  our example. if we want the ghoda to be singleton, we annotate alongside the
@Provides in module. if we want the license to be singleton, we annotate its class( because dagger
is generating its object automatically). If we want the complete Tanga object to be singleon we
 annotate its class.
 if you have used @binds method in your components then you should be annotating the function's parameter as singleton

fun facts :
- if all the dependencies of a class are singleton, the class automatcally becomes singleton
since all dependencies are being reused
- similarly if parent is singleton, all the dependencies are being reused.

* */


@Singleton
data class Tanga  @Inject constructor(private val ghoda:Ghoda, private val license:License)


class License @Inject constructor()

class Ghoda  constructor(private val name:String)


@Module
class GhodaProvider{

    @Provides
    fun getGhoda() = Ghoda("Dhanno")

}

@Singleton
@Component(modules = [GhodaProvider::class])
interface TangaGenerator{
    fun getNewTanga():Tanga
}


fun main() {

    val tangaGenerator:TangaGenerator = DaggerTangaGenerator.create()


    val tanga1 = tangaGenerator.getNewTanga()
    println("tanga1: hash: ${tanga1.hashCode()}, value= $tanga1")

    val tanga2 = tangaGenerator.getNewTanga()
    println("tanga1: hash: ${tanga2.hashCode()}, value= $tanga2")

    val tanga3 = tangaGenerator.getNewTanga()
    println("tanga1: hash: ${tanga3.hashCode()}, value= $tanga3")

    val tanga4 = tangaGenerator.getNewTanga()
    println("tanga1: hash: ${tanga4.hashCode()}, value= $tanga4")



}