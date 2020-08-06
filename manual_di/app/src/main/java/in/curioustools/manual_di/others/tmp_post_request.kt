package `in`.curioustools.manual_di.others

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//

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

//data class LoginResponse(val token: String)
//interface LoginDao {
//    @POST("login")
//    @FormUrlEncoded
//    fun login(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<LoginResponse>
//}


//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Callback
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//fun makeSynchronousRequest(){
//    val okHttp = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
//    val dao =
//        Retrofit
//            .Builder()
//            .baseUrl("https://reqres.in/api/")
//            .client(okHttp)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(LoginDao::class.java)
//
//    val resp = dao.login("eve.holt@reqres.in", "cityslicka").execute()
//    println(resp)
//    println(resp.body())
//}
//
//fun makeAsynchronousRequest(callback : Callback<LoginResponse>){
//    val okhttp = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
//    val dao = Retrofit
//        .Builder()
//        .baseUrl("https://reqres.in/api/")
//        .client(okhttp)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(LoginDao::class.java)
//    dao.login("eve.holt@reqres.in", "cityslicka").enqueue(callback)
//}
//
//
//
