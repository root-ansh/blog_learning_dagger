package `in`.curioustools.dagger1

import `in`.curioustools.dagger1.a4_provides_runtime.Category
import `in`.curioustools.dagger1.a4_provides_runtime.DaggerItemGenerator
import `in`.curioustools.dagger1.a4_provides_runtime.ItemGenerator
import `in`.curioustools.dagger1.a4_provides_runtime.RuntimeUMPModuleForCategory
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class a4_ProvidesRuntimeTest {

    @Test
    fun getTimestampViaProvides() {
        val myCat = Category(System.currentTimeMillis(), "Breads")
        val itemGenerator: ItemGenerator? =
            DaggerItemGenerator.builder().runtimeUMPModuleForCategory(RuntimeUMPModuleForCategory(myCat)).build()

        if (itemGenerator != null) {
            val item = itemGenerator.getItem()
            println(item)
        }
    }


}