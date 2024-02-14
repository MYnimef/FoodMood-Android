package com.mynimef.di

import android.content.Context
import com.mynimef.data_local.AppStorageImpl
import com.mynimef.data_remote.createAppNetworkImpl
import com.mynimef.domain.AppRepository
import com.mynimef.domain.IAppNetwork
import com.mynimef.domain.IAppStorage
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
    fun provideStorage(@ApplicationContext context: Context): IAppStorage {
        return AppStorageImpl(context = context)
    }

    @Singleton
    @Provides
    fun provideNetwork(storage: IAppStorage): IAppNetwork {
        return createAppNetworkImpl(tokenGetter = storage)
    }

    @Singleton
    @Provides
    fun provideRepository(
        storage: IAppStorage,
        network: IAppNetwork
    ): AppRepository {
        return AppRepository(
            storage = storage,
            network = network
        )
    }

}