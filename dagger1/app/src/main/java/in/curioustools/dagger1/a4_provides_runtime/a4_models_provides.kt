package `in`.curioustools.dagger1.a4_provides_runtime

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/*

 In this example, we will be looking at runtime Providers.

 say our class is dependent on an unannotatable dependency. we simply create a module
 (abstract/concrete) and use either @Binds or @Provides to create the dependency manually .
 the dagger simply uses those dependencies when we call DaggerComponent.create()

 But here we are hardcoding the code for creation of the dependency. however, Dagger also lets us
 pass some or all of values using a builder pattern for generating dependencies.

 in this example, we let user pass the complete category object by themselves. dagger is simply
 gonna take the returned value from the @Provides function and generate an Item Object from it
 of courser our real life class would still require a better solution because our
 actual class would have looked like this:
data class Item @Inject constructor(
    private val id:String,
    private val name:String,
    private val category: Category //uac
)
  and none of  our 3 uac approaches(binds, provides and runtime provides deals with that correctly

  also, 2 fun facts:
  1. it is not exclusively necessary to have a param in Module to se as builder. you can attach modules
     via builder even if there are no parameter bound modules

  2. you can even make the @Provides functions static. via static in java and companion object in kotlin.
    here something interesting happens. once again, dagger has no problems in letting you attach
    modules via builder but this time it shows a deprection warning for our class, indicating that its
    going to  continue using the already available  static method and attaching a new instance of
    module is pointless

 another interesting fact(for me) is that dagger changes the definition of builder from what i
 thought it to be. I thought that a builder attaches the minimum required dependencies to an object
 and allows for additional depenedencies / modifying other depenedencies for an object via optional
 function calls.
 BUT the builder that dagger generates does not have any such minimum dependency fullfillment criteria.
 here, if you don't call all(or atleast some) attachment functions, our final output generated from
 the build will be incomplete and cause errors
*/


//models
data class Item @Inject constructor(

    private val category: Category //uac
)


//------------------------------un-annotatable classes(uac)
data class Category  constructor(
    private val timestamp: Long,
    private val catName: String
)


@Module
class RuntimeUMPModuleForCategory(private val userDefinedCategory: Category){

    @Provides
    fun addCategory():Category{
        return userDefinedCategory
    }
}


@Module
class UMPModuleForItemIdAndName{

    companion object {
        @Provides
        fun getString() = "Hello"
    }
}

@Component(modules = [RuntimeUMPModuleForCategory::class,UMPModuleForItemIdAndName::class])
interface ItemGenerator{

    fun getItem():Item
}

/*
class Activity:.. {


    fun getTimestampViaProvides() {
        val myCat = Category(System.currentTimeMillis(), "Breads")

        val itemGenerator: ItemGenerator? =  DaggerItemGenerator.builder().runtimeUMPModuleForCategory
                                                                                  (RuntimeUMPModuleForCategory(myCat)).build()

        if (itemGenerator != null) {
            val item = itemGenerator.getItem()
            println(item)
        }
    }

}


* */