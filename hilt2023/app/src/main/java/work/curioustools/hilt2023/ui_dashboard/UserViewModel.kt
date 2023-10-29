package work.curioustools.hilt2023.ui_dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import work.curioustools.hilt2023.network.models.UserResponse
import work.curioustools.hilt2023.network.usecases.CreateUserUseCase
import work.curioustools.hilt2023.network.usecases.GetAllUsersUseCase
import work.curioustools.hilt2023.network.usecases.GetSingleUserUseCase
import work.curioustools.hilt2023.network.usecases.UpdateUserUseCase
import work.curioustools.hilt2023.network.utils.ApiResponse
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject  constructor(
     private val getAllUsersUseCase: GetAllUsersUseCase,
     private val getSingleUserUseCase: GetSingleUserUseCase,
     private val createUserUseCase: CreateUserUseCase,
     private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel() {
     private val _allUsersLiveData = getAllUsersUseCase.responseLiveData
     val allUsersLiveData:LiveData<ApiResponse<List<UserResponse>>>  = _allUsersLiveData


     fun getAllUsers(){
          _allUsersLiveData.postValue(ApiResponse.Loading())
          getAllUsersUseCase.requestForData(Unit)
     }

}