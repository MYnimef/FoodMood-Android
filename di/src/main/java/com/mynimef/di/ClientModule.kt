package com.mynimef.di

import android.content.Context
import com.mynimef.data_local.ClientStorageImpl
import com.mynimef.data_remote.createClientNetworkImpl
import com.mynimef.domain.AppRepository
import com.mynimef.domain.ClientRepository
import com.mynimef.domain.IAppNetwork
import com.mynimef.domain.IAppStorage
import com.mynimef.domain.IClientNetwork
import com.mynimef.domain.IClientStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {

    @Provides
    fun provideStorage(@ApplicationContext context: Context): IClientStorage {
        return ClientStorageImpl(context = context)
    }

    @Provides
    fun provideNetwork(
        appStorage: IAppStorage,
        appNetwork: IAppNetwork
    ): IClientNetwork {
        return createClientNetworkImpl(
            tokenGetter = appStorage,
            appNetwork = appNetwork
        )
    }

    @Provides
    fun provideRepository(
        storage: IClientStorage,
        network: IClientNetwork,
        appRepository: AppRepository
    ): ClientRepository {
        return ClientRepository(
            storage = storage,
            network = network,
            appRepository = appRepository
        )
    }

}