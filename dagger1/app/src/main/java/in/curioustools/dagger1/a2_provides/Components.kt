package `in`.curioustools.dagger1.a2_provides

import dagger.Component


@Component(modules=[UDPModuleDefault::class])
interface TimeStampObjGenerator{ //aka CoolerGenerator

    fun getTimestampInstance():TimeStamp
}




