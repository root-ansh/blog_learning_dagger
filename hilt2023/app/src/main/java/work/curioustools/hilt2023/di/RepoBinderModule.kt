package work.curioustools.hilt2023.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import work.curioustools.hilt2023.network.repos.UserRepo
import work.curioustools.hilt2023.network.repos.UserRepoRemoteImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoBinderModule {
    @Binds
    abstract fun bindsUserRepoRemote(userRemoteImpl: UserRepoRemoteImpl): UserRepo
}