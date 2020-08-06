package `in`.curioustools.manual_di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY



        val okhttp = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor()).build()
        val dao =
            Retrofit
                .Builder()
                .baseUrl("https://reqres.in/api/")
                .client(okhttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginDao::class.java)


        val r = dao.login("eve.holt@reqres.in", "cityslicka").execute()

        println(r)
        println(r.body())


        assertEquals(4, 2 + 2)


    }
}