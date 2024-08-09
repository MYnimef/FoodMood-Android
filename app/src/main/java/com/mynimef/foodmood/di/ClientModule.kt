package com.mynimef.foodmood.di

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
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object ClientModule {

    @ActivityRetainedScoped
    @Provides
    fun provideStorage(@ApplicationContext context: Context): IClientStorage {
        return ClientStorageImpl(context = context)
    }

    @ActivityRetainedScoped
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

    @ActivityRetainedScoped
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