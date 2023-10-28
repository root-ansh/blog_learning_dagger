package work.curioustools.hilt2023.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import work.curioustools.hilt2023.network.api.UserAPI


@Module
@InstallIn(SingletonComponent::class)
class ApiProviderModule {

    // ---- API INJECTION
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }
}