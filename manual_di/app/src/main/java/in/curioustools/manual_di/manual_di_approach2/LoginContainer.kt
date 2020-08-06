package `in`.curioustools.manual_di.manual_di_approach2

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


data class LoginResponse2(val token: String)
interface LoginDao2 {
    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse2>
}

class LoginContainer {
    private val okHttp =
        OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
    private val dao =
        Retrofit
            .Builder()
            .baseUrl("https://reqres.in/api/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginDao2::class.java)
    private val onlineDataSource =
        OnlineDataSource2(dao)
    private val offlineDataSource =
        OfflineDataSource2()
    val repository =
        DataHandlingRepository2(
            onlineDataSource,
            offlineDataSource
        )

    val loginMaker: AbstractFactory<LoginViewModel2> =
        LoginVieModel2Factory(
            repository
        )


}


interface AbstractFactory<T> {
    fun create(): T
}


class LoginVieModel2Factory(private val repository: DataHandlingRepository2) :
    AbstractFactory<LoginViewModel2> {
    override fun create(): LoginViewModel2 {
        return LoginViewModel2(
            repository
        )
    }


}


/*

type: POST
api : https://reqres.in/api/login
extra params :
{
    "email": "eve.holt@reqres.in",
    "password": "cityslicka"
}

response:
{
    "token": "QpwL5tke4Pnpja7X4"
}

* */
