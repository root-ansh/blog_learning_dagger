package work.curioustools.hilt2023.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import work.curioustools.hilt2023.network.utils.BackendJsonStructure
import work.curioustools.hilt2023.network.models.CreateUserRequest
import work.curioustools.hilt2023.network.models.CreateUserResponse
import work.curioustools.hilt2023.network.models.UserResponse

interface UserAPI {
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") pageNum: Int? = null,
        @Query("per_page") perPage: Int? = null,
    ): Response<BackendJsonStructure<List<UserResponse>>>

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<BackendJsonStructure<UserResponse>>


    @POST("/users")
    suspend fun createUser(@Body data: CreateUserRequest): Response<BackendJsonStructure<CreateUserResponse>>

    @PATCH("/users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body data: CreateUserRequest
    ): Response<BackendJsonStructure<CreateUserResponse>>



}