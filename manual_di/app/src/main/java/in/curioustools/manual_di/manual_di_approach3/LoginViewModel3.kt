package `in`.curioustools.manual_di.manual_di_approach3



interface AbstractFactory3<T> {
    fun create(): T
}
class LoginVieModel3Factory3(private val repository: DataHandlingRepository3) :
    AbstractFactory3<LoginViewModel3> {
    override fun create(): LoginViewModel3 {
        return LoginViewModel3(
            repository
        )
    }


}

class LoginViewModel3(repository: DataHandlingRepository3)


