package work.curioustools.hilt2023.network.repos

import work.curioustools.hilt2023.network.utils.ApiResponse
import work.curioustools.hilt2023.network.models.CreateUserRequest
import work.curioustools.hilt2023.network.models.CreateUserResponse
import work.curioustools.hilt2023.network.models.UserResponse

interface UserRepo {
    suspend fun getUserList(): ApiResponse<List<UserResponse>>
    suspend fun getUser(id: Int): ApiResponse<UserResponse>
    suspend fun createUser(data: CreateUserRequest): ApiResponse<CreateUserResponse>
    suspend fun updateUser(data: CreateUserRequest) : ApiResponse<CreateUserResponse>
}

