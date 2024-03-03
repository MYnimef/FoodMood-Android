package com.mynimef.di

import com.mynimef.domain.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object AuthModule {

    @ActivityRetainedScoped
    @Provides
    fun provideRepository(): AuthRepository {
        return AuthRepository()
    }

}