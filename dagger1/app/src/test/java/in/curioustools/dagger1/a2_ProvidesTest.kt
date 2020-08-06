package `in`.curioustools.dagger1


import `in`.curioustools.dagger1.a2_provides.DaggerTimeStampObjGenerator
import `in`.curioustools.dagger1.a2_provides.TimeStampObjGenerator
import `in`.curioustools.dagger1.a3_binds.DaggerTractorGeneratorComponent
import `in`.curioustools.dagger1.a3_binds.TractorGeneratorComponent
import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class a2_ProvidesTest {
    @Test
    fun getTimestampViaProvides(){

        val timeStampObjGenerator: TimeStampObjGenerator? = DaggerTimeStampObjGenerator.create()

        if(timeStampObjGenerator!=null){
            val timestamp = timeStampObjGenerator.getTimestampInstance()
            println(timestamp)
            println(timestamp.partsStatusListener.getPartDetails("RAM"))
        }
    }



}