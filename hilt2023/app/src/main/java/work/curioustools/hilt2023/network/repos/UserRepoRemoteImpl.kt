package work.curioustools.hilt2023.network.repos

import work.curioustools.hilt2023.network.api.UserAPI
import work.curioustools.hilt2023.network.models.CreateUserRequest
import work.curioustools.hilt2023.network.models.CreateUserResponse
import work.curioustools.hilt2023.network.models.UserResponse
import work.curioustools.hilt2023.network.utils.ApiResponse
import work.curioustools.hilt2023.network.utils.toApiResponse
import javax.inject.Inject

class UserRepoRemoteImpl @Inject constructor(private val userAPI: UserAPI) : UserRepo {
    override suspend fun getUserList(): ApiResponse<List<UserResponse>> {
        return userAPI.getUserList().toApiResponse()
    }

    override suspend fun getUser(id: Int): ApiResponse<UserResponse> {
        return userAPI.getUser(id).toApiResponse()
    }

    override suspend fun createUser(data: CreateUserRequest): ApiResponse<CreateUserResponse> {
        return userAPI.createUser(data).toApiResponse()
    }

    override suspend fun updateUser(data: CreateUserRequest): ApiResponse<CreateUserResponse> {
        if (data.id == null) error("pass id when updating user. id cannot be null")
        return userAPI.updateUser(data.id, data).toApiResponse()
    }

    // todo we got 2 more classes : UserRepoCacheImpl and UserRepoDataSource, but that can be included later

}
