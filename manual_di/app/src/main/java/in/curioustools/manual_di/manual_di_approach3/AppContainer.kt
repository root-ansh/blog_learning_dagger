package `in`.curioustools.manual_di.manual_di_approach3

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {

    private val okHttp = OkHttpClient().newBuilder().build()

    private val dao = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginDao3::class.java)

    private val onlineDataSource = OnlineDataSource3(dao)//  !!! change
    private val offlineDataSource = OfflineDataSource3()
    val repository = DataHandlingRepository3(onlineDataSource, offlineDataSource)
    var loginContainer: LoginContainer? = null

}

class LoginContainer(repository: DataHandlingRepository3) {


    val loginMaker: AbstractFactory3<LoginViewModel3> = LoginVieModel3Factory3(repository)

}