package `in`.curioustools.manual_di.manual_di_approach3



import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class DataHandlingRepository3 (onlineDataSource: OnlineDataSource3, offlineDataSource: OfflineDataSource3)
class OnlineDataSource3(dao: LoginDao3)
class OfflineDataSource3 ( )


data class LoginResponse3(val token: String)
interface LoginDao3 {
    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse3>
}
// response  builder method in the containers

