package work.curioustools.hilt2023.network.usecases

import work.curioustools.hilt2023.network.models.CreateUserRequest
import work.curioustools.hilt2023.network.models.CreateUserResponse
import work.curioustools.hilt2023.network.models.UserResponse
import work.curioustools.hilt2023.network.repos.UserRepo
import work.curioustools.hilt2023.network.utils.ApiResponse
import work.curioustools.hilt2023.network.utils.BaseConcurrencyUseCase
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val repo: UserRepo) :
    BaseConcurrencyUseCase<Unit, ApiResponse<List<UserResponse>>>() {
    override suspend fun getRepoCall(param: Unit): ApiResponse<List<UserResponse>> {
        return repo.getUserList()
    }
}

class GetSingleUserUseCase @Inject constructor( private val repo: UserRepo) :
    BaseConcurrencyUseCase<Int, ApiResponse<UserResponse>>() {
    override suspend fun getRepoCall(param: Int): ApiResponse<UserResponse> {
        return repo.getUser(param)
    }
}

class UpdateUserUseCase @Inject constructor( private val repo: UserRepo) :
    BaseConcurrencyUseCase<CreateUserRequest, ApiResponse<CreateUserResponse>>() {
    override suspend fun getRepoCall(param: CreateUserRequest): ApiResponse<CreateUserResponse> {
        return repo.updateUser(param)
    }
}

class CreateUserUseCase @Inject constructor( private val repo: UserRepo) :
    BaseConcurrencyUseCase<CreateUserRequest, ApiResponse<CreateUserResponse>>() {
    override suspend fun getRepoCall(param: CreateUserRequest): ApiResponse<CreateUserResponse> {
        return repo.createUser(param)
    }
}
