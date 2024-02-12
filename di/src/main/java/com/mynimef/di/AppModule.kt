package com.mynimef.di

import android.content.Context
import com.mynimef.data_local.AppStorageImpl
import com.mynimef.data_remote.AppNetworkImpl
import com.mynimef.domain.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        @ApplicationContext context: Context
    ): AppRepository {
        val storage = AppStorageImpl(context = context)
        val network = AppNetworkImpl(tokenGetter = storage)

        return AppRepository(
            storageRoot = storage,
            networkRoot = network
        )
    }

}