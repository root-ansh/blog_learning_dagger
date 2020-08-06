package `in`.curioustools.dagger1


import `in`.curioustools.dagger1.a2_provides.DaggerTimeStampObjGenerator
import `in`.curioustools.dagger1.a2_provides.TimeStampObjGenerator
import `in`.curioustools.dagger1.a3_binds.DaggerTractorGeneratorComponent
import `in`.curioustools.dagger1.a3_binds.TractorGeneratorComponent
import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject


class a3_BindsTest {

    @Test
    fun getTractorViaBindsAndProvides(){
        val tractorGenerator :TractorGeneratorComponent? =DaggerTractorGeneratorComponent.create()

        if(tractorGenerator!=null){
            val tractor = tractorGenerator.getTractor()
            println(tractor)
            println(tractor.engine.getEngineDetails())
        }
    }

}