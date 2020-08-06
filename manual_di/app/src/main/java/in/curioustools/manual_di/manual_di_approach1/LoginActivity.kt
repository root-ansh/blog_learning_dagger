package `in`.curioustools.manual_di

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //manual injection part 1
        /*
            this is a basic dependency injection example:
                >> online data source needs a retrfoit client dao to work, but instead of creating it iself,
                  it requests its caller to pass retrofit as parameter

                >> online data source needs a context  to work, but instead of creating it iself,
                   it requests its caller to pass context as parameter

                >> repo needs both offline and online DS to work, but instead of creating it iself,
                   it requests its caller to pass those

                >> viewmodel  needs a repo work, but instead of creating it iself,
                   it requests its caller to pass repo as parameter

                thus activity, which need the viewmodel ends up creating vm and all its subsequent
                dependencies
                in order to use, viewmodel, we have to first satisfy dependencies of all its subsequent
                dependencies, like a tree.
                here, client-->sevice (--> means "is dependent on")
                1 viewmodel -> repo
                2. repo->offline, online
                3. offlineDataSource-> context
                4. onlineDataSource -> Retrofit dao
                so, we have to satisfy them sequentially as 4 then 3 then 2 then 1
        * */

        val okHttp = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
        val dao =
            Retrofit
                .Builder()
                .baseUrl("https://reqres.in/api/")
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginDao::class.java)
        val onlineDataSource = OnlineDataSource(dao)
        val offlineDataSource= OfflineDataSource(this)
        val repository =DataHandlingRepository(onlineDataSource,offlineDataSource)
        val viewModel = LoginViewModel(repository)


        /*
        ISSUES:
           1. code duplication&boiler plate: There's a lot of boilerplate code. If you wanted to create another
              instance of LoginViewModel in another part of the code, you'd have to write all this
              again.
           2. unnecessary sequential initialisation:   Dependencies have to be declared in order.
              You have to instantiate UserRepository before LoginViewModel in order to create it.

           3. no reusability w/o singleton : additional point for #1.  If you wanted to reuse repository or
              viewmodel across multiple features, you'd have to make it follow the singleton pattern.
              But The singleton pattern makes testing more difficult because all tests share
              the same singleton instance.
           we need a solution in which we could use a single instance of a class  without
           declaring it multiple times or making it a singleotn, which is easily replacable at
           runtime
        *
        * */






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

data class LoginResponse(val token: String)
interface LoginDao {
    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}






