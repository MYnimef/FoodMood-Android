package com.mynimef.di

import android.content.Context
import com.mynimef.data_local.AppStorageImpl
import com.mynimef.data_remote.AppNetworkImpl
import com.mynimef.domain.AppRepository
import com.mynimef.domain.IAppNetworkRoot
import com.mynimef.domain.IAppStorageRoot
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideStorage(
        @ApplicationContext context: Context
    ): IAppStorageRoot {
        return AppStorageImpl(context = context)
    }

    @Singleton
    @Provides
    fun provideNetwork(storage: IAppStorageRoot): IAppNetworkRoot {
        return AppNetworkImpl(tokenGetter = storage)
    }

    @Singleton
    @Provides
    fun provideRepository(
        network: IAppNetworkRoot,
        storage: IAppStorageRoot
    ): AppRepository {
        return AppRepository(
            storageRoot = storage,
            networkRoot = network
        )
    }

}