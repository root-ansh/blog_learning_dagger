package work.curioustools.hilt2023.ui_dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import work.curioustools.hilt2023.R
import work.curioustools.hilt2023.network.utils.ApiResponse


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val userSharedViewModel by viewModels<DashboardSharedViewModel>()
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        userViewModel.allUsersLiveData.observe(this){
            if(it==null){
                log("response is null")
            }
            else{
                when(it){
                    is ApiResponse.Failure -> {
                        log("FAILURE|${it.errorCodeType()}|${it.errorMsg}|${it.errorCode}")
                    }
                    is ApiResponse.Success -> {
                        log("SUCCESS|${it.page}|${it.perPage}|${it.total}|${it.totalPages}||${it.data}|")

                    }
                    is ApiResponse.Loading -> {}

                    else -> {

                    }
                }
            }
        }
        userViewModel.getAllUsers()
    }

    private fun log(s:String){
        Toast.makeText(this,"received data", Toast.LENGTH_SHORT).show()
        Log.e("DashboardActivity", s )
    }
}