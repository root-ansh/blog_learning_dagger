package work.curioustools.hilt2023.network.models

data class CreateUserRequest(
    val name:String,
    val job:String,
    val id:Int? = null
)