package work.curioustools.hilt2023.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import work.curioustools.hilt2023.network.repos.UserRepo
import work.curioustools.hilt2023.network.usecases.CreateUserUseCase
import work.curioustools.hilt2023.network.usecases.GetAllUsersUseCase
import work.curioustools.hilt2023.network.usecases.GetSingleUserUseCase
import work.curioustools.hilt2023.network.usecases.UpdateUserUseCase


@Module
@InstallIn(SingletonComponent::class)
class UseCaseProviderModule {

    @Provides
    fun provideGetAllUsersUseCase(repo: UserRepo) = GetAllUsersUseCase(repo)

    @Provides
    fun provideGetSingleUserUseCase(repo: UserRepo) = GetSingleUserUseCase(repo)

    @Provides
    fun provideCreateUserUseCase(repo: UserRepo) = CreateUserUseCase(repo)

    @Provides
    fun provideUpdateUserUseCase(repo: UserRepo) = UpdateUserUseCase(repo)


}